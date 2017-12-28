package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;

import java.util.List;

/*
    管理员接口类
 */
public interface ManagerService {
    /**
     *
     * @return 查询出所有的一级二级菜单，并按照序列号排序
     */
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

    /**
     * 增加一级菜单
     * @param firstTitle 菜单标题
     */
    void newFirstMenus(String firstTitle);

    /**
     * 增加二级菜单及其内容
     * @param secondTitle 菜单标题
     * @param content 纯文本内容
     * @param html 内容的html
     * @param secondFirstId 对应一级菜单的编号
     */
    Boolean newSecondMenus(String secondTitle,String content,String html,int secondFirstId);
}
