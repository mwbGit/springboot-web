package com.mwb.web.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.core.Seimi;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import com.mwb.web.model.ArticleInfo;
import com.mwb.web.service.ArticleService;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;
import java.util.Random;

/**
 * 描述:
 * 文章
 * https://www.pethome.com.cn/cjh/article/5768.html
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/10/14
 */
@Crawler(name = "article")
public class ArticleCrawler extends BaseSeimiCrawler {

//    @Autowired
    private ArticleService articleService;

    @Override
    public String[] startUrls() {

        String[] strs = new String[1000];

        int index = 5000;
        for (int i = 0; i < 1000; i++) {
            strs[i] = String.format("https://www.pethome.com.cn/cjh/article/%d.html", index++);
        }
        return strs;
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            if (doc == null) {
                return;
            }
            List<Object> urls = doc.sel("//div[@class='article-content']");
            Object image = doc.selOne("//div[@class='article-content']/p/img/@src");
            Object title = doc.selOne("//h1[@class='article-title']/text()");
            Object name = doc.selOne("//div[@class='article-sub']/span/a/text()");

//            logger.info("{}", urls.size());

//            for (Object s : urls) {
//                System.out.println(s);
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setAuthor((String) name);
            articleInfo.setSource("宠物之家");
            articleInfo.setTitle((String) title);
            articleInfo.setBody(urls.get(0).toString());
            articleInfo.setImage((String) image);
            articleInfo.setViewNum(500 + new Random().nextInt(3000));
            articleInfo.setPraiseNum(new Random().nextInt(500));
//                petPictureService.saveNotNull(petPicture);
//            }
            articleService.saveNotNull(articleInfo);
            System.out.println(22);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void operation(Response response) {
        JXDocument doc = response.document();

        try {
            List<Object> urls = doc.sel("//div[@class='pic_c_list bd']/img/@src");
            for (Object s : urls) {
                System.out.println("=======" + s);
            }
            System.out.println(response.getUrl());
            //do something

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        Seimi s = new Seimi();

        s.goRun("article");

    }

}