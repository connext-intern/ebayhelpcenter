package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ManagerDao {
    /*
        查询是否存在该二级菜单
        @Param("secondId") 二级菜单id
        @return boolean
     */
    Boolean hasSecondMenu(@Param("secondId") int secondId);

    /*
        删除该二级菜单
        @Param("secondId") 二级菜单id
        @return boolean
     */
    Boolean deleteSecondMenuInfo(@Param("secondId") int secondId);

    /*
        查询一级菜单下是否存在二级菜单
        @Param("firstId") 一级菜单id
        @return boolean
     */
    Boolean firstHasSecondMenus(@Param("firstId") int firstId);

    /*
        删除一级菜单下的所有二级菜单
     */
    @Transactional
    Boolean deleteSecondMenusInfoFromFirst(@Param("firstId") int firstId);

    /*
        删除一级菜单
     */
    @Transactional
    Boolean deleteFirstMenuInfo(@Param("firstId") int firstId);
}
