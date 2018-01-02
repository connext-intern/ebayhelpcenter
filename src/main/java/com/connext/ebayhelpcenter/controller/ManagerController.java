package com.connext.ebayhelpcenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "/listAllMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<EbayFirstMenus> listAllTitle(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");//解决远端跨域访问不允许
        List<EbayFirstMenus> list = managerService.listAllTitle();
        return list;
    }

    /**
     * 删除一级标题，需判断是否存在二级标题
     * @param firstId
     * @return
     */
    @RequestMapping(value = "/deleteFirstMenu/{firstId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteFirstMenu(@PathVariable("firstId") int firstId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
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

    /**
     * 删除二级标题对象
     * @param secondId
     * @return
     */
    @RequestMapping(value = "/deleteSecondMenu/{secondId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteSecondMenu(@PathVariable("secondId") int secondId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
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

    /**
     * 新增一级菜单
     * @param firstTitle 菜单标题
     * @return
     */
    @RequestMapping(value = "newFirstMenus", method = RequestMethod.POST)
    @ResponseBody
    public String newFirstMenus(String firstTitle){
        managerService.newFirstMenus(firstTitle);
        return "ok";
    }

    /**
     * 新增二级标题及其内容
     * @param secondTitle 菜单标题
     * @param content 纯文本内容
     * @param html 内容的html
     * @param secondFirstId 对应一级菜单的编号
     * @return
     */
    @RequestMapping(value = "newSecondMenus", method = RequestMethod.POST)
    @ResponseBody
    public String newSecondMenus(String secondTitle, String content, String html, int secondFirstId) {
        Boolean isNewSecondMenus = managerService.newSecondMenus(secondTitle, content, html, secondFirstId);
        if (isNewSecondMenus) {
            return "newSecondMenusSuccess";
        }
        return "newSecondMenusFail";
    }
    /**
     * 给一级菜单进行排序
     * ByZach Zhang
     * @param firstSerials
     * @return
     */
    @RequestMapping("/sortFirstTitle/{firstSerials}")
    @ResponseBody
    public String sortFirstTitle(@PathVariable("firstSerials") Integer[] firstSerials){
        log.info("传进来的新序列是：{}",firstSerials);
        managerService.sortFirstTitle(firstSerials);
        return "success";
    }
    /**
     * 给二级菜单进行排序
     * ByZach Zhang
     * @param secondSerials,firstId
     * @return
     */
    @RequestMapping("/sortSecondTitle/{firstId}/{secondSerials}")
    @ResponseBody
    public String sortSecondTitle(@PathVariable("firstId") Integer firstId,@PathVariable("secondSerials") Integer[] secondSerials){
        log.info("传进来的二级菜单们的一级菜单id和新的序列分别是：{},{}",firstId,secondSerials);
        managerService.sortSecondTitle(firstId,secondSerials);
        return "success";
    }

    /**
     * 给一级菜单进行排序测试
     * By Zach Zhang
     * @return
     */
    @RequestMapping("/sortFirstTitleTest")
    @ResponseBody
    public String sortFirstTitleTest(){
        //此处写死一个数组进行测试，
        Integer[] firstSerials={2,1,4,3,5,6};
        log.info("传进来的新序列是：{}",firstSerials);
        managerService.sortFirstTitle(firstSerials);
        return "success";
    }
    /**
     * 给二级菜单进行排序测试
     * ByZach Zhang
     * @return
     */
    @RequestMapping("/sortSecondTitleTest")
    @ResponseBody
    public String sortSecondTitleTest(){
        int firstId = 2;
        Integer[] secondSerials = {1,2,3,5,6,7,4};
        log.info("传进来的二级菜单们的一级菜单id和新的序列分别是：{},{}",firstId,secondSerials);
        managerService.sortSecondTitle(firstId,secondSerials);
        return "success";
    }

    /**
     * 修改一级菜单标题 zhangyang
     */
    @RequestMapping(value = "/updateFirst",method = RequestMethod.POST)
    @ResponseBody
    public String updateFirst(Integer firstSerial,String firstTitle,Integer firstId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        String str = managerService.updateFirst(firstSerial,firstTitle,firstId);
        return str;
    }


    /**
     * 查询所有一级菜单标题
     * @return
     */
    @RequestMapping(value = "showAllFirst",method = RequestMethod.GET)
    @ResponseBody
    public List<EbayFirstMenus> showAllFirst(){
        List<EbayFirstMenus> ebayFirstMenu = managerService.showAllFirst();
        return ebayFirstMenu;
    }

  

    /**
     *  通过一级菜单的id（second_first_id）查询二级菜单对应的标题
     */
    @RequestMapping("listSecondTitle")
    @ResponseBody
    public List<EbaySecondMenus> listSecondTitle(int id) {
        List<EbaySecondMenus> list = managerService.findTitleById(id);
        return list;
    }

    /**
     *  通过二级菜单的id（second_id）查询二级菜单对应的内容
     */
    @RequestMapping("listSecondContent")
    @ResponseBody
    public EbaySecondMenus listSecondContent(int id) {
        EbaySecondMenus list = managerService.findContentById(id);
        return list;
    }

    /**
     * 修改二级菜单标题和内容
     */
    @RequestMapping(value = "updateSecond",method = RequestMethod.PUT)
    @ResponseBody
    public String updateSecond(Integer id,String title,String content,String html,Integer serial,HttpServletRequest request){
        managerService.updateSecondTitle(id,title);
        managerService.updateSecondContent(id,content);
        managerService.updateSecondHtml(id,html);
        managerService.updateSecondSerial(id,serial);
        return "success to update";
    }

}
