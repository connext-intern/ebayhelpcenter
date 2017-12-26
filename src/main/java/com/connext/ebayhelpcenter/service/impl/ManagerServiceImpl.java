package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ManagerDao;
import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private static Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerDao managerDao;

    @Override
    public Boolean deleteFirstMenu(String firstId) {
        log.info("ManagerServiceImpl is deleteFirstMenu start...");
        log.info("firstId-->{}",firstId);

        //1.判断一级菜单下是否存在二级菜单
        Boolean isFirstHasSecondMenus = this.managerDao.firstHasSecondMenus(firstId);
        if(isFirstHasSecondMenus){
            log.info("该一级菜单下存在二级菜单,准备删除二级菜单和一级菜单");
            return true;
        }else{
            log.info("该一级菜单下不存在二级菜单");
            return false;
        }
    }

    /*
            实现 删除二级菜单接口
         */
    @Override
    public Boolean deleteSecondMenu(String secondId) {
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
