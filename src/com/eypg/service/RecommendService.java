package com.eypg.service;

import java.util.List;

import com.eypg.pojo.Recommend;

public interface RecommendService extends TService<Recommend, Integer> {

    public List getRecommend();

}
