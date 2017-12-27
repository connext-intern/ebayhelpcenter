package com.connext.ebayhelpcenter.controller;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
    管理员对菜单的增删改查操作类
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    private static Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        log.info("test");
        return "success";
    }

    /**
     * @return 将二级菜单的标题封装到一级菜单中，返回一级菜单的list
     */
    @RequestMapping(value = "/listAllTitle", method = RequestMethod.GET)
    @ResponseBody
    public List<EbayFirstMenus> listAllTitle() {
        List<EbayFirstMenus> list = managerService.listAllTitle();
        return list;
    }
    @RequestMapping("/bb")
    @ResponseBody
    public List<EbaySecondMenus> queryKeyWords(String keyword){
        log.info("keyword-->"+keyword);
        List<EbaySecondMenus> list = this.managerService.queryKeyWords(keyword);
        for(EbaySecondMenus e:list){
            log.info(e.getContent());
        }
        return list;
    }
    @RequestMapping("/cc")
    @ResponseBody
    public  EbaySecondMenus queryContent(int secondId){
        EbaySecondMenus ebaySecondMenus = this.managerService.queryContent(secondId);
        return  ebaySecondMenus;
    }

}
