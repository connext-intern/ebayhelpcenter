package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.UserDao;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * 查看一二级菜单 正文接口实现了
 */
@Service
public class UserServiceImpl implements UserService{
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;

    @Override
    public List<EbaySecondMenus> queryKeyWords(String keyword) {
        List<EbaySecondMenus> ebaySecondMenusList = this.userDao.queryKeyWords(keyword);
        for (int i=0;i<ebaySecondMenusList.size();i++){
            String content = (ebaySecondMenusList.get(i).getContent()).substring(0,1);
            ebaySecondMenusList.get(i).setContent(content);
        }
        return ebaySecondMenusList;
    }

    @Override
    public EbaySecondMenus queryContent(int secondId) {
        return this.userDao.queryContent(secondId);
    }

    /**
     * 根据二级菜单id查找正文内容
     * @param secondId
     * @return
     */
    @Override
    public String queryHtmlBySecondId(int secondId) {
        log.info("UserServiceImpl is queryHtmlBySecondId start...");
        return this.userDao.queryHtmlInfoBySecondId(secondId);
    }
}
