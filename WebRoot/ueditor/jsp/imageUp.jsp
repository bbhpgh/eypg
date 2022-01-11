<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@page import="com.eypg.util.Uploader" %>

<%
    System.out.println("123");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    up.setSavePath("uploadImages");
    System.out.println("up" + up.getFileName());
    String[] fileType = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB
    up.upload();
    response.getWriter().print("{'original':'" + up.getOriginalName() + "','url':'" + up.getUrl() + "','title':'" + up.getTitle() + "','state':'" + up.getState() + "'}");
%>
