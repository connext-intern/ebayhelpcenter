package com.connext.ebayhelpcenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ArticleService;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.connext.ebayhelpcenter.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @date 2017/12/28 9:24
 * 用户查看 帮助中心 页面 类
 */
@Controller
@RequestMapping("/user")
public class ArticleController {
    private static Logger log = LoggerFactory.getLogger(ManagerController.class);
    @Autowired
    private ArticleService userService;

    @Autowired
    private ManagerService managerService;

    /**
     * 显示所有菜单标题
     * @param
     * @return
     */
    @RequestMapping(value = "/listAllTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult listAllTitle(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("UserController is listAllTitle start...");
        List<EbayFirstMenus> allTitleLists = managerService.listAllTitle();
        log.info("allTitleLists.size()-->"+allTitleLists.size());
        return new JsonResult(allTitleLists);
    }

    @RequestMapping(value = "/queryHtmlBySecondId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult queryHtmlBySecondId(HttpServletResponse response, @RequestParam("secondId") int secondId){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("UserController is queryHtmlBySecondId start...");

        String html = this.userService.queryHtmlBySecondId(secondId);
        log.info("html-->"+html);

        return new JsonResult(html);
    }
}
