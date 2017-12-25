package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {
    List<EbayFirstMenus> queryAll();
}
