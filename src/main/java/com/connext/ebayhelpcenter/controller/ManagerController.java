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

    /**
     * @return 将二级菜单封装到一级菜单中，返回一级菜单的list
     */
    @RequestMapping(value = "/listAllMenus", method = RequestMethod.GET)
    @ResponseBody
    public List<EbayFirstMenus> listAllTitle() {
        List<EbayFirstMenus> list = managerService.listAllTitle();
        return list;
    }

    /**
     * @param keyword
     * @return
     */
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
