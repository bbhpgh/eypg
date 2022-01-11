package com.eypg.service;

import java.util.List;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Sharecomments;
import com.eypg.pojo.Shareimage;
import com.eypg.pojo.Shareinfo;

public interface ShareService extends TService<Shareinfo, Integer> {

    /**
     * 晒单列表页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination loadPageShare(String type, int pageNo, int pageSize);

    /**
     * 后台晒单列表
     *
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination adminShareList(String type, String status, int pageNo, int pageSize);

    /**
     * 晒单内容页
     *
     * @param id
     * @return
     */
    public List shareShow(int id);

    /**
     * 我的晒单
     *
     * @param userId
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination shareByUser(String userId, String startDate, String endDate, int pageNo, int pageSize);

    /**
     * 获取晒单图片
     *
     * @param shareId
     * @return
     */
    public List getShareimage(String shareId);

    /**
     * 添加晒单图片
     *
     * @param shareimage
     */
    public void addShareImage(Shareimage shareimage);

    /**
     * 晒单评论列表
     *
     * @param shareId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination shareByComment(String shareId, int pageNo, int pageSize);

    /**
     * 获取评论列表的评论
     *
     * @param shareCommentId
     * @return
     */
    public List getReCommentList(String shareCommentId);

    /**
     * 添加评论
     *
     * @param sharecomments
     */
    public void createComment(Sharecomments sharecomments);

    /**
     * 查询某条评论
     *
     * @param sharecommentsId
     * @return
     */
    public Sharecomments findBySharecommentsId(String sharecommentsId);

}
