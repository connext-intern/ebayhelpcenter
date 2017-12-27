package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;

import java.util.List;

/*
    管理员接口类
 */
public interface ManagerService {
    public List<EbayFirstMenus> listAllTitle();
    /**
     * 根据用户输入的关键字查询
     * @param keyword
     * @return
     */
    public List<EbaySecondMenus> queryKeyWords(String keyword);
    /**
     * 根据标题查询内容
     * @param secondId
     * @return
     */
    public EbaySecondMenus queryContent(int secondId);

    /*
       删除一级标题
    */
    Boolean deleteFirstMenu(int firstId);

    /*
        删除二级菜单
     */
    Boolean deleteSecondMenu(int secondId);
}
