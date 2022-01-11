package com.eypg.service;

import java.util.List;

import com.eypg.pojo.Lotteryproductutil;

public interface LotteryproductutilService extends TService<Lotteryproductutil, Integer> {

    public List<Lotteryproductutil> loadAll();

}
