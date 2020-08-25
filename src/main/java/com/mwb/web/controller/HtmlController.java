package com.mwb.web.controller;

import com.mwb.web.model.UserInfo;
import com.mwb.web.model.common.WebLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@Controller
public class HtmlController {

    @RequestMapping("/html/{page}")
    public ModelAndView page(UserInfo userInfo, @PathVariable("page") String page) {
        page = page.substring(0, page.contains(".") ? page.indexOf(".") : page.length());
        ModelAndView modelAndView = new ModelAndView("/" + page);
        if (userInfo != null) {
            modelAndView.addObject("userName", userInfo.getName());
            modelAndView.addObject("avatar", userInfo.getAvatar());
            modelAndView.addObject("admin", userInfo.isAdmin());
            modelAndView.addObject("unRead", userInfo.getUnReadMsgNum() > 0);
        }
        return modelAndView;
    }

    @WebLogin(option = WebLogin.Option.ADMIN)
    @RequestMapping("/admin/{page}")
    public ModelAndView admin(UserInfo userInfo, @PathVariable("page") String page) {
        page = page.substring(0, page.contains(".") ? page.indexOf(".") : page.length());
        ModelAndView modelAndView = new ModelAndView("/admin/" + page);
        if (userInfo != null && userInfo.getIdentity() == 1) {
            modelAndView.addObject("userName", userInfo.getName());
            modelAndView.addObject("avatar", userInfo.getAvatar());
            modelAndView.addObject("admin", userInfo.isAdmin());
            modelAndView.addObject("unRead", userInfo.getUnReadMsgNum() > 0);
            return modelAndView;
        }
        return null;
    }

    @RequestMapping("/index.html")
    public ModelAndView index(UserInfo userInfo) {
        ModelAndView modelAndView = new ModelAndView("/index");
        if (userInfo != null) {
            modelAndView.addObject("userName", userInfo.getName());
            modelAndView.addObject("avatar", userInfo.getAvatar());
            modelAndView.addObject("admin", userInfo.isAdmin());
            modelAndView.addObject("unRead", userInfo.getUnReadMsgNum() > 0);
        }
        return modelAndView;
    }

    @RequestMapping("/baidu_verify_VuY12dJa6l.html")
    public ModelAndView baidu() {
        ModelAndView modelAndView = new ModelAndView("/baidu_verify_VuY12dJa6l");
        return modelAndView;
    }
}
