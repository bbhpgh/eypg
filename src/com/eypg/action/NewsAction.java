package com.eypg.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.pojo.News;
import com.eypg.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("NewsAction")
public class NewsAction extends ActionSupport {

    private static final long serialVersionUID = 1889272927204740730L;

    @Autowired
    private NewsService newsService;

    private Integer id;
    private News news;

    public String index() {
        news = newsService.findById(id.toString());
        return "index";
    }

    public String toAdd() {

        return "toAddOrUpdate";
    }

    public String add() {

        return "success";
    }

    public String toUpdate() {

        return "toAddOrUpdate";
    }

    public String update() {

        return "success";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }


}
