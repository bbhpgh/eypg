<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title>晒单分享_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/sharelist.css"/>
</head>

<body class="share">
<input type="hidden" id="topid" value="${id }"/>
<div class="Current_nav">
    <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>">首页</a> &gt; 晒单分享
</div>
<div class="share_Curtit" id="current">
    <h1 class="fl">晒单分享</h1>
    <span id="spTotalCount">(共<em class="orange">${resultCount}</em>个幸运获得者晒单)</span>
</div>
<div class="list_Sort">
    <dl>
        <dt>
            排序
        </dt>
        <dd>
            <a id="new" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/new20.html">最新晒单</a><a
                id="popularity"
                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/hot20.html">人气晒单</a><a
                id="comment"
                href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/share/reply20.html">评论最多</a>
        </dd>
    </dl>
</div>
<div id="loadingPicBlock" class="share_list">
    <div class="goods_share_listC" id="container">
        <ul>
            <li>
                <c:forEach var="ShareJSONs" items="${ShareJSONList}" varStatus="status" begin="0" end="4">

                    <div class="share_list_content">
                        <dl>
                            <dt>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                   target="_blank"><img class="scrollLoading"
                                                        data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/220/${ShareJSONs.shareImages }"
                                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"
                                                        alt="${ShareJSONs.shareTitle }"/>
                                </a>
                            </dt>
                            <dd class="share-name gray02">
                                <a class="name-img" target="_blank"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"><img
                                        border="0" alt=""
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${ShareJSONs.userFace }"/>
                                </a>
                                <div class="share-name-r">
												<span class="gray03"> <a class="blue"
                                                                         href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"
                                                                         target="_blank"
                                                                         rel="nofollow">${ShareJSONs.userName}</a>${ShareJSONs.shareDate}
												</span><a target="_blank"
                                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                          class="Fb gray01">${ShareJSONs.shareTitle }</a>
                                </div>
                            </dd>
                            <dd class="share_info gray01">
                                    ${ShareJSONs.shareContent }
                            </dd>
                            <dd class="message hidden" style="display: block;">
											<span class="smile gray03"><i></i><b>羡慕(<em
                                                    num="${ShareJSONs.uid }">${ShareJSONs.upCount }</em>)</b>
											</span>
                                <span class="much"> <a class="gray03"
                                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                       rel="nofollow"
                                                       target="_blank"><i></i>评论<em>(${ShareJSONs.replyCount})</em>
											</a>
											</span>
                            </dd>
                        </dl>
                        <p class="text-h10"></p>
                    </div>
                </c:forEach>
            </li>
            <li>
                <c:forEach var="ShareJSONs" items="${ShareJSONList}" varStatus="status" begin="5" end="9">

                    <div class="share_list_content">
                        <dl>
                            <dt>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                   target="_blank"><img class="scrollLoading"
                                                        data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/220/${ShareJSONs.shareImages }"
                                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"
                                                        alt="${ShareJSONs.shareTitle }"/>
                                </a>
                            </dt>
                            <dd class="share-name gray02">
                                <a class="name-img" target="_blank"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"><img
                                        border="0" alt=""
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${ShareJSONs.userFace }"/>
                                </a>
                                <div class="share-name-r">
												<span class="gray03"> <a class="blue"
                                                                         href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"
                                                                         target="_blank"
                                                                         rel="nofollow">${ShareJSONs.userName}</a>${ShareJSONs.shareDate}
												</span><a target="_blank"
                                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                          class="Fb gray01">${ShareJSONs.shareTitle }</a>
                                </div>
                            </dd>
                            <dd class="share_info gray01">
                                    ${ShareJSONs.shareContent }
                            </dd>
                            <dd class="message hidden" style="display: block;">
											<span class="smile gray03"><i></i><b>羡慕(<em
                                                    num="${ShareJSONs.uid }">${ShareJSONs.upCount }</em>)</b>
											</span>
                                <span class="much"> <a class="gray03"
                                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                       rel="nofollow"
                                                       target="_blank"><i></i>评论<em>(${ShareJSONs.replyCount})</em>
											</a>
											</span>
                            </dd>
                        </dl>
                        <p class="text-h10"></p>
                    </div>
                </c:forEach>
            </li>
            <li>
                <c:forEach var="ShareJSONs" items="${ShareJSONList}" varStatus="status" begin="10" end="14">

                    <div class="share_list_content">
                        <dl>
                            <dt>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                   target="_blank"><img class="scrollLoading"
                                                        data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/220/${ShareJSONs.shareImages }"
                                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"
                                                        alt="${ShareJSONs.shareTitle }"/>
                                </a>
                            </dt>
                            <dd class="share-name gray02">
                                <a class="name-img" target="_blank"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"><img
                                        border="0" alt=""
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${ShareJSONs.userFace }"/>
                                </a>
                                <div class="share-name-r">
												<span class="gray03"> <a class="blue"
                                                                         href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"
                                                                         target="_blank"
                                                                         rel="nofollow">${ShareJSONs.userName}</a>${ShareJSONs.shareDate}
												</span><a target="_blank"
                                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                          class="Fb gray01">${ShareJSONs.shareTitle }</a>
                                </div>
                            </dd>
                            <dd class="share_info gray01">
                                    ${ShareJSONs.shareContent }
                            </dd>
                            <dd class="message hidden" style="display: block;">
											<span class="smile gray03"><i></i><b>羡慕(<em
                                                    num="${ShareJSONs.uid }">${ShareJSONs.upCount }</em>)</b>
											</span>
                                <span class="much"> <a class="gray03"
                                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                       rel="nofollow"
                                                       target="_blank"><i></i>评论<em>(${ShareJSONs.replyCount})</em>
											</a>
											</span>
                            </dd>
                        </dl>
                        <p class="text-h10"></p>
                    </div>
                </c:forEach>
            </li>
            <li class="share-liR">
                <c:forEach var="ShareJSONs" items="${ShareJSONList}" varStatus="status" begin="15" end="20">
                    <div class="share_list_content">
                        <dl>
                            <dt>
                                <a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                   target="_blank"><img class="scrollLoading"
                                                        data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/UserPost/220/${ShareJSONs.shareImages }"
                                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/pixel.gif"
                                                        alt="${ShareJSONs.shareTitle }"/>
                                </a>
                            </dt>
                            <dd class="share-name gray02">
                                <a class="name-img" target="_blank"
                                   href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"><img
                                        border="0" alt=""
                                        src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${ShareJSONs.userFace }"/>
                                </a>
                                <div class="share-name-r">
												<span class="gray03"> <a class="blue"
                                                                         href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${ShareJSONs.userId }.html"
                                                                         target="_blank"
                                                                         rel="nofollow">${ShareJSONs.userName}</a>${ShareJSONs.shareDate}
												</span><a target="_blank"
                                                          href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                          class="Fb gray01">${ShareJSONs.shareTitle }</a>
                                </div>
                            </dd>
                            <dd class="share_info gray01">
                                    ${ShareJSONs.shareContent }
                            </dd>
                            <dd class="message hidden" style="display: block;">
											<span class="smile gray03"><i></i><b>羡慕(<em
                                                    num="${ShareJSONs.uid }">${ShareJSONs.upCount }</em>)</b>
											</span>
                                <span class="much"> <a class="gray03"
                                                       href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/shareShow/${ShareJSONs.uid }.html"
                                                       rel="nofollow"
                                                       target="_blank"><i></i>评论<em>(${ShareJSONs.replyCount})</em>
											</a>
											</span>
                            </dd>
                        </dl>
                        <p class="text-h10"></p>
                    </div>
                </c:forEach>
            </li>
        </ul>
    </div>
    <div class="pages">
        ${pageString }
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/postlist.js"></script>
<script type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery.scrollloading-min.js"></script>
</body>
</html>
