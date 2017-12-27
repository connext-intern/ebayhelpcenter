package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;

import java.util.List;

public interface ManagerService {
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
