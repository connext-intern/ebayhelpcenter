package com.connext.ebayhelpcenter.controller;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String test(){
        log.info("test");
        return "success";
    }


}
