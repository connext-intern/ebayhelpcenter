package com.connext.ebayhelpcenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.UserService;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.connext.ebayhelpcenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:24
 * 用户查看 帮助中心 页面 类
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(ManagerController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/queryByKeyWords", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON queryByKeyWords(String keyword, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("keyword-->"+keyword);
        List<EbaySecondMenus> list = this.userService.queryKeyWords(keyword);
        for(EbaySecondMenus e:list){
            log.info(e.getContent());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        return jsonObject;
    }

    @RequestMapping("/queryByTitle")
    @ResponseBody
    public  EbaySecondMenus queryContent(int secondId) {
        EbaySecondMenus ebaySecondMenus = this.userService.queryContent(secondId);
        return ebaySecondMenus;
    }

    /**
     * 显示所有菜单标题
     * @param
     * @return
     */
    @RequestMapping(value = "/listAllTitle", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<EbayFirstMenus> listAllTitle(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("UserController is listAllTitle start...");
        List<EbayFirstMenus> allTitleLists = managerService.listAllTitle();
        log.info("allTitleLists.size()-->"+allTitleLists.size());
        return allTitleLists;
    }

    @RequestMapping("/queryHtmlBySecondId")
    @ResponseBody
    public String queryHtmlBySecondId(){
        log.info("UserController is queryHtmlBySecondId start...");
        //log.info("secondId-->"+secondId);

        String html = this.userService.queryHtmlBySecondId(11);
        log.info("html-->"+html);
        return html;
    }
}
