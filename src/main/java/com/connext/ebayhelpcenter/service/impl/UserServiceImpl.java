package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.UserDao;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:25
 */
@Service
public class UserServiceImpl implements UserService{

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
}
