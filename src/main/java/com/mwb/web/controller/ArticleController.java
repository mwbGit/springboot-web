package com.mwb.web.controller;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.ArticleInfo;
import com.mwb.web.model.PraiseInfo;
import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.ApiResult;
import com.mwb.web.model.common.WebLogin;
import com.mwb.web.model.query.ArticleQuery;
import com.mwb.web.service.ArticleService;
import com.mwb.web.service.PraiseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PraiseService praiseService;

    @RequestMapping("/search")
    public ApiResult search(ArticleQuery query) {
        query.setOrder("addTime");
        PageInfo<ArticleInfo> pageInfo = articleService.search(query);
        return ApiResult.success(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }

    @RequestMapping("/hot")
    public ApiResult hot() {
        List<ArticleInfo> pageInfo = articleService.hot(15);
        return ApiResult.success(pageInfo);
    }

    @RequestMapping("/{id}.html")
    public ModelAndView detail(@PathVariable("id") long id) {
        ArticleInfo articleInfo = articleService.selectByKey(id);
        if (articleInfo != null) {
            articleInfo.setViewNum(articleInfo.getViewNum() + 1);
            articleService.updateNotNull(articleInfo);
        }
        ModelAndView modelAndView = new ModelAndView("/article_detail");
        modelAndView.addObject("article", articleInfo);
        return modelAndView;
    }

    @RequestMapping("/praise")
    public ApiResult praise(UserInfo user, @RequestParam("id") long id) {
        ArticleInfo articleInfo = articleService.selectByKey(id);
        boolean result = false;
        if (articleInfo != null && user != null) {
            result = praiseService.savePraiseInfo(new PraiseInfo(id, user.getId(), 1));
            if (result) {
                articleInfo.setPraiseNum(articleInfo.getPraiseNum() + 1);
                articleService.updateNotNull(articleInfo);
            }
        }
        log.info("ArticleController.praise id={},result={},user={}", id, result, user);
        return ApiResult.success(result);
    }


    /**
     * admin 接口
     *
     * @return
     */
    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/info")
    public ModelAndView info(@RequestParam("id") long id) {
        ArticleInfo article = articleService.selectByKey(id);
        ModelAndView modelAndView = new ModelAndView("/admin/article_info");
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/delete")
    public ApiResult delete(@RequestParam("id") long id) {
        articleService.delete(id);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/save")
    public ApiResult saveOrUpdate(ArticleInfo articleInfo) {
        if (StringUtils.isBlank(articleInfo.getImage())) {
            return ApiResult.failed("请上传主图");
        }
        articleService.saveOrUpdate(articleInfo);
        return ApiResult.success();
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/praise/add")
    public ApiResult praiseAdd(@RequestParam("id") long id) {
        ArticleInfo articleInfo = articleService.selectByKey(id);
        if (articleInfo != null) {
            int num = new Random().nextInt(100);
            articleInfo.setPraiseNum(articleInfo.getPraiseNum() + num);
            articleInfo.setViewNum(articleInfo.getViewNum() + (num + new Random().nextInt(50)));
            articleService.updateNotNull(articleInfo);
        }
        return ApiResult.success();
    }
}