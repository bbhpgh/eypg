<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>会员登出_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
    </title>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery-1.4.4-min.js"></script>
    <script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1434403650" type="text/javascript"
            charset="utf-8"></script>
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="100363700"
            data-redirecturi="http://<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/qc_callback.html"
            charset="utf-8"></script>
</head>

<body>
Logging out ...
<script type="text/javascript">
    var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
    var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
    var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
    var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
    $(document).ready(function () {
        function getCookie(name)//取cookies函数
        {
            var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
            if (arr != null) return unescape(arr[2]);
            return null;
        }

        function delCookie(name)//删除cookie
        {
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cval = getCookie(name);
            if (cval != null) document.cookie = name + "=" + cval + ";id=1ypg;path=/;expires=" + exp.toGMTString() + ";domain=" + $domain;
        }

        function logout() {
            window.location.href = $www;
        }

        var loginType = getCookie("loginType");
        if (loginType == 0) {
            delCookie("userId");
            delCookie("phone");
            delCookie("mail");
            delCookie("loginType");
            setTimeout(logout, 1500);
        }
        if (loginType == 1) {
            WB2.logout(function () {
            });
            if (WB2.checkLogin() == false) {
                delCookie("userId");
                delCookie("mail");
                delCookie("loginType");
                setTimeout(logout, 1500);
            }
        }
        if (loginType == 2) {
            QC.Login.signOut();
            if (QC.Login.check() == false) {
                delCookie("userId");
                delCookie("mail");
                delCookie("loginType");
                setTimeout(logout, 1500);
            }
        }
        if (loginType == null) {
            delCookie("userId");
            delCookie("phone");
            delCookie("mail");
            delCookie("loginType");
            setTimeout(logout, 1500);
        }
    });
</script>
</body>
</html>
