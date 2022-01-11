<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div align="center">
    <div class="form-group">
        <label style="width: 100px;" class="col-sm-3 control-label">晒单类型</label>
        <div class="col-sm-3">
            <select style="width:180px;" class="form-control" id="typeSelect" name="products.productType">
                <option value="hot20">请选择</option>
                <option value="new20">最新晒单</option>
                <option value="hot20">人气晒单</option>
                <option value="reply20">评论最多</option>
            </select>
        </div>
        <label style="width: 100px;" class="col-sm-3 control-label">晒单状态</label>
        <div class="col-sm-3" style="float: none;">
            <select style="width:180px;" class="form-control" id="statusSelect" name="products.productType">
                <option value="">请选择</option>
                <option value="-1">暂未晒单</option>
                <option value="0">等待审核</option>
                <option value="1">未审核通过，请重新修改晒单信息</option>
                <option value="2">审核通过，奖励10元</option>
            </select>
        </div>
    </div>
    <script language="javascript" type="text/javascript">
        $("#typeSelect").change(function () {
            location.replace("/admin_back/shareList.action?typeId=" + $("#typeSelect").val());
        });
        $("#statusSelect").change(function () {
            location.replace("/admin_back/shareList.action?typeId=" + $("#typeSelect").val() + "&id=" + $("#statusSelect").val());
        });
    </script>


    <div class="table-responsive">
        <table id="datatable" class="table table-bordered dataTable" aria-describedby="datatable_info">
            <thead>
            <tr role="row">
                <th class="sorting" rowspan="1" colspan="1" style="width: 350px;">标题</th>
                <th class="sorting_desc" rowspan="1" colspan="1" style="width: 420px;">内容</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 100px;">是否奖励</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 50px;">顶</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 50px;">回复</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 150px;">晒单时间</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 150px;">晒单人</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 150px;">状态</th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 150px;">操作</th>
            </tr>
            </thead>

            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <s:iterator value="ShareJSONList" var="ShareJSONLs">
                <tr class="gradeA odd">
                    <td>${ShareJSONLs.shareTitle }</td>
                    <td>${ShareJSONLs.shareContent }</td>
                    <td>
                        <c:if test="${ShareJSONLs.reward == 1}">
                            已奖励
                        </c:if>
                        <c:if test="${ShareJSONLs.reward == 0}">
                            未奖励
                        </c:if>
                    </td>
                    <td>${ShareJSONLs.upCount }</td>
                    <td>${ShareJSONLs.replyCount }</td>
                    <td>${ShareJSONLs.shareDate }</td>
                    <td>${ShareJSONLs.userName }</td>
                    <td>
                        <c:if test="${ShareJSONLs.status == -1}">
                            暂未晒单
                        </c:if>
                        <c:if test="${ShareJSONLs.status == 0}">
                            等待审核
                        </c:if>
                        <c:if test="${ShareJSONLs.status == 1}">
                            未审核通过，请重新修改晒单信息
                        </c:if>
                        <c:if test="${ShareJSONLs.status == 2}">
                            审核通过，奖励10元
                        </c:if>
                    </td>
                    <td><a href="/admin_back/toAddShareImage.action?id=${ShareJSONLs.uid }">[添加晒单图]</a> <a
                            href="/admin_back/toUpdateShare.action?id=${ShareJSONLs.uid }">[审核]</a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>

    </div>

    <!--	<table border="1px;" width="1024px;">-->
    <!--		<tr>-->
    <!--			<th width="1%">ID</th>-->
    <!--			<th width="15%">标题</th>-->
    <!--			<th width="15%">内容</th>-->
    <!--			<th>是否奖励</th>-->
    <!--			<th>顶</th>-->
    <!--			<th>回复</th>-->
    <!--			<th>晒单时间</th>-->
    <!--			<th>晒单人</th>-->
    <!--			<th width="50px;">状态</th>-->
    <!--			<th width="70px;">操作</th>-->
    <!--		</tr>-->
    <!--		<s:iterator value="ShareJSONList" var="ShareJSONLs">-->
    <!--			<tr>-->
    <!--			<td>${ShareJSONLs.uid }</td>-->
    <!--			<td style="padding: 0px 0px;">${ShareJSONLs.shareTitle }</td>-->
    <!--			<td style="padding: 0px 0px;">${ShareJSONLs.shareContent }</td>-->
    <!--			<td>${ShareJSONLs.reward }</td>-->
    <!--			<td>${ShareJSONLs.upCount }</td>-->
    <!--			<td>${ShareJSONLs.replyCount }</td>-->
    <!--			<td>${ShareJSONLs.shareDate }</td>-->
    <!--			<td>${ShareJSONLs.userName }</td>-->
    <!--			<td>-->
    <!--			<c:if test="${ShareJSONLs.status == -1}">-->
    <!--				暂未晒单-->
    <!--			</c:if>-->
    <!--			<c:if test="${ShareJSONLs.status == 0}">-->
    <!--				等待审核-->
    <!--			</c:if>-->
    <!--			<c:if test="${ShareJSONLs.status == 1}">-->
    <!--				未审核通过，请重新修改晒单信息-->
    <!--			</c:if>-->
    <!--			<c:if test="${ShareJSONLs.status == 2}">-->
    <!--				审核通过，奖励10元-->
    <!--			</c:if>-->
    <!--			</td>-->
    <!--			<td><a href="/admin_back/toAddShareImage.action?id=${ShareJSONLs.uid }">[添加晒单图]</a>  <a href="/admin_back/toUpdateShare.action?id=${ShareJSONLs.uid }">[审核]</a></td>-->
    <!--		</tr>-->
    <!--		</s:iterator>-->
    <!--		-->
    <!--	</table>-->
    ${pageString }
</div>
</body>
</html>
