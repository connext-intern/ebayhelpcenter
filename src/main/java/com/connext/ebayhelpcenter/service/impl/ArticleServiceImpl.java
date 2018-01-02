package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ArticleDao;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ArticleService;
import com.connext.ebayhelpcenter.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * 查看一二级菜单 正文接口实现了
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao userDao;

    /**
     * 根据二级菜单id查找正文内容
     * @param secondId
     * @return
     */
    @Override
    public String queryHtmlBySecondId(int secondId) {
        log.info("UserServiceImpl is queryHtmlBySecondId start...");
        if(secondId == 0){
            throw new ServiceException("secondId is null");
        }
        String html = this.userDao.queryHtmlInfoBySecondId(secondId);
        if(html == null){
            throw new ServiceException("html is null");
        }
        return html;
    }
}
