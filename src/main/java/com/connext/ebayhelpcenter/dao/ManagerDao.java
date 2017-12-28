package com.connext.ebayhelpcenter.dao;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ManagerDao {
    /**
     *
     * @return 查询出所有的一级和二级菜单，并按照序列号排序
     */
    public List<EbayFirstMenus> listAllTitle();
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

    /**
     * 增加一级菜单
     * @param ebayFirstMenus
     */
    void newFirstMenus(@Param("ebayFirstMenus") EbayFirstMenus ebayFirstMenus);

    /**
     * 增加二级菜单及其内容
     * @param ebaySecondMenus
     */
    void newSecondMenus(@Param("ebaySecondMenus") EbaySecondMenus ebaySecondMenus);

    /**
     * 新增二级菜单时判断一级菜单是否还存在
     * @param secondFirstId
     */
    Boolean hasFirstMenus(@Param("secondFirstId") int secondFirstId);
}
