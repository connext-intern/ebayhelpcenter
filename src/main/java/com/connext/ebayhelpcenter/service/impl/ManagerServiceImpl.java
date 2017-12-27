package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ManagerDao;
import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private static Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerDao managerDao;

    @Override
    public List<EbayFirstMenus> listAllTitle() {
        return managerDao.listAllTitle();
    }
    @Override
    public List<EbaySecondMenus> queryKeyWords(String keyword) {
        List<EbaySecondMenus> ebaySecondMenusList = this.managerDao.queryKeyWords(keyword);
        for (int i=0;i<ebaySecondMenusList.size();i++){
            String content = (ebaySecondMenusList.get(i).getContent()).substring(1,1);
            ebaySecondMenusList.get(i).setContent(content);
        }
        return this.managerDao.queryKeyWords(keyword);
    }

    @Override
    public EbaySecondMenus queryContent(int secondId) {
        return this.managerDao.queryContent(secondId);
    }
}
