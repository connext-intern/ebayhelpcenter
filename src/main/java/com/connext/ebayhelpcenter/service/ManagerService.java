package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;

import java.util.List;

public interface ManagerService {
    /*
        删除一级标题
     */
    Boolean deleteFirstMenu(String firstId);

    /*
        删除二级菜单
     */
    Boolean deleteSecondMenu(String secondId);
}
