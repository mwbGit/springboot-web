package com.mwb.web.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.core.Seimi;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.PetPicture;
import com.mwb.web.service.DynamicService;
import org.apache.commons.lang3.StringUtils;
import org.seimicrawler.xpath.JXDocument;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述:
 * 动态
 * https://talk.pethome.com.cn/
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/10/14
 */
@Crawler(name = "dynamic")
public class DynamicCrawler extends BaseSeimiCrawler {

    Map<String, PetPicture> map = new HashMap<>();

//    @Autowired
    private DynamicService dynamicService;

    @Override
    public String[] startUrls() {

//        List<Integer> forunms = Arrays.asList(77, 83,53,55,145,144,52,151,56,57,84,49,47,48,50);
//        String[] strs = new String[10 * forunms.size()];
//        int index = 0;
//        for (Integer indx : forunms) {
//            for (int i = 0; i < 10; i++) {
//                strs[index++] = String.format("https://talk.pethome.com.cn/forum-%d-%d.htm?orderby=lastpid&digest=0", indx, i);
//            }
//        }
        return new String[]{""};
    }

    {

    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            if (doc == null) {
                return;
            }
            List<Object> names = doc.sel("//div[@class='forum_main main cl']/ul/li/a/img/@alt");
            List<Object> urls = doc.sel("//div[@class='forum_main main cl']/ul/li/a/img/@src");
            List<Object> content = doc.sel("//div[@class='forum_main main cl']/ul/li/div[@class='exp-text']/div[@class='exp-detail']/p/text()");
            List<Object> hrefs = doc.sel("//div[@class='forum_main main cl']/ul/li/div[@class='exp-text']/div[@class='exp-detail']/h1/a/@href");
            if (names == null) {
                return;
            }
            content = content.stream().filter(obj -> obj != null && StringUtils.isNotBlank(String.valueOf(obj))).collect(Collectors.toList());

//            for (int i = 0; i < names.size(); i++) {
//                DynamicInfo dynamicInfo = new DynamicInfo();
//                dynamicInfo.setUserId(10 + new Random().nextInt(15));
//                dynamicInfo.setUserName("");
//                dynamicInfo.setHeadImg("");
//                dynamicInfo.setTitle((String) names.get(i));
//                dynamicInfo.setContent((String) content.get(i));
//                dynamicInfo.setImages((String) urls.get(i));
//                dynamicInfo.setStatus(1);
//                dynamicInfo.setPraiseNum(new Random().nextInt(100));
//                dynamicInfo.setAddTime(new Date(1602727832036L - 100000000 * new Random().nextInt(10)));
////                dynamicService.saveNotNull(dynamicInfo);
//            }

            for (Object s : hrefs) {
                push(new Request("https://talk.pethome.com.cn/" + s.toString(), "operation"));
            }
            System.out.println(22);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        Seimi s = new Seimi();

        s.goRun("basic");

    }

    public void operation(Response response) {
        JXDocument doc = response.document();

        try {
            Object title = doc.selOne("//div[@class='left']/h1/strong/text()");
            Object content = doc.selOne("//div[@class='works-cont editor-style ui-theme']");
            List<Object> imgs = doc.sel("//div[@class='works-cont editor-style ui-theme']/p/img/@src");
            if (imgs.size() > 2) {
                return;
            }
            DynamicInfo dynamicInfo = new DynamicInfo();
            dynamicInfo.setUserId(10 + new Random().nextInt(5));
            dynamicInfo.setUserName("");
            dynamicInfo.setHeadImg("");
            dynamicInfo.setTitle((String) title);
            dynamicInfo.setContent(content.toString());
            dynamicInfo.setImages("");
            dynamicInfo.setStatus(1);
            dynamicInfo.setPraiseNum(54 + new Random().nextInt(100));
            dynamicInfo.setAddTime(new Date(1602727832036L - 100000000 * new Random().nextInt(10)));
            System.out.println(response.getUrl());
            //do something
            dynamicService.saveNotNull(dynamicInfo);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}