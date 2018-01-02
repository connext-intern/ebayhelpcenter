package com.connext.ebayhelpcenter.service;

import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;

import java.util.List;

/**
 * 管理员接口类
 */
public interface ManagerService {
    /**
     *
     * @return 查询出所有的一级二级菜单，并按照序列号排序
     */
    public List<EbayFirstMenus> listAllTitle();


    /**
     * 删除一级标题
     * @param firstId
     * @return
     */
    Boolean deleteFirstMenu(int firstId);

    /**
     * 删除二级菜单
     * @param secondId
     * @return
     */
    Boolean deleteSecondMenu(int secondId);

    /**
     * 增加一级菜单
     * @param firstTitle 菜单标题
     */
    void newFirstMenus(String firstTitle);

    /**
     * 增加二级菜单及其内容
     * @param secondTitle 菜单标题
     * @param content 纯文本内容
     * @param html 内容的html
     * @param secondFirstId 对应一级菜单的编号
     */
    Boolean newSecondMenus(String secondTitle,String content,String html,int secondFirstId);
    /**
     * 对一级菜单进行排序
     * By Zach Zhang
     * @param firstSerials
     */
    void sortFirstTitle(Integer[] firstSerials);

    /**
     * 对二级菜单进行排序
     * By Zach Zhang
     * @param firstId
     * @param secondSerials
     */
    void sortSecondTitle(Integer firstId, Integer[] secondSerials);

    /**
     * 查找一级菜单的所有内容
     * @return
     */
    List<EbayFirstMenus> showAllFirst();

    /**
     *修改一级菜单标题
     */
    String updateFirst(Integer firstSerial,String firstTitle,Integer firstId);


    /**
     *
     *通过second_first_id查询二级标题对应的内容
     *
     */
    EbaySecondMenus findContentById(Integer id);

    /**
     *
     *通过first_id查询二级标题
     *
     */
    List<EbaySecondMenus> findTitleById(Integer id);

    /**
     *
     *通过id编辑二级菜单的标题
     *
     */
    void  updateSecondTitle(Integer id,String title);

    /**
     *
     *通过id编辑二级菜单的内容
     *
     */
    void updateSecondContent(Integer id,String content);

    /**
     *
     *通过id编辑二级菜单的html
     *
     */
    void updateSecondHtml(Integer id,String html);

    /**
     *
     *通过id编辑二级菜单的排序号
     *
     */
    void updateSecondSerial(Integer id,Integer second_serial);

}
