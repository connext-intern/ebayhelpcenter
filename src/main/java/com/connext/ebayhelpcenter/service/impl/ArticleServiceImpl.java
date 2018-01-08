package com.connext.ebayhelpcenter.service.impl;

import com.connext.ebayhelpcenter.dao.ArticleDao;
import com.connext.ebayhelpcenter.model.EbaySecondMenus;
import com.connext.ebayhelpcenter.service.ArticleService;
import com.connext.ebayhelpcenter.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 查看一二级菜单 正文接口实现了
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    /**
     * 根据二级id查找一级菜单标题
     *
     * @param secondId
     * @return
     */
    @Override
    public String queryFirstTitleBySecondId(int secondId) {
        log.info("UserServiceImpl is queryFirstTitleBySecondId start...");
        if (secondId == 0) {
            throw new ServiceException("secondId is null");
        }
        String firstTitle = this.articleDao.queryFirstTitleInfoBySecondId(secondId);
        if (firstTitle == null) {
            throw new ServiceException(String.format("secondId:{},firstTitle is null", secondId));
        }
        return firstTitle;
    }

    /**
     * 根据二级菜单id查找正文内容
     *
     * @param secondId
     * @return
     */
    @Override
    public String queryHtmlBySecondId(int secondId) {
        log.info("UserServiceImpl is queryHtmlBySecondId start...");
        if (secondId == 0) {
            throw new ServiceException("secondId is null");
        }
        String html = this.articleDao.queryHtmlInfoBySecondId(secondId);
        if (html == null) {
            throw new ServiceException(String.format("secondId:{},html is null", secondId));
        }
        return html;
    }

    /**
     * 根据关键字查询数量
     *
     * @param keyword
     * @return
     */
    @Override
    public int countByKeyword(String keyword) {
        if (keyword == null || keyword.equals("")) {
            throw new ServiceException("keyword is null");
        }
        return this.articleDao.countByKeyword(keyword);
    }

    /**
     * 根据关键词查询标题和文本列表
     *
     * @param keyword
     * @return
     */
    @Override
    public List<EbaySecondMenus> queryKeyWords(String keyword) {
        if (keyword == null || keyword.equals("")) {
            throw new ServiceException("keyword is null");
        }
        List<EbaySecondMenus> ebaySecondMenusList = this.articleDao.queryKeyWords(keyword);
        List<EbaySecondMenus> ebaySecondMenusListHaveKeyWord = new ArrayList<EbaySecondMenus>();
        List<EbaySecondMenus> ebaySecondMenusListNoKeyWord = new ArrayList<EbaySecondMenus>();
        if (ebaySecondMenusList.size() == 0) {
            throw new ServiceException("ebaySecondMenusList is null");
        }
        for (int i = 0; i < ebaySecondMenusList.size(); i++) {
            int length = ebaySecondMenusList.get(i).getContent().length();
            String content = "";
            String secondTitle = "";
            if (length <= 100) {
                content = ebaySecondMenusList.get(i).getContent();
                ebaySecondMenusList.get(i).setContent(content);
                //小于100字的
                ebaySecondMenusListHaveKeyWord.add(ebaySecondMenusList.get(i));
            } else {
                content = (ebaySecondMenusList.get(i).getContent()).substring(0, 100);
                secondTitle = (ebaySecondMenusList.get(i).getSecondTitle());
                //判断100字是否包含关键词,不包含关键词的话内容后面加...
                if (!content.contains(keyword) && !secondTitle.contains(keyword)) {
                    content = content + "...";
                    ebaySecondMenusList.get(i).setContent(content);
                    //大于100字 但标题和内容中不含关键字
                    ebaySecondMenusListNoKeyWord.add(ebaySecondMenusList.get(i));
                } else {
                    //大于100字 标题或内容中包含关键字
                    ebaySecondMenusList.get(i).setContent(content);
                    ebaySecondMenusListHaveKeyWord.add(ebaySecondMenusList.get(i));
                }
            }
        }
        ebaySecondMenusListHaveKeyWord.addAll(ebaySecondMenusListNoKeyWord);
        return ebaySecondMenusListHaveKeyWord;
    }
}
