package com.mwb.web.controller;

import com.mwb.web.mapper.PetPicture1Mapper;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.parser.PageParser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLConnection;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/3
 */
@RestController
public class XxlController {

    @PageSelect(cssQuery = "ul")
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


    @RequestMapping("/test122")
    public int testJsonp() {

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
                            petPicture1Mapper.insert(pageVo.getTitle(), pageVo.getImages());
                        }
                    }
                })
                .build();
        System.out.println();
        return 1;
    }


}
