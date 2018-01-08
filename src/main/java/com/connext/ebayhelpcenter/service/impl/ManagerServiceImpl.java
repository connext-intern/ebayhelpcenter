package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ManagerDao;
import com.connext.ebayhelpcenter.model.EbayFirstMenus;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ManagerService;
import com.connext.ebayhelpcenter.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员接口实现类
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    private static Logger log = LoggerFactory.getLogger(ManagerServiceImpl.class);
    private static final int FIRST_TITLE_MAX = 8;
    private static final int SECOND_TITLE_MAX = 15;


    @Autowired
    private ManagerDao managerDao;

    /**
     * @return 查询出所有的一级二级菜单，并按照序列号排序
     */
    @Override
    public List<EbayFirstMenus> listAllTitle() {
        return managerDao.listAllTitle();
    }

    /**
     * 实现 删除一级菜单接口
     *
     * @param firstId
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteFirstMenu(int firstId) {
        log.info("ManagerServiceImpl is deleteFirstMenu start...");

        //1.判断传入值是否为空
        if (firstId == 0) {
            throw new ServiceException("firstId is null");
        }

        //2.判断该一级菜单是否存在
        Boolean hasFirstMenu = this.managerDao.hasFirstMenu(firstId);

        if (!hasFirstMenu) {
            log.info("firstId:{},firstMenu not exist", firstId);
            throw new ServiceException(String.format("firstId:{},firstMenu not exist", firstId));
        }

        //3.判断一级菜单下是否存在二级菜单
        Boolean isFirstHasSecondMenus = this.managerDao.firstHasSecondMenus(firstId);
        if (isFirstHasSecondMenus) {
            log.info("一级菜单:{},存在二级菜单,需操作两张表", firstId);
            Boolean b1 = this.managerDao.deleteSecondMenusInfoFromFirst(firstId);
            Boolean b2 = this.managerDao.deleteFirstMenuInfo(firstId);
            if (b1 && b2) {
                log.info("firstId:{},两张表同时删除成功", firstId);
                return true;
            } else {
                log.info("firstId:{},有一张表删除操作失败", firstId);
                throw new ServiceException(String.format("firstId:{},transaction error", firstId));
            }
        } else {
            log.info("一级菜单:{},不存在二级菜单,直接删除一级菜单即可", firstId);
            Boolean b = this.managerDao.deleteFirstMenuInfo(firstId);
            if (b) {
                log.info("firstId:{},delete firstMenu success", firstId);
                return true;
            } else {
                log.info("firstId:{},delete firstMenu fail", firstId);
                throw new ServiceException(String.format("firstId:{},delete firstMenu fail", firstId));
            }
        }
    }

    /**
     * 实现 删除二级菜单接口
     *
     * @param secondId
     * @return
     */
    @Override
    public Boolean deleteSecondMenu(int secondId) {
        log.info("ManagerServiceImpl is deleteSecondMenu start...");

        //1.判断传入值是否为空
        if (secondId == 0) {
            throw new ServiceException("secondId id null");
        }

        //2.判断该二级菜单是否存在
        Boolean hasSecondMenu = this.managerDao.hasSecondMenu(secondId);

        if (!hasSecondMenu) {
            log.info("secondId:{},secondMenu not exist", secondId);
            throw new ServiceException(String.format("secondId:{},secondMenu not exist", secondId));
        }

        Boolean isDeleteSecondMenu = this.managerDao.deleteSecondMenuInfo(secondId);
        if (isDeleteSecondMenu) {
            log.info("secondId:{}, delete success", secondId);
            return true;
        } else {
            log.info("secondId:{}, delete fail", secondId);
            throw new ServiceException(String.format("secondId:{},delete secondMenu fail", secondId));
        }

    }

    /**
     * 增加一级菜单
     *
     * @param firstTitle 菜单标题
     */
    @Override
    public void saveFirstMenus(String firstTitle) {
        if (firstTitle.trim().isEmpty()) {
            throw new ServiceException("标题不能为空");
        }
        if (firstTitle.length() > FIRST_TITLE_MAX) {

            throw new ServiceException("标题字数不能多于8");
        }
        if (managerDao.hasFirstMenusTitle(firstTitle)) {
            throw new ServiceException("标题已存在");
        }
        EbayFirstMenus ebayFirstMenus = new EbayFirstMenus();
        ebayFirstMenus.setFirstTitle(firstTitle);
        managerDao.saveFirstMenus(ebayFirstMenus);
    }

    /**
     * 增加二级菜单及其内容
     *
     * @param secondTitle   菜单标题
     * @param content       纯文本内容
     * @param html          内容的html
     * @param secondFirstId 对应一级菜单的编号
     */
    @Override
    public void saveSecondMenus(String secondTitle, String content, String html, int secondFirstId) {
        if (secondTitle.trim().isEmpty()) {
            throw new ServiceException("标题不能为空");
        }
        if (secondTitle.length() > SECOND_TITLE_MAX) {
            throw new ServiceException("标题字数不能多于15");
        }
        if (!managerDao.hasFirstMenus(secondFirstId)) {
            throw new ServiceException("一级菜单不存在");
        }
        EbaySecondMenus ebaySecondMenus = new EbaySecondMenus();
        ebaySecondMenus.setSecondTitle(secondTitle);
        ebaySecondMenus.setContent(content);
        ebaySecondMenus.setHtml(html);
        ebaySecondMenus.setSecondFirstId(secondFirstId);

        if (managerDao.hasSecondMenusTitle(ebaySecondMenus)) {
            throw new ServiceException("标题已存在");
        }
        managerDao.saveSecondMenus(ebaySecondMenus);
    }

    /**
     * 对一级菜单进行排序的方法
     * By Zach Zhang
     *
     * @Param firstSerials
     */
    @Override
    public void sortFirstTitle(Integer[] firstSerials) {
        //1.先判断传来的新一级菜单序列号数组是否为空
        if (firstSerials.length == 0) {
            throw new ServiceException("传来的新一级菜单序列号数组为空！");
        }
        //2.再按序列号排序查出所有的一级菜单
        List<EbayFirstMenus> ebayFirstMenuses = managerDao.listAllFirstTitle();
        //3.再判断从数据库查出的所有一级菜单长度是否为0以及是否等于传进来的数组长度
        if (ebayFirstMenuses == null) {
            throw new ServiceException("从数据库取出的一级菜单为空！");
        } else if (ebayFirstMenuses.size() != firstSerials.length) {
            throw new ServiceException("新旧序列号数组长度不一样！");
        }
        int i = 0;
        //4.使用for循环更新序列号
        for (EbayFirstMenus ebayFirstMenus : ebayFirstMenuses) {
            ebayFirstMenus.setFirstSerial(firstSerials[i]);
            log.info(ebayFirstMenus.toString());
            managerDao.updateFirstSerialByID(ebayFirstMenus);
            i++;
        }
    }

    /**
     * 对二级菜单进行排序
     * By Zach Zhang
     *
     * @param firstId
     * @param secondSerials
     */
    @Override
    public void sortSecondTitle(Integer firstId, Integer[] secondSerials) {
        //判断是否为空
        if (firstId == 0) {
            throw new ServiceException("传进来的一级菜单id为空！");
        }
        if (secondSerials.length == 0) {
            throw new ServiceException("传进来的二级菜单序列号数组为空！");
        }
        //根据一级id查出所有的二级菜单（按原来的序列号排序）
        List<EbaySecondMenus> ebaySecondMenuses = managerDao.listAllSencondTitleByFirstId(firstId);
        if (ebaySecondMenuses == null) {
            throw new ServiceException("从数据库取出的二级菜单为空！");
        } else if (ebaySecondMenuses.size() != secondSerials.length) {
            throw new ServiceException("新旧序列号数组长度不一样！");
        }
        int i = 0;
        //使用for循环更新序列号
        for (EbaySecondMenus ebaySecondMenus : ebaySecondMenuses) {
            ebaySecondMenus.setSecondSerial(secondSerials[i]);
            log.info(ebaySecondMenus.toString());
            managerDao.updateSecondSerialByID(ebaySecondMenus);
            i++;
        }
    }

    /**
     * 查找所有一级菜单的标题
     *
     * @return
     */
    @Override
    public List<EbayFirstMenus> showAllFirst() {
        return managerDao.listAllFirstTitle();
    }

    /**
     * 修改一级标题
     *
     * @param firstSerial
     * @param
     */
    @Override
    public void updateFirst(Integer firstSerial, String firstTitle, Integer firstId) {
        if (firstId == null || firstSerial == null || firstTitle == "") {
            log.info("firstid或者serial不能为空或者title不能为空");
            throw new ServiceException("其中有一个为空");
        } else if (firstTitle.length() > FIRST_TITLE_MAX) {
            log.info("标题字数不能多余八个字");
            throw new ServiceException("标题字数大于八个字");
        } else if (firstTitle.length() < FIRST_TITLE_MAX && firstId != null && firstSerial != null) {
            EbayFirstMenus ebayFirstMenus = managerDao.findFirstId(firstId);
            if (ebayFirstMenus != null) {
                boolean hasFirstmenustitle = managerDao.hasFirstMenusTitle(firstTitle);
                if (!hasFirstmenustitle) {
                    managerDao.updateFirst(firstSerial, firstTitle, firstId);
                } else {
                    throw new ServiceException("此标题已经存在");
                }
            } else {
                log.info("没有找到对应的id");
                throw new ServiceException("没有找到对应的id");
            }
        }
    }

    /**
     * 根据title关键字模糊查找一级菜单所有内容 zhangyang
     *
     * @param firstTitle
     * @return
     */
    @Override
    public List<EbayFirstMenus> searchFirstByTitle(String firstTitle) {
        if (firstTitle != "") {
            return managerDao.searchFirstByTitle(firstTitle);
        } else {
            throw new ServiceException("关键字不能为空");
        }
    }

    /**
     * 通过second__id查询二级标题对应的内容
     */
    @Override
    public EbaySecondMenus findContentById(Integer id) {
        if (id == 0) {
            throw new ServiceException("secondId is null");
        }

        EbaySecondMenus ebaySecondMenus = managerDao.findContentById(id);
        if (ebaySecondMenus == null) {
            throw new ServiceException(String.format("secondId:{}, ebaySecondMenus is null", id));
        }
        return ebaySecondMenus;
    }

    /**
     * 通过second_id查询二级标题
     */
    @Override
    public List<EbaySecondMenus> findTitleById(Integer id) {
        if (id == 0) {
            throw new ServiceException("secondId is null");
        }

        List<EbaySecondMenus> ebaySecondMenusList = managerDao.listAllSencondTitleByFirstId(id);
        if (ebaySecondMenusList == null) {
            throw new ServiceException(String.format("secondId:{},ebaySecondMenusList is null", id));
        }
        return ebaySecondMenusList;
    }

    /**
     * 通过id编辑二级菜单的标题
     */
    @Override
    public void updateSecondTitle(Integer id, String title) {
        if (title.trim().isEmpty()) {
            throw new ServiceException("标题不能为空！");
        }
        if (title.length() > SECOND_TITLE_MAX) {
            throw new ServiceException("标题字数不能多于15");
        }

        managerDao.updateSecondTitle(id, title);
    }

    /**
     * 通过id编辑二级菜单的内容
     */
    @Override
    public void updateSecondContent(Integer id, String content) {
        if (content.trim().isEmpty()) {
            throw new ServiceException("内容不能为空！");
        }
        managerDao.updateSecondContent(id, content);
    }

    /**
     * 通过id编辑二级菜单的html
     */
    @Override
    public void updateSecondHtml(Integer id, String html) {
        if (html.trim().isEmpty()) {
            throw new ServiceException("html内容不能为空！");
        }
        managerDao.updateSecondHtml(id, html);
    }

    /**
     * 通过id编辑二级菜单的排序号
     */
    @Override
    public void updateSecondSerial(Integer id, Integer second_serial) {
        if (second_serial == null) {
            throw new ServiceException("二级菜单序号不能为空！");
        }
        managerDao.updateSecondSerial(id, second_serial);
    }

    /**
     * 二级菜单标题的模糊查询
     */
    @Override
    public List<EbaySecondMenus> searchSecondByTitle(String secondTitle) {
        if (secondTitle == null) {
            throw new ServiceException("secondTitle is null");
        }

        List<EbaySecondMenus> ebaySecondMenusList = managerDao.searchSecondByTitle(secondTitle);
        if (ebaySecondMenusList == null) {
            throw new ServiceException(String.format("secondTitle:{},ebaySecondMenusList is null", secondTitle));
        }
        return ebaySecondMenusList;
    }
}
