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
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Sharecomments;
import com.eypg.pojo.Shareimage;
import com.eypg.pojo.Shareinfo;
import com.eypg.pojo.User;
import com.eypg.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    @Qualifier("baseDao")
    BaseDAO baseDao;

    public Pagination loadPageShare(String type, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select so.*,se.*,ly.* from shareinfo so,shareimage se,latestlottery ly where so.productId = ly.spellbuyProductId and so.userId = ly.userId and so.uid = se.shareInfoId and so.status = 2 group by so.uid");
        StringBuffer sql = new StringBuffer("select count(DISTINCT(so.uid)) from shareinfo so,shareimage se,latestlottery ly where so.productId = ly.spellbuyProductId and so.userId = ly.userId and so.uid = se.shareInfoId and so.status = 2");
        if (type.equals("new20")) {
            hql.append(" ORDER BY so.shareDate DESC");
        }
        if (type.equals("hot20")) {
            hql.append(" ORDER BY so.upCount DESC");
        }
        if (type.equals("reply20")) {
            hql.append(" ORDER BY so.replyCount DESC");
        }
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("so", Shareinfo.class);
        entityMap.put("se", Shareimage.class);
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public Pagination adminShareList(String type, String status, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select so.*,ly.* from shareinfo so,latestlottery ly where so.userId = ly.userId and so.productId = ly.spellbuyProductId");
        StringBuffer sql = new StringBuffer("select count(*) from shareinfo so,latestlottery ly where so.userId = ly.userId and so.productId = ly.spellbuyProductId");
        if (StringUtils.isNotEmpty(status)) {
            if (!status.equals("null")) {
                hql.append(" and so.status ='" + status + "'");
                sql.append(" and so.status ='" + status + "'");
            }
        }
        if (type.equals("new20")) {
            hql.append(" ORDER BY so.shareDate DESC");
        }
        if (type.equals("hot20")) {
            hql.append(" ORDER BY so.upCount DESC");
        }
        if (type.equals("reply20")) {
            hql.append(" ORDER BY so.replyCount DESC");
        }
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("so", Shareinfo.class);
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }


    public List shareShow(int id) {
        StringBuffer sql = new StringBuffer("select so.*,ly.* from shareinfo so,latestlottery ly where so.userId = ly.userId and so.productId = ly.spellbuyProductId and so.uid ='" + id + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("so", Shareinfo.class);
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQueryEntity(sql, entityMap);
        return list;
    }

    public void add(Shareinfo t) {
        this.baseDao.saveOrUpdate(t);
    }

    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    public Shareinfo findById(String id) {
        StringBuffer hql = new StringBuffer("from Shareinfo shareinfo where 1=1");
        if (StringUtils.isNotBlank(id)) {
            hql.append(" and shareinfo.uid='" + id + "'");
        }
        return (Shareinfo) baseDao.loadObject(hql.toString());
    }

    public List<Shareinfo> query(String hql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(String hql) {
        // TODO Auto-generated method stub

    }

    public Pagination shareByUser(String userId, String startDate,
                                  String endDate, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select so.*,se.*,ly.* from shareinfo so,shareimage se,latestlottery ly where so.productId = ly.spellbuyProductId and so.userId = ly.userId and so.uid = se.shareInfoId and so.status = 2 and so.userId = '" + userId + "'");
        StringBuffer sql = new StringBuffer("select count(*) from shareinfo so,latestlottery ly where so.productId = ly.spellbuyProductId and so.userId = ly.userId and so.status = 2 and so.userId = '" + userId + "' ");
        if (StringUtils.isNotBlank(startDate)) {
            hql.append(" and so.shareDate >= '" + startDate + "'");
            sql.append(" and so.shareDate >= '" + startDate + "'");
        }
        if (StringUtils.isNotBlank(endDate)) {
            hql.append(" and so.shareDate <='" + endDate + "'");
            sql.append(" and so.shareDate <='" + endDate + "'");
        }
        hql.append(" group by so.uid order by so.shareDate desc");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("so", Shareinfo.class);
        entityMap.put("se", Shareimage.class);
        entityMap.put("ly", Latestlottery.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public List getShareimage(String shareId) {
        StringBuffer sql = new StringBuffer("select * from shareimage se where se.shareInfoId = '" + shareId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("se", Shareimage.class);
        List list = baseDao.sqlQueryEntity(sql, entityMap);
        return list;
    }

    public void addShareImage(Shareimage shareimage) {
        baseDao.saveOrUpdate(shareimage);
    }

    public Pagination shareByComment(String shareId, int pageNo, int pageSize) {
        StringBuffer hql = new StringBuffer("select * from sharecomments ss,user ur where ss.userId = ur.userId and ss.shareInfoId = '" + shareId + "' and ss.reCommentId is null order by createDate desc");
        StringBuffer sql = new StringBuffer("select count(*) from sharecomments ss,user ur where ss.userId = ur.userId and ss.shareInfoId = '" + shareId + "' and ss.reCommentId is null");
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ss", Sharecomments.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQuery(hql, entityMap, page);
        int resultCount = baseDao.getAllNum(sql);
        page.setList(list);
        page.setResultCount(resultCount);
        return page;
    }

    public List getReCommentList(String shareCommentId) {
        StringBuffer sql = new StringBuffer("select * from sharecomments ss,user ur where ss.userId = ur.userId and ss.reCommentId = '" + shareCommentId + "'");
        Map<String, Class> entityMap = new HashMap<String, Class>();
        entityMap.put("ss", Sharecomments.class);
        entityMap.put("ur", User.class);
        List list = baseDao.sqlQueryEntity(sql, entityMap);
        return list;
    }

    public void createComment(Sharecomments sharecomments) {
        this.baseDao.saveOrUpdate(sharecomments);
    }

    public Sharecomments findBySharecommentsId(String sharecommentsId) {
        StringBuffer hql = new StringBuffer("from Sharecomments sharecomments where 1=1");
        if (StringUtils.isNotBlank(sharecommentsId)) {
            hql.append(" and sharecomments.uid='" + sharecommentsId + "'");
        }
        return (Sharecomments) baseDao.loadObject(hql.toString());
    }

}
