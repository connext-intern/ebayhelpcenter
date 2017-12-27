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
}
