<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/global.css" rel="stylesheet" type="text/css"/>
    <link href="/admin_css/admin_style.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="header-title lr10">
    <b>在线升级</b> <span class="lr10"> <font color="red">升级前请做好数据备份,请下载更新文件替换所有对应文件夹文件，替换完成后请重启tomcat生效。
	</font> </span>
</div>
<div class="bk10"></div>
<div class="header-data lr10">
    系统版本: V5.1.8 <span class="lr10">&nbsp;</span>
    升级时间: 20140712 <span class="lr10">&nbsp;</span>
</div>
<div class="bk10"></div>
<div class="table-list lr10">
    <!--start-->
    <table width="100%" cellspacing="0">
        <thead>
        <tr>
            <th width="100px" align="center">可升级文件</th>
            <th width="100px" align="center">编码方式</th>
            <th width="100px" align="center">状态</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>暂无可升级文件。。。</td>
        </tr>
        <!--		<tr>-->
        <!--            <td width="100px" align="center">patch_20140712_6655.zip</td>-->
        <!--			<td align="center">utf-8</td>-->
        <!--            <td align="center"><font color="#0c0">可升级</font></td>-->
        <!--		</tr>-->
        </tbody>
    </table>
    <div class="btn_paixu">
        <form method="post" action="">
            <input type="submit" value=" 开始升级 " name="submit" class="button"/>
        </form>
    </div>
</div><!--table-list end-->
<script>
</script>

</body>
</html>
