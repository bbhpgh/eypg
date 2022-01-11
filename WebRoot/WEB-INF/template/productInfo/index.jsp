<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>${product.productName }${product.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/header.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodsdetail.css"/>
</head>
<body id="loadingPicBlock">
<div class="Current_nav">
    <a href="/">首页</a> <span>&gt;</span> 商品详情
</div>
<!-- 商品图片 -->
<div class="show_content">
    <!-- 商品信息 -->
    <div class="Pro_Details">
        <h1>${product.productName } <span class="red">${product.productTitle }</span></h1>
        <div class="Pro_Detleft">
            <div class="detail-itemsummary-imageviewer">
                <div class="middlePicRemark" id="middlePicRemark"></div>
                <div class="middlePicBox" id="middlePicBox" style="position: relative;">
                    <span id="BigViewImage"
                          style="outline-style: none; cursor: crosshair; display: block; position: relative; height: 396px; width: 396px;">
                    <div id="onload"
                         style="width: 200px;height:50px;position: absolute;text-align: center;padding-left: 100px;padding-top: 200px;font-size: 14px;display: none;">正在努力加载图片...</div>
                    <c:if test="${fn:length(productimageList)!=0}">
                        <s:iterator value="productimageList" var="productimages" begin="0" end="0">
                            <img id="imgGoodsPic"
                                 src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }"
                                 style="width:396px; height:396px;" alt=""
                                 rel="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }"
                                 class="jqzoom"/>
                        </s:iterator>
                    </c:if>
                    </span></div>
                <div class="smallPicList">
                    <div class="hidden" style="display: none;"></div>
                    <div class="jcarousel-clip">
                        <ul id="mycarousel" style="width: 324px; left: 0px; display: block;">
                            <s:iterator value="productimageList" var="productimages">
                                <li><img width="48" height="48"
                                         src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }_small${productimages.imageType }"
                                         big="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }${productimages.imageType }"/>
                                </li>
                            </s:iterator>
                            <div class="hackbox"></div>
                        </ul>
                    </div>
                    <div class="hidden" style="display: none;"></div>
                </div>
            </div>
            <div class="Pro_GetPrize">
            </div>
        </div>
        <div class="Pro_Detright">
            <p class="Det_money">价值：<span class="rmbgray"
                                          style="color: #f60000;font-size: 18px;">${product.productPrice }.00</span></p>
            <p class="Det_money">商品编号：${product.productId }</p>

            <div class="Progress-bar">
            </div>
            <p class="Pro_Detsingle">
            </p>
            <div class="Det_button" id="divBuy">
                <a class="Det_Shopbut" prId="${product.productId }" price="${product.productPrice }" name="goPay"
                   href="javascript:;">立即购买</a>
            </div>
            <div class="Security">

            </div>

        </div>
    </div>
</div>
<!-- 商品图片结束 -->
<div class="ProductTabNav">
    <div class="DetailsT_Tit" id="divProductNav">
        <div class="DetailsT_TitP">
            <ul>
                <li class="Product_DetT DetailsTCur"><span class="DetailsTCur">商品详情</span></li>
                <!--			        <li class="Single_ConT"><span class="">晒单</span></li>-->
            </ul>
        </div>
    </div>
</div>
<!-- 商品详细介绍 -->
<div class="Product_Content" id="divContent">
    <div class="Product_Con">
        ${product.productDetail }
        <div style="clear: both"></div>
    </div>

</div>
<!-- 商品详细介绍结束 -->
<!-- 所有参与记录 -->
<div class="AllRecord_Content" id="divBuyRecord" style="display: none;">
    <div class="goods_nodata" style="display: none;">暂无参与记录</div>
    <div class="goods_loding" style="display: none;"><img border="0"
                                                          src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/goods_loading.gif"
                                                          alt=""/>正在加载......
    </div>
    <div id="userByListDIV">
    </div>
    <div class="pages" name="bitem">
        <ul class="pageULEx" id="pagination">
        </ul>
    </div>
</div>
<!-- 所有参与记录结束 -->
<!-- 人气推荐 -->
<!--	<div class="list_goodsTj" id="divGoodsRec">-->
<!--    <div class="list_goodsTjTit">人气推荐</div>-->
<!--    <div class="list_goodsTjCon" id="divRecommend">-->
<!--	    <div class="Roll_Left"><a href="javascript:;" name="left" class="Roll_enabled">&lt;</a></div>-->
<!--	    <div class="Roll_Con">-->
<!--	        <ul id="ulRecommend" style="width: 1820px; left: 0px;">-->
<!--	        </ul>-->
<!--	    </div>-->
<!--	    <div class="Roll_Right"><a href="javascript:;" name="right" class="Roll_enabled">&gt;</a></div>-->
<!--    </div>-->
<!--</div>-->
<!-- 人气推荐结束 -->
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/productinfo.js"></script>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.imagezoom.js"></script>
</body>
</html>