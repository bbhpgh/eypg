package com.eypg.service;

import java.math.BigDecimal;
import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Spellbuyrecord;

public interface SpellbuyrecordService extends TService<Spellbuyrecord, Integer> {

    /**
     * 最热人气商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination findHotProductList(int pageNo, int pageSize);

    /**
     * 最新拍购商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getNowBuyList(int pageNo, int pageSize);

    /**
     * 最新100条拍购记录AJAX请求
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getNowBuyAjaxList(int pageNo, int pageSize, int spellbuyRecordId);

    /**
     * 全站拍购记录查询
     *
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getAllBuyRecord(String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 揭晓详细计算结果(全站最后100条购买记录)
     *
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getlotteryDetail(String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 开奖最后一百条购买记录
     *
     * @return
     */
    public List getSpellbuyRecordByLast100(String startDate, String endDate);

    /**
     * 得到某商品最后购买记录
     *
     * @param fkSpellbuyProductId
     * @return
     */
    public List getEndBuyDateByProduct(Integer fkSpellbuyProductId);

    /**
     * 首页分类显示商品
     *
     * @param typeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination findProductByTypeId(String typeId, int pageNo, int pageSize);

    /**
     * 商品页按商品类型查询商品
     *
     * @param typeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination ProductByTypeIdList(String typeId, String orderName, int pageNo, int pageSize);

    /**
     * 用户中心首页最新上架
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination nowUpProducts(int pageNo, int pageSize);

    /**
     * 搜索商品
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination searchProduct(String keyword, String orderName, int pageNo, int pageSize);


    /**
     * 最新参与
     *
     * @param productId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination LatestParticipate(String spellbuyProductId, int pageNo, int pageSize);

    /**
     * 最新参与ByCount
     *
     * @param productId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Integer LatestParticipateByCount(String spellbuyProductId);

    /**
     * 得到某条购买记录的随机码(list 页)
     *
     * @param spellbuyrecordId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List getRandomNumberList(String spellbuyProductId, String userId);

    /**
     * 得到某条购买记录的随机码(详细 页)
     *
     * @param spellbuyrecordId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getRandomNumberListPage(String spellbuyProductId, String userId, int pageNo, int pageSize);

    /**
     * 得到某条购买记录的随机码(详细 页)ByCount
     *
     * @param spellbuyrecordId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Integer getRandomNumberListPageByCount(String spellbuyProductId, String userId);

    /**
     * 查询某用户的购买记录
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination buyHistoryByUser(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 查询某用户的购买记录ByCount
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Integer buyHistoryByUserByCount(String userId, String startDate, String endDate);

    /**
     * 查询某用户购买记录详情
     *
     * @param userId
     * @param productId
     * @return
     */
    public List getBuyHistoryByDetail(String productId, String userId);

    /**
     * 我的购买记录
     *
     * @param userId
     * @param fkSpellbuyProductId
     * @return
     */
    public List getUserByHistory(String userId, String fkSpellbuyProductId);

    /**
     * 某件商品满员后抽奖
     *
     * @param spellbuyProductId
     * @return
     */
    public List randomByBuyHistoryByspellbuyProductId(Integer productId, String randomNumber);


    public List WinRandomNumber(Integer productId, Integer randomNumber);

    /**
     * 全站总拍购人数
     *
     * @return
     */
    public BigDecimal getAllByCount();

    /**
     * 查询某用户总共消费金额
     *
     * @param userId
     * @return
     */
    public BigDecimal findSumByBuyPriceByUserId(String userId);


}
