package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:55
 */
@Repository
public interface ArticleDao {
    /**
     * 根据用户输入的关键字查询
     * @param keyword
     * @return
     */
    public List<EbaySecondMenus> queryKeyWords(@Param("keyword") String keyword);

    /**
     * 查询根据关键词查询的条数，用于分页查询
     * @param keyword
     * @return
     */
    public int countByKeyword(@Param("keyword") String keyword);
    /**
     * 根据标题查询内容
     * @param secondId
     * @return
     */
    public EbaySecondMenus queryContent(int secondId);
}
