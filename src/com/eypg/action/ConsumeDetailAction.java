package com.eypg.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Consumerdetail;
import com.eypg.service.ConsumerdetailService;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("ConsumeDetailAction")
public class ConsumeDetailAction extends ActionSupport {

    private static final long serialVersionUID = 3910394002303639758L;

    @Autowired
    private ConsumerdetailService consumerdetailService;

    private String id;
    private String userId;
    private int pageNo;
    private int pageSize = 10;
    private int pageCount;
    private int resultCount;
    private List<Consumerdetail> consumerdetailList;

    public String index() {
        HttpServletRequest request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        resultCount = consumerdetailService.userByConsumetableDetailByCount(id);
                        return "ConsumeDetail";
                    }
                }
            }
        }
        return "login_index";
    }

    /**
     * 消费记录-查看详情分页
     *
     * @return
     */
    public String ConsumeDetailAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = consumerdetailService.userByConsumetableDetail(id, pageNo, pageSize);
        List<Consumerdetail> dataList = (List<Consumerdetail>) datePage.getList();
        Struts2Utils.renderJson(dataList);
        return null;
    }

    /**
     * Count
     */
    public void ConsumeDetailAjaxPageByCount() {
        resultCount = consumerdetailService.userByConsumetableDetailByCount(id);
        Struts2Utils.renderText(resultCount + "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Consumerdetail> getConsumerdetailList() {
        return consumerdetailList;
    }

    public void setConsumerdetailList(List<Consumerdetail> consumerdetailList) {
        this.consumerdetailList = consumerdetailList;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public int getPageNo() {
        return pageNo;
    }


    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getPageCount() {
        return pageCount;
    }


    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public int getResultCount() {
        return resultCount;
    }


    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }


}
