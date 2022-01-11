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
    <title>${productInfo.productName }${productInfo.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/header.css"/>
    <link type="text/css" rel="stylesheet"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/goodsdetail.css"/>
</head>
<body id="loadingPicBlock" rf="2">
<input name="hidGoodsID" type="hidden" id="hidGoodsID" value="${productInfo.spellbuyProductId  }"/>
<input name="hidCodeID" type="hidden" id="hidCodeID" value="${productInfo.spellbuyProductId  }"/>
<input name="hidLogined" type="hidden" id="hidLogined" value="0"/>
<input name="hidIsEnd" type="hidden" id="hidIsEnd" value="0"/>
<input name="hidIsStart" type="hidden" id="hidIsStart" value="${productInfo.status }"/>
<input type="hidden" id="productId" value="${productInfo.spellbuyProductId  }"/>
<input type="hidden" id="resultCount" value="${resultCount }"/>
<div class="Current_nav">
    <a href="/">首页</a> <span>&gt;</span> 商品详情
</div>
<!-- 商品图片 -->
<div class="show_content">
    <!-- 商品期数 -->
    <c:choose>
    <c:when test="${fn:length(productPeriodList)>27}">
    <div class="show_Period" id="divPeriodList" style="height: 99px;">
        <div class="period_Open"><a href="javascript:;" id="btnOpenPeriod" class="gray02">展开<i></i></a></div>
        </c:when>
        <c:otherwise>
        <div class="show_Period" id="divPeriodList">
            </c:otherwise>
            </c:choose>
            <ul class="Period_list">
                <c:forEach items="${productPeriodList}" var="entry" varStatus="i">
                <c:choose>
                    <c:when test="${entry.key==productInfo.productPeriod}">
                        <li><b class="period_ArrowCur">第${productInfo.productPeriod }期</b></li>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${i.index==0 }">
                                <li>
                                    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/products/${entry.value}.html"><b
                                            class="period_Ongoing">第${entry.key }期<i></i></b></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="gray02"
                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/lotteryDetail/${entry.value}.html">第${entry.key }期</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <c:if test="${(i.index+1)%9==0 }">
            </ul>
            <ul class="Period_list">
                </c:if>
                </c:forEach>
        </div>
        <!-- 商品信息 -->
        <div class="Pro_Details">
            <h1><span>(第${productInfo.productPeriod }期)</span>${productInfo.productName } <span
                    class="red">${productInfo.productTitle }</span></h1>
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
                    <!--				    <h2>上期获得者</h2>-->
                    <!--				    <div class="GetPrize">-->
                    <!--					    <dl>-->
                    <!--						    <dt><a target="_blank" href="javascript:;" rel="nofollow"><img width="80" height="80" src="" alt=""></a></dt>-->
                    <!--						    <dd class="gray02">-->
                    <!--							    <p>恭喜 <a class="blue" target="_blank" href="javascript:;">卧槽</a> (广东省梅州市) 获得了本商品</p>-->
                    <!--							    <p>揭晓时间：2013-06-16 23:14:42.296</p>-->
                    <!--							    <p><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间：2013-06-16 22:42:12.530</p>-->
                    <!--							    <p>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<em class="orange Fb">10002031</em></p>-->
                    <!--						    </dd>-->
                    <!--					    </dl>-->
                    <!--				    </div>-->
                </div>
            </div>
            <div class="Pro_Detright">
                <p class="Det_money">价值：<span class="rmbgray">${productInfo.productPrice }.00</span></p>
                <div class="Progress-bar">
                    <p title="已完成<fmt:formatNumber type="number" value="${(productInfo.spellbuyCount/productInfo.productPrice)*100 } " maxFractionDigits="2"/> %">
                        <span style="width:<fmt:formatNumber type="number"
                                                             value="${(productInfo.spellbuyCount/productInfo.productPrice)*100 } "
                                                             maxFractionDigits="2"/>%;"></span></p>
                    <ul class="Pro-bar-li">
                        <li class="P-bar01"><em>${productInfo.spellbuyCount }</em>已参与人次</li>
                        <li class="P-bar02"><em id="CodeQuantity">${productInfo.productPrice }</em>总需人次</li>
                        <li class="P-bar03"><em
                                id="CodeLift">${productInfo.productPrice - productInfo.spellbuyCount}</em>剩余人次
                        </li>
                    </ul>
                </div>
                <p class="Pro_Detsingle">
                    <!--			        <a id="btnGoToPost" class="gray02" href="javascript:;">本商品已有<span class="Fb blue">33</span>个幸运者晒单   已有<span class="Fb blue">2719</span>条晒单评论</a>-->
                </p>
                <div class="Pro_number" id="divNumber">
                    我要<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <a class="num_del num_ban"
                                                                                       href="javascript:;">-</a><input
                        type="text" class="num_dig" maxlength="7" value="1"/><a class="num_add"
                                                                                href="javascript:;">+</a> 人次 <span
                        class="gray03" id="chance"><font color="ff6600">购买人次越多获得几率越大哦！</font></span>
                </div>
                <div class="Det_button" id="divBuy">
                    <a class="Det_Shopbut"
                       href="javascript:;">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                    </a><a class="Det_Cart" href="javascript:;"><i></i>加入购物车</a>
                </div>
                <div class="Security">
                    <ul>
                        <li><a target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuinetwo.html"><i></i>100%公平公正</a>
                        </li>
                        <li><a target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/genuine.html"><s></s>100%正品保证</a>
                        </li>
                        <li><a target="_blank"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/deliveryFees.html"><b></b>全国免费配送</a>
                        </li>
                    </ul>
                    <div class="Det_Share">
                        <!-- JiaThis Button BEGIN -->
                        <div class="jiathis_style">
                            <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt" target="_blank"><img
                                    src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/jiathis.gif"
                                    border="0"/></a>
                        </div>
                        <!-- JiaThis Button END -->
                    </div>
                </div>
                <div class="Pro_Record">
                    <ul class="Record_tit" id="ulRecordTab">
                        <li class="NewestRec Record_titCur">
                            最新<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录
                        </li>
                        <li class="MytRec">我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</li>
                        <li class="Explain orange">什么是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>？</li>
                    </ul>
                    <div class="Newest_Con">
                        <c:choose>
                            <c:when test="${fn:length(ParticipateJSONList)!=0}">
                                <ul>
                                    <s:iterator var="ParticipateJSON" value="ParticipateJSONList">
                                        <li><a target="_blank"
                                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ParticipateJSON.userId }.html"
                                               rel="nofollow"><img width="20" height="20" border="0" alt=""
                                                                   src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/${ParticipateJSON.userFace }"/></a><a
                                                class="blue" target="_blank"
                                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ParticipateJSON.userId }.html">${ParticipateJSON.userName }</a>(${ParticipateJSON.ip_location }) ${ParticipateJSON.buyDate } <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                                            了<em class="Fb gray01">${ParticipateJSON.buyCount }</em>人次
                                        </li>
                                    </s:iterator>
                                </ul>
                                <p style=""><a class="gray01" href="javascript:;" id="btnUserBuyMore">查看更多</a></p>
                            </c:when>
                            <c:otherwise>
                                <div class="NewRecord_msg"><s></s><span class="NewRecord_msgtxt"><em>居然还没有人参与？! </em>机不可失！ 大奖正在向您挥手！</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div style="display:none;" class="My_Record">
                        <div class="My_RecordReg">
                            <b class="gray01">看不到？是不是没登录或是没注册？ 登录后看看</b>
                            <a class="My_Signbut"
                               href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/login/index.html?forward=rego">登录</a><a
                                class="My_Regbut"
                                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/register/index.html?forward=rego">注册</a>
                        </div>
                    </div>
                    <div style="display:none;" class="Newest_Con">
                        <p class="MsgIntro"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                            是指只需1元就有机会买到想要的商品。即每件商品被平分成若干“等份”出售，每份1元，当一件商品所有“等份”售出后，根据<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            规则产生一名幸运者，该幸运者即可获得此商品。</p>
                        <p class="MsgIntro1"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
                            以“快乐<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
                            ，惊喜无限”为宗旨，力求打造一个100%公平公正、100%正品保障、寄娱乐与购物一体化的新型购物网站。<a target="_blank" class="blue"
                                                                                  href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/help/whatPaigou.html">查看详情&gt;&gt;</a>
                        </p>
                    </div>
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
                    <li class="All_RecordT" id="liUserBuyAll"><span class="">所有参与记录</span></li>
                    <!--			        <li class="Single_ConT"><span class="">晒单</span></li>-->
                </ul>
                <p><a class="white DetailsT_Cart" href="javascript:;" id="btnAdd2Cart"><s></s>加入购物车</a></p>
            </div>
        </div>
    </div>
    <!-- 商品详细介绍 -->
    <div class="Product_Content" id="divContent">
        <div class="Product_Con">
            ${productInfo.productDetail }
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
    <div class="list_goodsTj" id="divGoodsRec">
        <div class="list_goodsTjTit">人气推荐</div>
        <div class="list_goodsTjCon" id="divRecommend">
            <div class="Roll_Left"><a href="javascript:;" name="left" class="Roll_enabled">&lt;</a></div>
            <div class="Roll_Con">
                <ul id="ulRecommend" style="width: 1820px; left: 0px;">
                </ul>
            </div>
            <div class="Roll_Right"><a href="javascript:;" name="right" class="Roll_enabled">&gt;</a></div>
        </div>
    </div>
    <!-- 人气推荐结束 -->
    <script type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagination.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/goodsdetail.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.imagezoom.js"></script>
</body>
</html>