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
 * ����Ա�Բ˵�����ɾ�Ĳ������
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    private static Logger log = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    /**
     * @return �������˵���װ��һ���˵��У�����һ���˵���list
     */
    @RequestMapping(value = "/listAllMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<EbayFirstMenus> listAllTitle(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");//���Զ�˿�����ʲ�����
        List<EbayFirstMenus> list = managerService.listAllTitle();
        return list;
    }

    /**
     * ɾ��һ�����⣬���ж��Ƿ���ڶ�������
     * @param firstId
     * @return
     */
    @RequestMapping(value = "/deleteFirstMenu/{firstId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JsonResult deleteFirstMenu(@PathVariable("firstId") int firstId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.info("ManagerController is deleteFirstMenu start...");

        //ɾ��һ���˵�
        Boolean  isDeleteFirstMenu = this.managerService.deleteFirstMenu(firstId);
        if(!isDeleteFirstMenu){
            log.info("firstId:{},deleteFirstMenu fail",firstId);
        }
        return new JsonResult();
    }

    /**
     * ɾ�������������
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
     * ����һ���˵�
     * @param firstTitle �˵�����
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
     * �����������⼰������
     * @param secondTitle �˵�����
     * @param content ���ı�����
     * @param html ���ݵ�html
     * @param secondFirstId ��Ӧһ���˵��ı��
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
     * ��һ���˵���������
     * ByZach Zhang
     * @param firstSerials
     * @return
     */
    @RequestMapping("/sortFirstTitle/{firstSerials}")
    @ResponseBody
    public String sortFirstTitle(@PathVariable("firstSerials") Integer[] firstSerials){
        log.info("���������������ǣ�{}",firstSerials);
        managerService.sortFirstTitle(firstSerials);
        return "success";
    }
    /**
     * �������˵���������
     * ByZach Zhang
     * @param secondSerials,firstId
     * @return
     */
    @RequestMapping("/sortSecondTitle/{firstId}/{secondSerials}")
    @ResponseBody
    public String sortSecondTitle(@PathVariable("firstId") Integer firstId,@PathVariable("secondSerials") Integer[] secondSerials){
        log.info("�������Ķ����˵��ǵ�һ���˵�id���µ����зֱ��ǣ�{},{}",firstId,secondSerials);
        managerService.sortSecondTitle(firstId,secondSerials);
        return "success";
    }


    /**
     * �޸�һ���˵����� zhangyang
     */
    @RequestMapping(value = "/updateFirst",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateFirst(Integer firstSerial,String firstTitle,Integer firstId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        managerService.updateFirst(firstSerial,firstTitle,firstId);
        return new JsonResult();
    }


    /**
     * ��ѯ����һ���˵�����
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
     *  ͨ��һ���˵���id��second_id����ѯ�����˵���Ӧ�ı���
     */

    @RequestMapping("/listSecondTitle/{firstId}")
    @ResponseBody
    public EbaySecondMenus listSecondTitle(@PathVariable("firstId") Integer firstId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        EbaySecondMenus list = managerService.findTitleById(firstId);

        return list;
    }

    /**
     *  ͨ�������˵���id��second_id����ѯ�����˵���Ӧ������
     */
    @RequestMapping(value = "listSecondContent",method = RequestMethod.POST)
    @ResponseBody
    public EbaySecondMenus listSecondContent(int id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        EbaySecondMenus list = managerService.findContentById(id);
        return list;
    }

    /**
     * �޸Ķ����˵����������
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
