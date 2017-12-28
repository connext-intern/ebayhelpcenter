package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ManagerDao;
import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    管理员接口实现类
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    private static Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerDao managerDao;

    /**
     *
     * @return 查询出所有的一级二级菜单，并按照序列号排序
     */
    @Override
    public List<EbayFirstMenus> listAllTitle() {
        return managerDao.listAllTitle();
    }
    @Override
    public List<EbaySecondMenus> queryKeyWords(String keyword) {
        List<EbaySecondMenus> ebaySecondMenusList = this.managerDao.queryKeyWords(keyword);
        for (int i=0;i<ebaySecondMenusList.size();i++){
            String content = (ebaySecondMenusList.get(i).getContent()).substring(1,1);
            ebaySecondMenusList.get(i).setContent(content);
        }
        return this.managerDao.queryKeyWords(keyword);
    }

    @Override
    public EbaySecondMenus queryContent(int secondId) {
        return this.managerDao.queryContent(secondId);
    }

    @Override
    public Boolean deleteFirstMenu(int firstId) {
        log.info("ManagerServiceImpl is deleteFirstMenu start...");
        log.info("firstId-->{}",firstId);

        //1.判断一级菜单下是否存在二级菜单
        Boolean isFirstHasSecondMenus = this.managerDao.firstHasSecondMenus(firstId);
        if(isFirstHasSecondMenus){
            log.info("该一级菜单下存在二级菜单,准备删除二级菜单和一级菜单");

            Boolean b1 = this.managerDao.deleteSecondMenusInfoFromFirst(firstId);
            Boolean b2 = this.managerDao.deleteFirstMenuInfo(firstId);
            if(b1 && b2){
                log.info("两张表同时删除成功");
                return true;
            }else{
                log.info("b1或b2删除操作失败");
                return false;
            }
        }else{
            log.info("该一级菜单下不存在二级菜单,直接删除一级菜单即可");
            Boolean b = this.managerDao.deleteFirstMenuInfo(firstId);
            if(b){
                return true;
            }else {
                return false;
            }
        }
    }

    /*
        实现 删除二级菜单接口
     */
    @Override
    public Boolean deleteSecondMenu(int secondId) {
        log.info("ManagerServiceImpl is deleteSecondMenu start...");
        log.info("secondId-->{}",secondId);

        //1.判断该二级菜单是否存在
        Boolean hasSecondMenu = this.managerDao.hasSecondMenu(secondId);
        if(hasSecondMenu){
            log.info("存在该二级菜单,准备删除");
            Boolean isDeleteSecondMenu = this.managerDao.deleteSecondMenuInfo(secondId);
            if(isDeleteSecondMenu){
                log.info("该二级菜单 删除成功");
                return true;
            }else{
                log.info("该二级菜单 删除失败");
                return false;
            }
        }else{
            log.info("不存在该二级菜单");
            return false;
        }
    }

    @Override
    public void newFirstMenus(String firstTitle) {
        EbayFirstMenus ebayFirstMenus = new EbayFirstMenus();
        ebayFirstMenus.setFirstTitle(firstTitle);
        managerDao.newFirstMenus(ebayFirstMenus);
    }

    @Override
    public Boolean newSecondMenus(String secondTitle, String content, String html, int secondFirstId) {
        EbaySecondMenus ebaySecondMenus = new EbaySecondMenus();
        ebaySecondMenus.setSecondTitle(secondTitle);
        ebaySecondMenus.setContent(content);
        ebaySecondMenus.setHtml(html);
        ebaySecondMenus.setSecondFirstId(secondFirstId);

        if (managerDao.hasFirstMenus(secondFirstId)) {
            managerDao.newSecondMenus(ebaySecondMenus);
            return true;
        } else {
            return false;
        }
    }

        /**
         *  对一级菜单进行排序的方法
         *  By Zach Zhang
         *  @Param firstSerials
         */
        @Override
        public void sortFirstTitle(Integer[] firstSerials) {
            //先按序列号排序查出所有的一级菜单
            List<EbayFirstMenus> ebayFirstMenuses = managerDao.listAllFirstTitle();
            int i=0;
            //使用for循环更新序列号
            for(EbayFirstMenus ebayFirstMenus:ebayFirstMenuses) {
                ebayFirstMenus.setFirstSerial(firstSerials[i]);
                log.info(ebayFirstMenus.toString());
                managerDao.updateFirstSerialByID(ebayFirstMenus);
                i++;
            }
        }

    /**
     * 对二级菜单进行排序
     * By Zach Zhang
     * @param firstId
     * @param secondSerials
     */
    @Override
    public void sortSecondTitle(Integer firstId, Integer[] secondSerials) {
        //先根据一级id查出所有的二级菜单（按原来的序列号排序）
        List<EbaySecondMenus> ebaySecondMenuses = managerDao.listAllSencondTitleByFirstId(firstId);
        int i=0;
        //使用for循环更新序列号
        for(EbaySecondMenus ebaySecondMenus:ebaySecondMenuses) {
            ebaySecondMenus.setSecondSerial(secondSerials[i]);
            log.info(ebaySecondMenus.toString());
            managerDao.updateSecondSerialByID(ebaySecondMenus);
            i++;
        }
    }

    /**
     * 查找所有一级菜单的标题
     * @return
     */
    @Override
    public List<EbayFirstMenus> showAllFirst() {
        return managerDao.showAllFirst();
    }

    /**
     * 修改一级标题
     * @param firstSerial
     * @param title
     */
    @Override
    public void updateFirst(int firstSerial, String title) {
        managerDao.updateFirst(firstSerial,title);
    }
}
