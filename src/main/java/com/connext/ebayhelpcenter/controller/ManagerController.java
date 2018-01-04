package com.connext.ebayhelpcenter.controller;


import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.connext.ebayhelpcenter.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理员对菜单的增删改查操作类
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
    @RequestMapping(value = "/deleteFirstMenu/{firstId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JsonResult deleteFirstMenu(@PathVariable("firstId") int firstId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("ManagerController is deleteFirstMenu start...");

        //删除一级菜单
        Boolean  isDeleteFirstMenu = this.managerService.deleteFirstMenu(firstId);
        if(!isDeleteFirstMenu){
            log.info("firstId:{},deleteFirstMenu fail",firstId);
        }
        return new JsonResult();
    }

    /**
     * 删除二级标题对象
     * @param secondId
     * @return
     */
    @RequestMapping(value = "/deleteSecondMenu/{secondId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JsonResult deleteSecondMenu(@PathVariable("secondId") int secondId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("ManagerController is deleteSecondMenu start...");

        Boolean isDeleteSecondMenu = this.managerService.deleteSecondMenu(secondId);

        if(!isDeleteSecondMenu){
            log.info("secondId:{},deleteSecondMenu fail",secondId);
        }
        return new JsonResult();
    }

    /**
     * 新增一级菜单
     * @param firstTitle 菜单标题
     * @return
     */
    @RequestMapping(value = "saveFirstMenus", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveFirstMenus(HttpServletResponse response, String firstTitle){
        response.setHeader("Access-Control-Allow-Origin", "*");
        managerService.saveFirstMenus(firstTitle);
        return new JsonResult();
    }

    /**
     * 新增二级标题及其内容
     * @param secondTitle 菜单标题
     * @param content 纯文本内容
     * @param html 内容的html
     * @param secondFirstId 对应一级菜单的编号
     * @return
     */
    @RequestMapping(value = "saveSecondMenus", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveSecondMenus(HttpServletResponse response, String secondTitle, String content, String html, int secondFirstId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        managerService.saveSecondMenus(secondTitle, content, html, secondFirstId);
        return new JsonResult();
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
     * 修改一级菜单标题 zhangyang
     */
    @RequestMapping(value = "/updateFirst",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateFirst(Integer firstSerial,String firstTitle,Integer firstId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        managerService.updateFirst(firstSerial,firstTitle,firstId);
        return new JsonResult();
    }


    /**
     * 查询所有一级菜单标题
     * @return
     */
    @RequestMapping(value = "showAllFirst",method = RequestMethod.POST)
    @ResponseBody
    public List<EbayFirstMenus> showAllFirst(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<EbayFirstMenus> ebayFirstMenu = managerService.showAllFirst();
        return ebayFirstMenu;
    }



    /**
     *  通过一级菜单的id（second_id）查询二级菜单对应的标题
     */

    @RequestMapping("/listSecondTitle/{firstId}")
    @ResponseBody
    public EbaySecondMenus listSecondTitle(@PathVariable("firstId") Integer firstId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        EbaySecondMenus list = managerService.findTitleById(firstId);

        return list;
    }

    /**
     *  通过二级菜单的id（second_id）查询二级菜单对应的内容
     */
    @RequestMapping(value = "listSecondContent",method = RequestMethod.POST)
    @ResponseBody
    public EbaySecondMenus listSecondContent(int id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        EbaySecondMenus list = managerService.findContentById(id);
        return list;
    }

    /**
     * 修改二级菜单标题和内容
     */
    @RequestMapping(value = "updateSecond", method = RequestMethod.POST)
    @ResponseBody
    public String updateSecond(Integer id, String title, String content, String html, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (title != null) {
            managerService.updateSecondTitle(id, title);
        }
        if (content != null) {
            managerService.updateSecondContent(id, content);
        }
        if (html != null) {
            managerService.updateSecondHtml(id, html);
        }

        return "success to update";
    }

}
