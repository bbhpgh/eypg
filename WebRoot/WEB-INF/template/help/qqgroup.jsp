<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.eypg.util.ApplicationListenerImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="index_template"/>
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_帮助中心
        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>
        网</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/qqgroup.css"/>

</head>

<body>
<div class="help-main">
    <div class="qqGroup">
        <div class="qqTitle">
            <span class="groupt"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %><b>QQ</b>群</span>
            <span>为打造更具公平、透明的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>平台，<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>特成立各地QQ交流群（可在选择框查找本地群），欢迎广大拍友加入。<br/>
							另外，<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>正在招募各地群主加盟，也可自建群，具体待遇和要求请咨询QQ:52013594
					</span>
        </div>

        <div class="qqList" id="listTopContents">
            <p>
                直属群
            </p>
            <ul>
                <li>
                    <dt>
                        <img border="0" width="45px;" height="45px;" src="/Images/qqgroup.png"
                             alt="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>官方001群[s:已满]"/>
                    </dt>
                    <dt>
                        <%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>官方001群
                        <em>已满</em>
                    </dt>
                    <dd>

                    </dd>
                </li>
            </ul>
        </div>
        <div class="qsearch">
            <ul>
                <li>
                    <label>
                        筛选：
                    </label>
                </li>
                <li>
                    <dd>
                        <span id="sltAreaAID">----请选择----</span><b></b>
                    </dd>
                    <div class="sel" id="itemAreaAID"
                         style="height: 220px; display: none;">
                        <a no="0" href="javascript:void(0);">----请选择----</a><a no="7"
                                                                               href="javascript:void(0);">广东省</a><a
                            no="3"
                            href="javascript:void(0);">北京</a><a no="28"
                                                                href="javascript:void(0);">天津</a><a no="11"
                                                                                                    href="javascript:void(0);">河北省</a><a
                            no="24"
                            href="javascript:void(0);">山西省</a><a no="20"
                                                                 href="javascript:void(0);">内蒙古自治区</a><a no="19"
                                                                                                         href="javascript:void(0);">辽宁省</a><a
                            no="16"
                            href="javascript:void(0);">吉林省</a><a no="13"
                                                                 href="javascript:void(0);">黑龙江省</a><a no="26"
                                                                                                       href="javascript:void(0);">上海</a><a
                            no="17"
                            href="javascript:void(0);">江苏省</a><a no="32"
                                                                 href="javascript:void(0);">浙江省</a><a no="2"
                                                                                                      href="javascript:void(0);">安徽省</a><a
                            no="5"
                            href="javascript:void(0);">福建省</a><a no="18"
                                                                 href="javascript:void(0);">江西省</a><a no="23"
                                                                                                      href="javascript:void(0);">山东省</a><a
                            no="12"
                            href="javascript:void(0);">河南省</a><a no="14"
                                                                 href="javascript:void(0);">湖北省</a><a no="15"
                                                                                                      href="javascript:void(0);">湖南省</a><a
                            no="8"
                            href="javascript:void(0);">广西壮族自治区</a><a no="10"
                                                                     href="javascript:void(0);">海南省</a><a no="4"
                                                                                                          href="javascript:void(0);">重庆</a><a
                            no="27"
                            href="javascript:void(0);">四川省</a><a no="9"
                                                                 href="javascript:void(0);">贵州省</a><a no="31"
                                                                                                      href="javascript:void(0);">拍南省</a><a
                            no="29"
                            href="javascript:void(0);">西藏自治区</a><a no="25"
                                                                   href="javascript:void(0);">陕西省</a><a no="6"
                                                                                                        href="javascript:void(0);">甘肃省</a><a
                            no="22"
                            href="javascript:void(0);">青海省</a><a no="21"
                                                                 href="javascript:void(0);">宁夏回族自治区</a><a no="30"
                                                                                                          href="javascript:void(0);">新疆维吾尔自治区</a>
                    </div>
                </li>
                <li>
                    <dd>
                        <span id="sltAreaBID">----请选择----</span><b></b>
                    </dd>
                    <div class="sel" id="itemAreaBID"
                         style="height: 22px; display: none;">
                        <a no="0" href="javascript:void(0);">----请选择----</a>
                    </div>
                </li>
                <li>
                    <dd>
                        <span id="sltAreaCID">----请选择----</span><b></b>
                    </dd>
                    <div class="sel" id="itemAreaCID"
                         style="height: 22px; display: none;">
                        <a no="0" href="javascript:void(0);">----请选择----</a>
                    </div>
                </li>
            </ul>
            <span>地方群</span>
        </div>
        <div class="qqList" id="listContents">
            <ul>
                <li>
                    <dt>
                        <img border="0" width="45px;" height="45px;" src="/Images/qqgroup.png"
                             alt="一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>-铜川总群"/>
                    </dt>
                    <dt>
                        一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>-铜川总群
                    </dt>
                    <dd>

                    </dd>
                </li>
            </ul>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/qqgroup.js"></script>
</body>
</html>
