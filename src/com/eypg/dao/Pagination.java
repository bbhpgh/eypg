package com.eypg.dao;

import java.util.List;

/**
 * 用于分页处理
 *
 * @author songchong email:song316@gmail.com
 */
public class Pagination {

    public int pageSize = 10;    //每页显示数
    private int pageNo = 0;        //当前页码
    private int pageCount = 0;        //总页数
    private int resultCount = 0;    //总结果数
    private boolean isHaveNext = false;    //是否有下一页
    private boolean isHavePrevious = false;    //是否有上一页
    private int currentCount;        //当前结果数
    private List<?> list = null;                //返回结果list　

    public Pagination() {
    }

    ;

    public Pagination(int pageNo, int pageSize, int resultCount, List<?> list) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.setResultCount(resultCount);
        this.list = list;
        this.setCurrentCount();
    }

    //	在设置记录总数时初始化下列值
    public void setResultCount(int resultCount) {
        if (resultCount <= 0) {
            return;
        }
        this.resultCount = resultCount;
//		变量初始化
//		初始化总页数	pageCount
        if (resultCount % pageSize == 0) {
            pageCount = resultCount / pageSize;
        } else {
            pageCount = resultCount / pageSize + 1;
        }
//		初始化是否有下一页　isHaveNext
        if (pageNo < pageCount && pageNo > 0) {
            this.isHaveNext = true;
        }
//		初始化是否有上一页　isHavePrevious
        if (pageNo > 1 && pageNo <= pageCount) {
            this.isHavePrevious = true;
        }
    }

    //	返回开始标记
    public int getFirstResult() {
        int firstNo = 1;
        firstNo = (pageNo - 1) * pageSize;
        return firstNo;
    }

    //	返回结束标记
    public int getEndResult() {
        int endNo = 1;
        if (pageNo == pageCount) {
            endNo = resultCount;
        } else if (pageNo < pageCount && pageNo > 0) {
            endNo = pageNo * pageSize;
        }
        return endNo;
    }

    //	设置当前结果数
    public void setCurrentCount() {
        if (list != null) {
            this.currentCount = list.size();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isHaveNext() {
        return isHaveNext;
    }

    public void setHaveNext(boolean isHaveNext) {
        this.isHaveNext = isHaveNext;
    }

    public boolean isHavePrevious() {
        return isHavePrevious;
    }

    public void setHavePrevious(boolean isHavePrevious) {
        this.isHavePrevious = isHavePrevious;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getResultCount() {
        return resultCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

}
