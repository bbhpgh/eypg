package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Spellbuyproduct;

public interface SpellbuyproductService extends TService<Spellbuyproduct, Integer> {

    /**
     * 商品页
     *
     * @param productId
     * @return
     */
    public List findByProductId(int productId);

    /**
     * 即将揭晓商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination upcomingAnnounced(int pageNo, int pageSize);

    /**
     * 人气top
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination upcomingAnnouncedByTop(int pageNo, int pageSize);

    /**
     * 商品期数列表
     *
     * @param integer
     * @return
     */
    public List productPeriodList(Integer integer);

    /**
     * 查询某商品最后一期数
     *
     * @param productId
     * @return
     */
    public Spellbuyproduct findSpellbuyproductLastPeriod(Integer productId);

    /**
     * 查询某商品正在拍购期数，by 修改商品价格
     *
     * @param productId
     * @return
     */
    public Spellbuyproduct findSpellbuyproductByStatus(Integer productId);

    /**
     * 已经揭晓商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination announcedProduct(int pageNo, int pageSize);

    public List loadAllByType();

    public List loadAll();

    public List UpdateLatestlotteryGetList();

    /**
     * 查询某个商品是否已经有最新期
     *
     * @param productId
     * @return
     */
    public List findSpellbuyproductByProductIdIsStatus(Integer productId);
}
