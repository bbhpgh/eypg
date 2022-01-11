<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body name="sysInfo">
<div class="page-head">
    <h2>系统概括</h2>
    <div class="cl-mcont">
        <div class="stats_bar">
            <div class="butpro butstyle flat">
                <a href="/admin_back/userListAll.action">
                    <div class="sub"><h2>会员总数</h2><span id="total_clientes">载入中</span></div>
                </a>
            </div>
            <div class="butpro butstyle flat">
                <div class="sub"><h2>购买总数</h2><span id="buyCount">载入中</span></div>
            </div>
            <div class="butpro butstyle flat">
                <a href="/admin_back/index.action?id=hot20">
                    <div class="sub"><h2>在售商品数</h2><span id="productCount">载入中</span></div>
                </a>
            </div>
            <div class="butpro butstyle flat">
                <a href="/admin_back/latestList.action">
                    <div class="sub"><h2>揭晓商品</h2><span id="lotteryCount">载入中</span></div>
                </a>
            </div>
            <div class="butpro butstyle flat">
                <a href="/admin_back/shareList.action?typeId=hot20">
                    <div class="sub"><h2>晒单总数</h2><span id="shareCount">载入中</span></div>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="cl-mcont">
    <div class="row">
        <div class="col-sm-6 col-md-6">
            <div class="block-flat">
                <div class="header">
                    <h3>系统信息</h3>
                </div>
                <div class="content">
                    <h6>网站名称：<%=url.getString("saitName")%>
                    </h6>
                    <h6>网站域名：<%=url.getString("www")%>
                    </h6>
                    <h6>网站关键词：<%=url.getString("keyword")%>
                    </h6>
                    <h6>网站描述：<%=url.getString("description")%>
                    </h6>
                    <h6>系统邮箱：<%=url.getString("mailName")%>
                    </h6>
                    <h6>系统邮箱密码：<%=url.getString("mailPwd")%>
                    </h6>
                    <h6>网站备案编号：<%=url.getString("icp")%>
                    </h6>
                    <h6>财付通商户号：<%=url.getString("tenpayPartner")%>
                    </h6>
                    <h6>财付通初始密钥：<%=url.getString("tenpayKey")%>
                    </h6>
                    <h6>支付宝商户号：<%=url.getString("alipayPartner")%>
                    </h6>
                    <h6>支付宝初始密钥：<%=url.getString("alipayKey")%>
                    </h6>
                    <h6>支付宝帐户：<%=url.getString("alipayMail")%>
                    </h6>
                    <div class="spacer">&nbsp;</div>
                </div>
            </div>

        </div>

        <div class="col-sm-6 col-md-6">
            <div class="block-flat">
                <div class="header">
                    <h3>版权信息</h3>
                </div>
                <div class="content">
                    <h4>欢迎使用1元拍购源码，本源码为 java 语言编写，拥有最好的安全性能，程序可部署集群服务，不怕人气爆棚，该程序不邦定域名，不做任何限制，
                        可永久使用更新，您有任何好的意见可与作者联系，我们共同不断完善系统，程序提供永久升级服务。我们可定制开发功能，如果你有需要请联系我们。（按功能复杂成度收费）
                    </h4>
                    <p></p>
                    <p class="text-right">1元拍购</p>
                    <h4>www.1ypg.com</h4>
                    <p class="lead">感谢您的支持，我们会用心做的更好！</p>
                    <p class="text-right">购买程序请联系QQ：65615609</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $.ajax({
        url: "/admin_back/numberCount.action",
        success: function (data) {
            if (data != null) {
                data = data.split("_");
                $("#total_clientes").text(data[0]);
                $("#buyCount").text("￥" + data[1]);
                $("#productCount").text(data[2]);
                $("#lotteryCount").text(data[3]);
                $("#shareCount").text(data[4]);
            }
        },
        error: function () {
        }
    });
</script>
</body>
</html>
