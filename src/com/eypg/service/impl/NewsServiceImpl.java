package com.eypg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eypg.dao.BaseDAO;
import com.eypg.dao.Pagination;
import com.eypg.pojo.News;
import com.eypg.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public void add(News t) {
        baseDao.saveOrUpdate(t);

    }

    public void delete(String id) {
        baseDao.delById(News.class, id);
    }

    public News findById(String id) {
        StringBuffer hql = new StringBuffer("from News n where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and n.newsId='" + id + "'");
        }
        return (News) baseDao.loadObject(hql.toString());
    }

    public List<News> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination newsList(int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from news n order by postDate desc");
        StringBuffer sql = new StringBuffer("select count(*) from news");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("n", News.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public List indexNews() {
        StringBuffer sql = new StringBuffer("select * from news n order by postDate desc limit 5");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("n", News.class);
        List list = baseDao.sqlQueryEntity(sql, entityMap);
        return list;
    }

}
