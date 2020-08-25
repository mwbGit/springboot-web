package com.mwb.mwb;

import com.mwb.web.mapper.PetMapper;
import com.mwb.web.mapper.PetPicture1Mapper;
import com.mwb.web.mapper.PetPictureMapper;
import com.mwb.web.model.PetInfo;
import com.mwb.web.model.PetPicture;
import com.mwb.web.service.OssService;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.parser.PageParser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 爬虫示例03：爬取页面，下载图片文件
 *
 * @author xuxueli 2017-10-09 19:48:48
 */
public class XxlCrawlerTest03 extends BaseTest{

    @PageSelect(cssQuery = ".f3 > ul > li")
    public static class PageVo {

        @PageFieldSelect(cssQuery = "img", selectType = XxlCrawlerConf.SelectType.ATTR, selectVal = "abs:src")
        private String images;

        @PageFieldSelect(cssQuery = "img", selectType = XxlCrawlerConf.SelectType.ATTR, selectVal = "abs:alt")
        private String title;

        public String getImages() {
            return images == null || images.length() ==0 ? "": images.substring(0, images.indexOf("?"));
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTitle() {
            return StringUtils.isNotBlank(title) ? title.substring(title.lastIndexOf("/") + 1) : "";
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "images='" + getImages() + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Autowired
    private PetPicture1Mapper petPicture1Mapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetPictureMapper petPictureMapper;

    @Autowired
    private OssService ossClient;

    @Test
    public void tt1() throws Exception {
        List<PetPicture>  pets = petPictureMapper.selectAll();
        for (PetPicture petInfo : pets) {
            String url = ossClient.uploadImageToOss(petInfo.getImage());
            petInfo.setImage(url);
            petPictureMapper.updateByPrimaryKeySelective(petInfo);
        }
        System.out.println(1);
    }

    @Test
    public void tt() throws Exception {
        List<PetInfo>  pets = petMapper.selectAll();
        for (PetInfo petInfo : pets) {
            String url = ossClient.uploadImageToOss(petInfo.getImage());
            petInfo.setImage(url);
            petMapper.updateByPrimaryKeySelective(petInfo);
        }
        System.out.println(1);
    }

    @Test
    public void testJsonp() {
        String[] arrs = new String[]{"cats_28424", "cats_28426", "cats_28429", "cats_28436", "cats_28431", "cats_28432", "cats_28438",
                "cats_28428", "cats_28430", "cats_28433", "cats_28437", "cats_28425", "cats_28427" };
        for (String url : arrs) {
            XxlCrawler crawler = new XxlCrawler.Builder()
                    .setUrls("http://www.xingquanpet.com/" + url + ".html")
//                .setWhiteUrlRegexs("https://gitee\\.com/xuxueli0323/projects\\?page=\\d+")
                    .setThreadCount(10)
                    .setPageParser(new PageParser<PageVo>() {
                        @Override
                        public void parse(Document html, Element pageVoElement, PageVo pageVo) {

                            // 文件信息
                            if (pageVo.title != null && pageVo.title.contains("猫")) {
                                System.out.println("___" + pageVo);
                                petPicture1Mapper.insert(pageVo.getTitle(), pageVo.getImages());
                            }
                        }
                    })
                    .build();
            crawler.start(true);
        }
        System.out.println(1);
    }
//
//    public static void main(String[] args) {
//        String str = "http://s9.cdn.viposs.com/default/1152058695.jpg?imageView2/1/w/270/h/270/q/90|watermark/2/text/MTU3MTEzNDk0MzI=/font/5Lu_5a6L/fontsize/1000/fill/I0ZGMDAwMA==/dissolve/20/gravity/Center/dx/10/dy/10";
//
//        System.out.println();
//    }

    public static void main(String[] args) {


        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls("http://www.xingquanpet.com/cats_28424.html")
//                .setWhiteUrlRegexs("https://gitee\\.com/xuxueli0323/projects\\?page=\\d+")
                .setThreadCount(3)
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageVo pageVo) {

                        // 文件信息
                        if (pageVo.title != null && pageVo.title.contains("猫")) {
                            System.out.println("___" + pageVo);
                        }
                    }
                })
                .build();

        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
