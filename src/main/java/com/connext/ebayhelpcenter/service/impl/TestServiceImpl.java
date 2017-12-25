package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.TestDao;
import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<EbayFirstMenus> queryAll() {
        return this.testDao.queryAll();
    }
}
