package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Orderdetail;
import com.eypg.pojo.Orderdetailaddress;

public interface LatestlotteryService extends TService<Latestlottery, Integer> {

    /**
     * 最新揭晓
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination LatestAnnounced(int pageNo, int pageSize);

    /**
     * 最新揭晓6条滚动
     *
     * @return
     */
    public List indexWinningScroll();

    /**
     * 查看拍购记录商品详情
     *
     * @param productId
     * @return
     */
    public List getBuyHistoryByDetail(Integer spellbuyProductId);

    /**
     * 查询某用户获得的商品
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public Pagination getProductByUser(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 查询某用户获得的商品ByCount
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public Integer getProductByUserByCount(String userId, String startDate, String endDate);

    /**
     * 最新揭晓详情
     *
     * @param productId
     * @return
     */
    public List getLotteryDetail(Integer spellbuyProductId);

    /**
     * 某件商品其他期数获得者
     *
     * @param productId
     * @param shareId
     * @return
     */
    public List getProductOtherWinUser(String productId, String shareId);

    /**
     * 最新揭晓详情-总共多个人购买
     *
     * @param id
     * @return
     */
    public Integer getCountByLotteryDetail(String spellbuyProductId);

    /**
     * 最新揭晓详情-购买人购买详情
     *
     * @param productId
     * @return
     */
    public Pagination getLotteryDetailBybuyerList(Integer SpellbuyProductId, int pageNo, int pageSize);

    /**
     * 最新揭晓详情-购买人购买详情ByCount
     *
     * @param productId
     * @return
     */
    public Integer getLotteryDetailBybuyerListByCount(Integer SpellbuyProductId);

    /**
     * 查询某商品ID与商品基数ID是否已经开过奖
     *
     * @param SpellbuyProductId
     * @param productId
     * @return
     */
    public List getLatestlotteryBySpellbuyProductIdAndProductIdIsExist(Integer SpellbuyProductId);

    public Latestlottery findLatestlotteryByspellbuyrecordId(Integer spellbuyrecordId);

    /**
     * 获得商品详情-物流操作详情
     *
     * @param id
     * @return
     */
    public List orderdetailListById(String id);

    /**
     * 获得商品详情-物流地址详情
     *
     * @param id
     * @return
     */
    public Orderdetailaddress orderdetailaddressFindByOrderdetailId(String id);

    /**
     * 获得商品详情-添加物流地址详情
     *
     * @param orderdetailaddress
     */
    public void addOrderdetailaddress(Orderdetailaddress orderdetailaddress);

}
