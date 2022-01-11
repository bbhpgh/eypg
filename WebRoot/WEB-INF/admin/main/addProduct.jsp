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
    <script type="text/javascript" src="/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="/ueditor/editor_all.js"></script>
    <script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
</head>

<body>
<div class="block-flat">
    <div class="content">
        <c:choose>
        <c:when test="${product.productId!=null}">
        <form action="/admin_back/update.action" method="post" enctype="multipart/form-data" style="border-radius: 0px;"
              class="form-horizontal group-border-dashed">
            <input type="hidden" name="product.productId" value="${product.productId }"/>
            <input type="hidden" name="backUrl" value="${backUrl }"/>
            <input type="hidden" name="product.productRealPrice" value="${product.productRealPrice }"/>
            <input type="hidden" name="product.status" value="${product.status }"/>
            <input type="hidden" name="product.attribute71" value="${product.attribute71 }"/>
            </c:when>
            <c:otherwise>
            <form action="/admin_back/addProduct.action" method="post" enctype="multipart/form-data"
                  style="border-radius: 0px;" class="form-horizontal group-border-dashed">
                <input type="hidden" name="product.productId" value=""/>
                <input type="hidden" name="backUrl" value="${backUrl }"/>
                <input type="hidden" name="product.productRealPrice" value="0"/>
                <input type="hidden" name="product.status" value="0"/>
                <input type="hidden" name="product.attribute71" value="1"/>
                </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品名称</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="请输入商品名称" class="form-control" name="product.productName"
                               value="${product.productName }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品价格</label>
                    <div class="input-group" style="width: 180px; float: left;padding-left: 15px;">
                        <span class="input-group-addon">￥</span>
                        <input type="text" class="form-control" name="product.productPrice"
                               value="${product.productPrice }"/>
                        <span class="input-group-addon">.00</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品标题</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="请输入商品标题" class="form-control" name="product.productTitle"
                               value="${product.productTitle }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品类型</label>
                    <div class="col-sm-6" style="width: 180px;">
                        <select name="product.style" class="form-control">
                            <option value="">请选择</option>
                            <s:if test="product.style=='goods_xp'">
                                <option selected="selected" value="goods_xp">新品</option>
                                <option value="goods_tj">推荐</option>
                                <option value="goods_rq">人气</option>
                                <option value="goods_xs">限时</option>
                            </s:if>
                            <s:elseif test="product.style=='goods_tj'">
                                <option selected="selected" value="goods_tj">推荐</option>
                                <option value="goods_xp">新品</option>
                                <option value="goods_rq">人气</option>
                                <option value="goods_xs">限时</option>
                            </s:elseif>
                            <s:elseif test="product.style=='goods_rq'">
                                <option selected="selected" value="goods_rq">人气</option>
                                <option value="goods_tj">推荐</option>
                                <option value="goods_xp">新品</option>
                                <option value="goods_xs">限时</option>
                            </s:elseif>
                            <s:elseif test="product.style=='goods_xs'">
                                <option selected="selected" value="goods_xs">限时</option>
                                <option value="goods_rq">人气</option>
                                <option value="goods_tj">推荐</option>
                                <option value="goods_xp">新品</option>
                            </s:elseif>
                            <s:else>
                                <option value="goods_xp">新品</option>
                                <option value="goods_tj">推荐</option>
                                <option value="goods_rq">人气</option>
                                <option value="goods_xs">限时</option>
                            </s:else>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品分类</label>
                    <div class="col-sm-6" style="width: 180px;">
                        <select name="product.productType" class="form-control">
                            <option value="">请选择</option>
                            <s:iterator value="productTypeList" var="productTypes">
                                <c:choose>
                                    <c:when test="${product.productType==productTypes.typeId }">
                                        <option selected="selected"
                                                value="${productTypes.typeId }">${productTypes.typeName }</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${productTypes.typeId }">${productTypes.typeName }</option>
                                    </c:otherwise>
                                </c:choose>
                            </s:iterator>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品主图片</label>
                    <div class="col-sm-6" style="width: 180px;">
                        <input name="myFile" class="form-control" id="myFile" accept="image/*" type="file"/>
                    </div>
                    <c:if test="${product.headImage!=null}">
                        <div>
                            <img src="<%=url.getString("img")%>/${product.headImage }"/>
                            <input type="hidden" name="product.headImage" value="${product.headImage }"/>
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">商品详情</label>
                    <div class="col-sm-6">
                        <textarea id="editor" name="product.productDetail">${product.productDetail }</textarea>
                        <script type="text/javascript">
                            UE.getEditor('editor');
                        </script>
                    </div>
                </div>

                <button class="btn btn-success" type="submit" id="submit"><i class="fa fa-cloud-download"></i> 提交
                </button>
                <button class="btn btn-primary btn-flat" onclick="history.go(-1)" type="button">返回</button>
            </form>
    </div>
</div>
<script type="text/javascript">
    $("#submit").click(function () {
        if ($("[name='product.productName']").val() == "") {
            alert("请输入商品名称！");
            return false;
        }
        if ($("[name='product.productPrice']").val() == "") {
            alert("请输入商品价格！");
            return false;
        }
        if ($("[name='product.productTitle']").val() == "") {
            alert("请输入商品标题！");
            return false;
        }
        if ($("[name='product.productType']").val() == "") {
            alert("请选择商品类型！");
            return false;
        }
        if ($("[name='product.productId']").val() == "") {
            if ($("[name='myFile']").val() == "") {
                alert("请给该商品添一张主图片！");
                return false;
            }
        }
        $("form:first").submit();
    });
</script>

</body>
</html>
