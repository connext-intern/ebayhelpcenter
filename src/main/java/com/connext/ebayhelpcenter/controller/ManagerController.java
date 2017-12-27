package com.connext.ebayhelpcenter.controller;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /*
        删除一级标题，需判断是否存在二级标题
        @Param("firstId") 一级菜单id
     */
    @RequestMapping(value = "/deleteFirstMenu/{firstId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFirstMenu(@PathVariable("firstId") int firstId){
        log.info("ManagerController is deleteFirstMenu start...");
        log.info("firstId-->{}",firstId);

        //删除一级菜单
        Boolean  isDeleteFirstMenu = this.managerService.deleteFirstMenu(firstId);
        if(isDeleteFirstMenu){
            log.info("一级菜单删除成功");
            return "deleteFirstMenuSuccess";
        }else{
            log.info("一级菜单删除失败");
            return "deleteFirstMenuFail";
        }
    }

    /*
       删除二级标题对象
       @Param("secondId") 二级菜单id
    */
    @RequestMapping(value = "/deleteSecondMenu/{secondId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteSecondMenu(@PathVariable("secondId") int secondId){
        log.info("ManagerController is deleteSecondMenu start...");
        log.info("secondId-->{}",secondId);

        Boolean isDeleteSecondMenu = this.managerService.deleteSecondMenu(secondId);
        log.info("删除二级菜单对象-->"+isDeleteSecondMenu);

        if(isDeleteSecondMenu){
            log.info("deleteSecondMenu success");
            return "deleteSecondMenuSuccess";
        }else{
            log.info("deleteSecondMenu fail");
            return "deleteSecondMenuFail";
        }
    }



}
