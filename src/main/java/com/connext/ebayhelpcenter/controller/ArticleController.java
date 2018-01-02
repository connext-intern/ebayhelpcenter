package com.connext.ebayhelpcenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ArticleService;

import com.connext.ebayhelpcenter.util.JsonResult;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:24
 */
@Controller
@RequestMapping("/user")
public class ArticleController {
    private static Logger log = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService userService;

    /**
     * 根据关键词查询标题和内容列表
     * @param keyword
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryByKeyWords", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult queryByKeyWords(String keyword, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("keyword-->" + keyword);
        PageHelper.startPage(1, 10);
        List<EbaySecondMenus> list = this.userService.queryKeyWords(keyword);
        int countByKeyword = this.userService.countByKeyword(keyword);
        log.info("countByKeyword-->" + countByKeyword);
        for (EbaySecondMenus e : list) {
            log.info(e.getContent());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);
        jsonObject.put("countByKeyword", countByKeyword);
        return new JsonResult(jsonObject);
    }

    /**
     * 分页查询
     * @param keyword 关键词
     * @param pageno 页码
     * @param pagesize 每页显示的条数
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryByPage", produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public JsonResult queryByPage(String keyword, int pageno, int pagesize, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int countByKeyword = this.userService.countByKeyword(keyword);
        PageHelper.startPage(pageno, pagesize);
        List<EbaySecondMenus> list = this.userService.queryKeyWords(keyword);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);
        jsonObject.put("countByKeyword", countByKeyword);
        return new JsonResult(jsonObject);
    }

    /**
     * 根据标题的id查询具体内容
     * @param secondId 标题的编号
     * @param response
     * @return
     */
    @RequestMapping("/queryByTitle")
    @ResponseBody
    public JsonResult queryContent(int secondId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        EbaySecondMenus ebaySecondMenus = this.userService.queryContent(secondId);
        log.info("ID-->" + secondId);
        log.info("content-->" + ebaySecondMenus.getContent());
        return new JsonResult(ebaySecondMenus);
    }
}
