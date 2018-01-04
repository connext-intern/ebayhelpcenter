package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbaySecondMenus;

import java.util.List;

/**
 * @date 2017/12/28 9:24
 */
public interface ArticleService {
    /**
     * 根据二级菜单id 查找正文内容
     * @param secondId
     * @return
     */
    String queryHtmlBySecondId(int secondId);

    /**
     * 用户根据关键字查询内容
     *
     * @param keyword
     * @return
     */
    List<EbaySecondMenus> queryKeyWords(String keyword);

    /**
     * 查询根据关键词查询的条数，用于分页查询
     *
     * @param keyword
     * @return
     */
    int countByKeyword(String keyword);
}
