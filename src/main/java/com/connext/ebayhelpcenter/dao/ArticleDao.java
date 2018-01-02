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

}