package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbaySecondMenus;

import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:24
 */
public interface UserService {
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

    /**
     * 根据二级菜单id 查找正文内容
     * @param secondId
     * @return
     */
    String queryHtmlBySecondId(int secondId);
}
