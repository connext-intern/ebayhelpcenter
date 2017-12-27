package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerDao {
    public List<EbayFirstMenus> listAllTitle();
}
