package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.News;

public interface NewsService extends TService<News, Integer> {

    public Pagination newsList(int pageNo, int pageSize);

    public List indexNews();

}
