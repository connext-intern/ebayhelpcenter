package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ArticleDao;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ArticleService;
import com.connext.ebayhelpcenter.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mengdi.Li
 * @date 2017/12/28 9:25
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao userDao;

    @Override
    public int countByKeyword(String keyword) {
        if(keyword==null||keyword.equals("")){
            throw new ServiceException("keyword is null");
        }
        return this.userDao.countByKeyword(keyword);
    }

    @Override
    public List<EbaySecondMenus> queryKeyWords(String keyword) {
        if(keyword==null||keyword.equals("")){
            throw new ServiceException("keyword is null");
        }
        List<EbaySecondMenus> ebaySecondMenusList = this.userDao.queryKeyWords(keyword);
        if(ebaySecondMenusList.size()==0){
            throw new ServiceException("ebaySecondMenusList is null");
        }
        for (int i=0;i<ebaySecondMenusList.size();i++){
            int length = ebaySecondMenusList.get(i).getContent().length();
            String content = "";
            if(length < 100){
                content = ebaySecondMenusList.get(i).getContent();
            }else{
                content = (ebaySecondMenusList.get(i).getContent()).substring(0,100);
            }
            ebaySecondMenusList.get(i).setContent(content);
        }
        return ebaySecondMenusList;
    }

    @Override
    public EbaySecondMenus queryContent(int secondId) {
        if(secondId==0){
            throw new ServiceException("secondId is null");
        }
        EbaySecondMenus ebaySecondMenus = this.userDao.queryContent(secondId);
        if(ebaySecondMenus == null){
            throw new ServiceException("ebaySecondMenus is null");
        }
        return ebaySecondMenus;
    }
}
