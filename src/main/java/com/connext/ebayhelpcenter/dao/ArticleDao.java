package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 查看一二级菜单 正文持久层
 */
@Repository
public interface ArticleDao {
    /**
     * 根据二级菜单id查找正文内容
     * @param secondId
     * @return
     */
    String queryHtmlInfoBySecondId(@Param("secondId") int secondId);

    /**
     * 根据用户输入的关键字查询
     * @param keyword
     * @return
     */
    List<EbaySecondMenus> queryKeyWords(@Param("keyword") String keyword);

    /**
     * 查询根据关键词查询的条数，用于分页查询
     * @param keyword
     * @return
     */
    int countByKeyword(@Param("keyword") String keyword);

    /**
     * 根据二级id查找一级菜单标题
     * @param secondId
     * @return
     */
    String queryFirstTitleInfoBySecondId(@Param("secondId") int secondId);
}
