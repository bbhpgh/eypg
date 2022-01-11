<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript"
            src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="block-flat">
    <div class="content">
        <div class="form-group">
            <label class="col-sm-3 control-label" style="width: 100px;">搜索类型</label>
            <div class="col-sm-3">
                <select id="typeId" class="form-control" style="width:180px;">
                    <option value="userId">用户ID</option>
                    <option value="userName">用户昵称</option>
                    <option value="mail">用户邮箱</option>
                    <option value="phone">用户手机</option>
                </select>
            </div>
            <div class="col-sm-3">
                <input type="text" value="" name="product.productName" id="keyword" class="form-control"
                       placeholder="请输入..."/>
            </div>
            <button class="btn btn-primary btn-flat" id="search" type="button">查询</button>
            <script language="javascript" type="text/javascript">
                $("#search").click(function () {
                    location.replace("/admin_back/userListAll.action?typeId=" + $("#typeId").val() + "&keyword=" + $("#keyword").val());
                });
            </script>
        </div>
        <table id="datatable" class="table table-bordered dataTable"
               aria-describedby="datatable_info">
            <thead>
            <tr role="row">
                <th class="sorting_asc" rowspan="1" colspan="1" style="width: 80px;">
                    UID
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 100px;">
                    用户昵称
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 100px;">
                    邮箱
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 80px;">
                    手机
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 150px;">
                    注册时间
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 200px;">
                    登录IP地址
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 150px;">
                    最后登录时间
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 80px;">
                    经验值
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 80px;">
                    福分
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 80px;">
                    余额
                </th>
                <th class="sorting" rowspan="1" colspan="1" style="width: 120px;">
                    操作
                </th>
            </tr>
            </thead>

            <tbody role="alert" aria-live="polite" aria-relevant="all">
            <s:iterator value="userList" var="users" status="st">
                <s:if test="#st.odd == true">
                    <tr class="gradeA odd">
                        <td class="  sorting_1">${users.userId }</td>
                        <td class=" ">${users.userName }</td>
                        <td class=" ">${users.mail }</td>
                        <td class="center ">${users.phone }</td>
                        <td class="center ">${users.oldDate }</td>
                        <td class="center ">${users.ipLocation }(${users.ipAddress })</td>
                        <td class="center ">${users.newDate }</td>
                        <td class="center ">${users.experience }</td>
                        <td class="center ">${users.experience }</td>
                        <td class="center ">${users.balance }</td>
                        <td class="center ">
                            <a href="javascript:alert('无权修改');">
                                <button class="btn btn-warning btn-flat" type="button">修改</button>
                            </a>
                            <button class="btn btn-danger btn-flat" type="button">删除</button>
                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr class="gradeA even">
                        <td class="  sorting_1">${users.userId }</td>
                        <td class=" ">${users.userName }</td>
                        <td class=" ">${users.mail }</td>
                        <td class="center ">${users.phone }</td>
                        <td class="center ">${users.oldDate }</td>
                        <td class="center ">${users.ipLocation }(${users.ipAddress })</td>
                        <td class="center ">${users.newDate }</td>
                        <td class="center ">${users.experience }</td>
                        <td class="center ">${users.experience }</td>
                        <td class="center ">${users.balance }</td>
                        <td class="center ">
                            <a href="javascript:alert('无权修改');">
                                <button class="btn btn-warning btn-flat" type="button">修改</button>
                            </a>
                            <button class="btn btn-danger btn-flat" type="button">删除</button>
                        </td>
                    </tr>
                </s:else>
            </s:iterator>
            </tbody>
        </table>
        ${pageString }
    </div>
</div>
</body>
</html>
