package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.es.ArticleEO;
import com.mwb.web.es.ArticleEsQuery;
import com.mwb.web.es.OpinionInfoEsService;
import com.mwb.web.model.ArticleInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.query.ArticleQuery;
import com.mwb.web.service.ArticleService;
import com.mwb.web.service.CommentService;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/3
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private UserInfoService loginUserService;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private OpinionInfoEsService opinionInfoEsService;


    @RequestMapping("/test11")
    public ApiResult test11() {
        ArticleEO articleEO = new ArticleEO();
        articleEO.setId(new Random().nextLong());
        articleEO.setTitle("标题" + articleEO.getId());
        articleEO.setAddTime(new Date());
        articleEO.setCompany("公司2");
        opinionInfoEsService.save(articleEO);
        ArticleEsQuery query = new ArticleEsQuery();
        query.setPageSize(1);
        query.setPage(1);
        query.setSortKey("id");
        query.setSortDesc(true);
        Map<String, Object> result = new HashMap<>();
        query.setGroupKey("company");
        result.put("group", opinionInfoEsService.normalGroupByEsQuery(query));
        result.put("search", opinionInfoEsService.searchNormalPage(query));
        return ApiResult.success(result);
    }

    /**
     * 处理无图片文章
     * @return
     */
    @ResponseBody
    @RequestMapping("/test3")
    public ApiResult test3() {
        ArticleQuery query = new ArticleQuery();
        query.setPageSize(300);
        PageInfo<ArticleInfo> pageInfo = articleService.search(query);
        for (ArticleInfo articleInfo : pageInfo.getList()) {
            if (StringUtils.isBlank(articleInfo.getImage()) && StringUtils.isNotBlank(articleInfo.getBody())) {
                String str = "";
                try {
                    str = articleInfo.getBody().substring(articleInfo.getBody().indexOf("https"), articleInfo.getBody().indexOf("\" width="));
                    if (StringUtils.isNotBlank(str)) {
                        System.out.println(articleInfo.getId() + "-----"  + str);
                    }
                } catch (Exception e) {

                }
                if (StringUtils.isBlank(str)){
                    str = "https://pethomeweb.oss-cn-beijing.aliyuncs.com/uploads/20200808/42f5f123f9dc09f3c6e72771413f6534.png";
                }
                articleInfo.setImage(str.trim());
                articleService.updateNotNull(articleInfo);
            }
        }
        return ApiResult.failed("");
    }
    @ResponseBody
    @RequestMapping("/test2")
    public ApiResult test2() {
        Random random =  new Random();
        List<ArticleInfo> list = articleService.selectAll();
        for (ArticleInfo articleInfo : list) {
            DateTime dateTime = new DateTime();
            dateTime = dateTime.minusDays(random.nextInt(3));
            dateTime = dateTime.minusHours(random.nextInt(15));
            dateTime = dateTime.minusMinutes(random.nextInt(40));
            dateTime = dateTime.minusHours(random.nextInt(12));
            dateTime = dateTime.minusMillis(random.nextInt(50));
            articleInfo.setAddTime(dateTime.toDate());
            articleInfo.setUpdateTime(new Date());
            articleInfo.setPraiseNum(random.nextInt(300));
            articleService.updateNotNull(articleInfo);
        }
        List<DynamicInfo> list2 = dynamicService.selectAll();
        for (DynamicInfo articleInfo : list2) {
            DateTime dateTime = new DateTime();
            dateTime = dateTime.minusDays(random.nextInt(3));
            dateTime = dateTime.minusHours(random.nextInt(15));
            dateTime = dateTime.minusMinutes(random.nextInt(40));
            dateTime = dateTime.minusHours(random.nextInt(12));
            dateTime = dateTime.minusMillis(random.nextInt(50));
            articleInfo.setAddTime(dateTime.toDate());
            articleInfo.setUpdateTime(new Date());
            if (articleInfo.getUserId() < 21) {
                articleInfo.setPraiseNum(random.nextInt(300));
            }
            dynamicService.updateNotNull(articleInfo);
        }
        return ApiResult.success(1);
    }

    public static void main(String[] args) {

//        StringBuffer sb = new StringBuffer();
//        for (int i = 1032; i < 1226; i++) {
//            sb.append(String.format("http://www.maomihome.com/article/%d.html", i));
//            sb.append("\n");
//        }
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("text/plain");
//        headers.setContentType(type);
//        HttpEntity<String> formEntity = new HttpEntity<>(sb.toString(), headers);
//        RestTemplate restTemplate = new RestTemplate();
//        String s = restTemplate.postForEntity("http://data.zz.baidu.com/urls?site=www.maomihome.com&token=k2n5HRW5orlGqayp", formEntity, String.class).getBody();
//        System.out.println(s);


    }


}
