<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="decorator" content="admin_main"/>
    <title>1元拍购_管理中心</title>
    <link href="/admin_css/component.css" rel="stylesheet"/>
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
    <script language="javascript" type="text/javascript" src="/admin_js/jquery.modalEffects.js"></script>
</head>

<body>
<div class="col-sm-6 col-md-10">
    <div class="block-flat">
        <div class="header">
            <h3>系统设置</h3>
        </div>
        <div class="content">
            <form action="/admin_back/setSysInfo.action" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">网站名称</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入网站名称" name="sysInfoBean.saitName"
                               value="${sysInfoBean.saitName }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">网站域名</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入网站域名" name="sysInfoBean.saitUrl"
                               value="${sysInfoBean.saitUrl }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">网站关键词</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入网站关键词" name="sysInfoBean.keyword"
                               value="${sysInfoBean.keyword }" class="form-control parsley-validated"/>如：1元拍购,1元购物
                        多个关键词请用英文逗号分割
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">网站描述</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入网站描述" name="sysInfoBean.description"
                               value="${sysInfoBean.description }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">系统邮箱</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入系统邮箱" name="sysInfoBean.mailName"
                               value="${sysInfoBean.mailName }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">系统邮箱密码</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入系统邮箱密码" name="sysInfoBean.mailPwd"
                               value="${sysInfoBean.mailPwd }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">ICP备案号</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入ICP备案号" name="sysInfoBean.icp" value="${sysInfoBean.icp }"
                               class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">财付通商户号</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入财付通商户号" name="sysInfoBean.tenpayPartner"
                               value="${sysInfoBean.tenpayPartner }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">财付通商户密钥</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入财付通商户密钥" name="sysInfoBean.tenpayKey"
                               value="${sysInfoBean.tenpayKey }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">支付宝商户号</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入支付宝商户号" name="sysInfoBean.alipayPartner"
                               value="${sysInfoBean.alipayPartner }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">支付宝商户密钥</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入支付宝商户密钥" name="sysInfoBean.alipayKey"
                               value="${sysInfoBean.alipayKey }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">支付宝帐号</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入支付宝帐号" name="sysInfoBean.alipayMail"
                               value="${sysInfoBean.alipayMail }" class="form-control parsley-validated"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">域名域（.1ypg.com）</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入域名域" name="sysInfoBean.domain" value="${sysInfoBean.domain }"
                               class="form-control parsley-validated"/>域名域格式为(.1ypg.com)
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">CSS、JS 域名</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入CSS、JS 域名" name="sysInfoBean.skin"
                               value="${sysInfoBean.skin }" class="form-control parsley-validated"/>
                        该域名为加载CSS与JS专用二级域名，提高网站访问速度快 如: http://skin.1ypg.com
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">图片域名</label>
                    <div class="col-sm-7">
                        <input type="text" placeholder="请输入图片域名" name="sysInfoBean.img" value="${sysInfoBean.img }"
                               class="form-control parsley-validated"/>
                        该域名为加载图片专用二级域名，提交浏览器访问网站速度快 如: http://img.1ypg.com
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-primary" id="submit" type="submit">提交</button>
                        <button class="btn btn-default" onclick="history.go(-1)" type="button">返回</button>
                    </div>
                    修改系统配置文件需要重启tomcat服务才能生效
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
