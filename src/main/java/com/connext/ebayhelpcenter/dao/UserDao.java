package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:55
 */
@Repository
public interface UserDao {
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
}
