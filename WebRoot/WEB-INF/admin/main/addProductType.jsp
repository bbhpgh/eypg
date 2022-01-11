<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
</head>

<body>
<div align="center">
    <div class="col-sm-6 col-md-6" style="padding: 10px;">
        <div class="block-flat">
            <div class="header">
                <h3>修改分类</h3>
            </div>
            <div class="content">
                <form action="/admin_back/addProductType.action" method="post" class="form-horizontal">
                    <input type="hidden" name="producttype.typeId" value="${producttype.typeId }"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类名称</label>
                        <div class="col-sm-7">
                            <input type="text" placeholder="请输入分类名称" name="producttype.typeName"
                                   value="${producttype.typeName }" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">父分类ID</label>
                        <div class="col-sm-7">
                            <input type="text" placeholder="请输入父分类ID" name="producttype.ftypeId"
                                   value="${producttype.ftypeId }" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">子分类ID</label>
                        <div class="col-sm-7">
                            <input type="text" placeholder="请输入子分类ID" name="producttype.stypeId"
                                   value="${producttype.stypeId }" class="form-control parsley-validated"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-primary" type="submit">确定</button>
                            <button class="btn btn-default" onclick="history.go(-1)" type="button">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
