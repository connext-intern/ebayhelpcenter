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
     *  查询是否存在该二级菜单
     * @param secondId
     * @return
     */
    Boolean hasSecondMenu(@Param("secondId") int secondId);

    /*
        删除该二级菜单
        @Param("secondId") 二级菜单id
        @return boolean
     */
    Boolean deleteSecondMenuInfo(@Param("secondId") int secondId);

    /**
     * 查询一级菜单下是否存在二级菜单
     * @param firstId
     * @return
     */
    Boolean firstHasSecondMenus(@Param("firstId") int firstId);

    /**
     * 删除一级菜单下的所有二级菜单
     * @param firstId
     * @return
     */
    @Transactional
    Boolean deleteSecondMenusInfoFromFirst(@Param("firstId") int firstId);

    /**
     * 删除一级菜单
     * @param firstId
     * @return
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
    /**
     * 查出所有的一级菜单zhangchi
     * @return
     */
    List<EbayFirstMenus> listAllFirstTitle();

    /**
     * 根据主键更新一级菜单的序列号zhangchi
     * @param ebayFirstMenus
     */
    void updateFirstSerialByID(EbayFirstMenus ebayFirstMenus);

    /**
     * 查处所有的二级菜单 张弛
     * @param firstId
     * @return
     */
    List<EbaySecondMenus> listAllSencondTitleByFirstId(Integer firstId);

    /**
     * 根据主键更新二级菜单的序列号zhangchi
     * @param ebaySecondMenus
     */
    void updateSecondSerialByID(EbaySecondMenus ebaySecondMenus);

    /**
     * 查找一级菜单的标题
     * @return
     */
    List<EbayFirstMenus> showAllFirst();

    /**
     * 修改标题
     */
    void updateFirst(@Param("firstSerial")int firstSerial,@Param("firstTitle")String title);

    /**
     *
     *通过second_first_id查询二级标题
     *
     */
    List<EbaySecondMenus>findTitleById(int id);

    /**
     *
     *通过second_id查询二级标题对应的内容
     *
     */
    List<EbaySecondMenus> findContentById(int id);

    /**
     *
     *通过id编辑二级菜单的标题
     *
     */
    void  updateSecondTitle(@Param("id") int id,@Param("title") String title);

    /**
     *
     *通过id编辑二级菜单的内容
     *
     */
    void updateSecondContent(@Param("id")int id,@Param("content")String content);

    /**
     *
     *通过id编辑二级菜单的html
     *
     */
    void updateSecondHtml(@Param("id")int id,@Param("html")String html);

    /**
     *
     *通过id编辑二级菜单的排序号
     *
     */
    void updateSecondSerial(@Param("id")int id,@Param("second_serial")int second_serial);
    /**
     * 查找是否含有一级菜单的id
     * @param firstSerial
     * @return
     */
    String findFirstSerial(@Param("firstSerial")int firstSerial);
}
