package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;

import java.util.List;

/*
    管理员接口类
 */
public interface ManagerService {
    /*
       删除一级标题
    */
    Boolean deleteFirstMenu(int firstId);

    /*
        删除二级菜单
     */
    Boolean deleteSecondMenu(int secondId);
}
