var theAddressNum = 0;
var listItems = null;
var addresslistDiv = null;
var divConsignee = null;
var userId = $("#userId").val();
$(document).ready(function () {
    var a = $skin;
    var b = function () {
        addresslistDiv = $("#addressListDiv");
        divConsignee = $("#div_consignee");
        isLoaded = true;
        getAddressListData();
        $("#btnAddnewAddr").click(function () {
            checkIsAddnewAddress(this);
        });
    };
    b();
});

function checkIsAddnewAddress(a) {
    if (theAddressNum < 4) {
        $(a).hide();
        addressAddnew();
    } else {
        $.PageDialog("<dl class=\"sAltFail\"><dd>对不起，最多只能录入4个收货地址，请删除其中的某个收货地址！</dd></dl>", {W: 430, H: 50});
    }
}

function getTrimVal(a) {
    return $("#" + a).val();
}

var ListHeadHtml = "<ul class=\"listTitle tdTitle\"><li class=\"pad\">详细地址</li><li class=\"wid55\">邮政编码</li><li class=\"wid70\">收货人</li><li class=\"wid110\">电话号码</li><li class=\"wid80\">&nbsp;</li><li class=\"wid70\">操作</li></ul>";

function getAddressListData() {
    if (!isLoaded) {
        return;
    }
    $.ajax({
        url: "/user/getAddressList.html",
        data: "userId=" + userId,
        success: function (data) {
            data = eval('(' + data + ')');
            var d = true;
            var b = "";
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    b += "<ul class=\"" + (data[i].status == 1 ? "liBg" : "") + "\">";
                    b += "<li class=\"pad\">" + data[i].province + "," + data[i].city + "," + data[i].district + "," + data[i].address + "</li>";
                    b += "<li class=\"wid55\">" + data[i].zipCode + "</li>";
                    b += "<li class=\"wid70\">" + data[i].consignee + "</li>";
                    b += "<li class=\"wid110\">" + data[i].phone + "</li>";
                    if (data[i].status == 1) {
                        b += "<li class=\"wid80 orange\">默认地址</li>";
                    } else {
                        b += "<li class=\"wid80 lightBlue\"><a class=\"blue\" href=\"javascript:;\" onclick=\"javascript:addressSetDefault(" + data[i].id + ");\">设为默认地址</a></li>\n";
                    }
                    b += "<li class=\"wid70\">" + (data[i].status == 1 ? "" : " <a href=\"javascript:;\" onclick=\"javascript:addressDelete(" + data[i].id + ");\" title=\"删除\">删除</a>") + "</li>";
                    b += "</ul>";
                }
                if (d) {
                    theAddressNum = data.length;
                    $("#btnAddnewAddr").show();
                    addresslistDiv.html(ListHeadHtml + b);
                    addresslistDiv.show();
                } else {
                    addresslistDiv.empty();
                    theAddressNum = 0;
                    isLoaded = true;
                    addressAddnew();
                }
            }
        }
    });
}

function StringBuilder(a) {
    this.s = new Array(a);
    this.append = function (b) {
        this.s.push(b);
        return this;
    };
    this.toString = function () {
        return this.s.join("");
    };
    this.clear = function () {
        this.s = new Array();
    };
    this.appendFormat = function () {
        var d = {
            object: function (c, e) {
                return c[1][e];
            }, string: function (c, e) {
                return c[e - 0 + 1];
            }
        };
        var b = arguments.length;
        if (b == 0) {
            return this;
        }
        var l = arguments[0];
        if (b == 1) {
            return this.append(l);
        }
        var p = arguments[1];
        if (p == null) {
            p = "";
        }
        var j, m, o, g, h = d[typeof (p)];
        for (j = 0; j < l.length;) {
            o = l.charAt(j);
            if (o == "{") {
                m = l.indexOf("}", j);
                g = l.substring(j + 1, m);
                this.s.push(h(arguments, g));
                j = m + 1;
                continue;
            }
            this.s.push(o);
            j++;
        }
        return this;
    };
}

function JSPanel(b) {
    this.Template = null;
    this.DataSource = null;
    var a = function (c, e) {
        var d = new StringBuilder();
        d.appendFormat(c, e);
        return d.toString();
    };
    this.DataBind = function () {
        $("#" + b).html(a(this.Template, this.DataSource));
    };
}

function requireFieldValid(a, c, b) {
    return getTrimVal(a) == "" ? $("#" + c).attr("class", "orange").html(" <s></s> " + b) : $("#" + c).attr("class", "red").html(" *");
}

function regularExpressionValid(b, a, e, c) {
    var d = new RegExp(a, "g");
    if (d.test(getTrimVal(b))) {
        $("#" + e).attr("class", "red").html(" *");
        return true;
    } else {
        $("#" + e).attr("class", "orange").html(c);
        return false;
    }
}

var SHIPNAME_REGULAR_EXPRESSION = "^[\\w\\u4e00-\\u9fa5]{1,20}$";
var SHIP_MB_REGULAR_EXPRESSION = "^1+[0-9]{10}$";
var SHIP_TEL_REGULAR_EXPRESSION = "^(0[0-9]{2,3}-)+([2-9][0-9]{6,7})+(-[0-9]{1,4})?$";
var SHIP_ZIP_REGULAR_EXPRESSION = "^(\\d{6})?$";
var MSG_SHIPADDRESS_FORMAT_ERROR = " 必填";
var MSG_SHIPNAME_EMPTY = " 必填";
var SHIP_MB_FORMAT_ERROR = " <s></s>请正确输入手机号码";
var SHIP_TEL_FORMAT_ERROR = " <s></s>请正确输入电话号码(区号、号码必填，用“-”隔开)";
var CONSIGNEE_COM_TEMPLATE = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td><label>所在地区：</label></td><td><select id=\"sel_areaA\" class=\"szxq\"><option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option></select><select id=\"sel_areaB\"><option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option></select><select id=\"sel_areaC\"><option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option></select><em id=\"region_id_msg_valid\" class=\"red\">*</em></td></tr><tr><td><label>街道地址：</label></td><td><input type=\"text\" class=\"street\" maxlength=\"50\" id=\"txt_ship_address\" value=\"{shipAddress}\"><em id=\"ship_address_valid_msg\" class=\"red\"> *</em> <span class=\"gray02\">(不需要重复填写省/市/区)</span></td></tr><tr><td><label>邮政编码：</label></td><td><input type=\"text\" maxlength=\"6\" id=\"txt_ship_zip\" class=\"inputTxt\" value=\"{shipZip}\"/> <font><a href=\"http://alexa.ip138.com/post/Search.aspx\" class=\"blue\" target=\"_blank\">邮编查询</a></font><em class=\"red\" id=\"ship_zip_valid_msg\"></em></td></tr><tr><td><label>收货人：</label></td><td><input type=\"text\" maxlength=\"20\" id=\"txt_ship_name\" class=\"inputTxt\" value=\"{shipName}\"/><em class=\"red\" id=\"ship_name_valid_msg\"> *</em></td></tr><tr><td><label>手机号码：</label></td><td><input type=\"text\" id=\"txt_ship_mb\" value=\"{shipMobile}\" class=\"inputTxt\" maxlength=\"11\"><em id=\"ship_mb_valid_msg\" class=\"red\">  *</em></td></tr>";
var CONSIGNEE_EDITABLE_TEMPLATE = "<dl>修改收货地址</dl>" + CONSIGNEE_COM_TEMPLATE + "<tr><td>&nbsp;</td><td><input type=\"button\" id=\"btn_consignee_save\" class=\"orangebut\" value=\"保存\" title=\"保存\"> <input type=\"button\" class=\"cancelBtn\" id=\"btn_consignee_cancle\" value=\"取消\" title=\"取消\"></td></tr></table>";
var CONSIGNEE_ADDNEW_TEMPLATE = "<dl>添加收货地址</dl>" + CONSIGNEE_COM_TEMPLATE + "<tr><td>&nbsp;</td><td><input type=\"button\" class=\"orangebut\" id=\"btn_consignee_save\" value=\"保存\" title=\"保存\"> <input type=\"button\" class=\"cancelBtn\" value=\"取消\" id=\"btn_consignee_cancle\" title=\"取消\"></td></tr></table>";

function Region() {
    var h = $("#sel_areaA");
    var g = $("#sel_areaB");
    var f = $("#sel_areaC");
    g.live("change", function () {
        d();
    });
    f.live("change", function () {
        $("#txt_ship_zip").val("");
    });
    h.live("change", function () {
        a();
    });
    var a = function () {
        var i = $("#sel_areaA").val();
        $.ajax({
            url: "/user/getCityList.html",
            data: "id=" + i,
            success: function (data) {
                data = eval('(' + data + ')');
                var html = "<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>";
                for (var i = 0; i < data.length; i++) {
                    html += "<option value=\"" + data[i][0] + "\">" + data[i][1] + "</option>";
                }
                $("#sel_areaB").html(html);
                $("#sel_areaC").html("<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>");
            },
            error: function () {
                alert("获取地区失败，请稍后再试！");
            }
        });
    };
    var d = function () {
        var i = $("#sel_areaB").val();
        var itext = $("#sel_areaB option:selected").text();
        $.ajax({
            url: "/user/getDistrictList.html",
            data: "id=" + i,
            success: function (data) {
                data = eval('(' + data + ')');
                var html = "<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>";
                if (data == "") {
                    html += "<option value=\"" + i + "\">" + itext + "</option>"
                } else {
                    for (var i = 0; i < data.length; i++) {
                        html += "<option value=\"" + data[i][0] + "\">" + data[i][1] + "</option>";
                    }
                }
                $("#sel_areaC").html(html);
            },
            error: function () {
                alert("获取地区失败，请稍后再试！");
            }
        });
    };
    this.setValue = function (k, j, i) {
        $.ajax({
            url: "/user/getProvinceList.html",
            success: function (data) {
                data = eval('(' + data + ')');
                var html = "<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>";
                for (var i = 0; i < data.length; i++) {
                    html += "<option value=\"" + data[i][0] + "\">" + data[i][1] + "</option>";
                }
                $("#sel_areaA").html(html);
            },
            error: function () {
                alert("获取地区失败，请稍后再试！");
            }
        });
    };
    this.getValue = function () {
        var k = $("#sel_areaA").val();
        var j = $("#sel_areaB").val();
        var i = $("#sel_areaC").val();
        if (k == "0" || j == "0" || i == "0") {
            $("#region_id_msg_valid").attr("class", "orange").html(" <s></s> 请选择有效的省市区");
            return false;
        } else {
            $("#region_id_msg_valid").attr("class", "red").html(" *");
        }
        return {areaAID: k, areaBID: j, areaCID: i};
    };
}

var clearNoNum = function (a) {
    a.value = a.value.replace((/[^\d-]/g), "");
    a.value = a.value.replace((/^\-/g), "");
    a.value = a.value.replace((/\-{2,}/g), "-");
    a.value = a.value.replace("-", "$#$").replace((/\-/g), "").replace("$#$", "-");
};
var bindTxtFun = function (a) {
    a.bind("keyup", function (b) {
        var c = a.val();
        if (!(b.keyCode > 48 && b.keyCode <= 57)) {
            a.val(c.replace(/\D/g, ""));
        }
    }).bind("dragenter", function () {
        return false;
    }).bind("onpaste", function () {
        return !clipboardData.getData("text").match(/\D/);
    });
};

function Consignee(f) {
    var g = 0;
    var i = null;
    var c = null;
    var l = null;
    var m = new JSPanel(f);
    var n = new Region();
    var h = function () {
        var o = n.getValue();
        var q = e();
        if (!o || !q) {
            return;
        }
        var p = new Object();
        p.contactID = g;
        p.areaCID = o.areaCID;
        p.shipAddress = getTrimVal("txt_ship_address");
        p.shipZip = getTrimVal("txt_ship_zip");
        p.shipName = getTrimVal("txt_ship_name");
        p.shipMobile = getTrimVal("txt_ship_mb");
        c(p);
    };
    this.setDataSource = function (o) {
        i = o;
        g = i.contactID;
        m.DataSource = o;
    };
    this.bindTemplateAddnew = function () {
        m.Template = CONSIGNEE_ADDNEW_TEMPLATE;
    };
    this.bindTemplateModify = function () {
        m.Template = CONSIGNEE_EDITABLE_TEMPLATE;
    };
    this.showEditable = function () {
        m.DataBind();
        n.setValue(i.areaAID, i.areaBID, i.areaCID);
        bindTxtFun($("#txt_ship_zip"));
        $("#txt_ship_tel").bind("keyup", function () {
            clearNoNum(this);
        }).bind("dragenter", function () {
            return false;
        }).bind("onpaste", function () {
            return !clipboardData.getData("text").match(/\D/);
        });
        bindTxtFun($("#txt_ship_mb"));
        $("#btn_consignee_save").bind("click", h);
        $("#btn_consignee_cancle").bind("click", a);
    };
    var a = function () {
        var o = null;
        l(o);
    };
    this.setConsigneeSave = function (o) {
        c = o;
    };
    this.setConsigneeCancle = function (o) {
        l = o;
    };
    var d = function () {
        return requireFieldValid("txt_ship_name", "ship_name_valid_msg", MSG_SHIPNAME_EMPTY);
    };
    var b = function () {
        var o = getTrimVal("txt_ship_mb");
//		var q = getTrimVal("txt_ship_tel");
//		if (o == "" && q.length < 6) {
//			alert(11);
//			$("#ship_mb_valid_msg").attr("class", "orange").html(" <s></s>必填");
//			return false;
//		}
        if (o == "") {
            $("#ship_mb_valid_msg").attr("class", "orange").html(" <s></s>必填");
            return false;
        }
        var p = true;
        if (o != "") {
            p = regularExpressionValid("txt_ship_mb", SHIP_MB_REGULAR_EXPRESSION, "ship_mb_valid_msg", SHIP_MB_FORMAT_ERROR);
        }
//		if (q != "" && q.length > 5) {
//			p = p && regularExpressionValid("txt_ship_tel", SHIP_TEL_REGULAR_EXPRESSION, "ship_tel_valid_msg", SHIP_TEL_FORMAT_ERROR);
//		}
        return p;
    };
    var j = function () {
        return requireFieldValid("txt_ship_address", "ship_address_valid_msg", MSG_SHIPADDRESS_FORMAT_ERROR);
    };
    var k = function () {
        var o = getTrimVal("txt_ship_zip");
        if (!regularExpressionValid("txt_ship_zip", SHIP_ZIP_REGULAR_EXPRESSION, "ship_zip_valid_msg", " <s></s> 请正确填写邮编")) {
            return false;
        }
        $("#ship_zip_valid_msg").empty();
        return true;
    };
    var e = function () {
        return j() && k() && d() && b();
    };
    this.setHignLight = function () {
        $("#btn_consignee_save").attr("class", "y_btn");
    };
    this.setDisabled = function () {
        $("#btn_consignee_save").attr("disabled", "disabled").css("cursor", "wait");
    };
    this.unDisabled = function () {
        $("#btn_consignee_save").removeAttr("disabled").css("cursor", "pointer");
    };
}

function oString(O) {
    var S = [];
    for (var i in O) {
        O[i] = typeof O[i] == 'string' ? '"' + O[i] + '"' : (typeof O[i] == 'object' ? oString(O[i]) : O[i]);
        S.push(i + ':' + O[i]);
    }
    return '{' + S.join(',') + '}';
}

function saveShipAddressFun(a, b) {
    var o = {
        userId: userId,
        province: $("#sel_areaA > option:checked").text(),
        city: $("#sel_areaB > option:checked").text(),
        district: $("#sel_areaC > option:checked").text(),
        address: $("#txt_ship_address").val(),
        zipCode: $("#txt_ship_zip").val() == "" ? 0 : $("#txt_ship_zip").val(),
        consignee: $("#txt_ship_name").val(),
        phone: $("#txt_ship_mb").val()
    };
    o = oString(o);
    $.ajax({
        url: "/user/addAddress.html",
        data: "userJSON=" + encodeURIComponent(o),
        success: function (data) {
            if (data == "success") {
                $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                b = null;
                divConsignee.empty().hide();
                isLoaded = true;
                getAddressListData();
                addresslistDiv.show();
                $("#btnAddnewAddr").show();
            } else if (data == "sizeError") {
                $.PageDialog("<dl class=\"sAltFail\"><dd>对不起，最多只能录入4个收货地址，请删除其中的某个收货地址！</dd></dl>", {W: 430, H: 50});
            }
        },
        error: function () {
            alert("网络错误，请与管理员联系！");
            location.reload();
        }
    });
}

function cancleShipAddressFun() {
    divConsignee.empty().hide();
    addresslistDiv.show();
    $("#btnAddnewAddr").show();
}

function addressAddnew() {
    if (!isLoaded) {
        return;
    }
    var c = $("#btn_consignee_save");
    if (c.length > 0) {
        c.attr("class", "y_btn").focus();
        return;
    }
    var a = new Consignee("div_consignee");
    a.setConsigneeSave(function (d) {
        saveShipAddressFun(d, a);
    });
    a.setConsigneeCancle(function (d) {
        a = null;
        cancleShipAddressFun();
    });
    var b = {
        contactID: 0,
        areaAID: 0,
        areaBID: 0,
        areaCID: 0,
        shipAddress: "",
        shipZip: "",
        shipName: "",
        shipMobile: "",
        shipTel: ""
    };
    a.setDataSource(b);
    a.bindTemplateAddnew();
    a.showEditable();
    if (theAddressNum == 0) {
        $("#btn_consignee_cancle").hide();
    }
    divConsignee.show();
}

function addressModify(c) {
    if (!isLoaded) {
        return;
    }
    var a = new Consignee("div_consignee");
    a.setConsigneeSave(function (d) {
        saveShipAddressFun(d, a);
    });
    a.setConsigneeCancle(function (d) {
        a = null;
        cancleShipAddressFun();
    });
    var b = null;
//	listItems.each(function () {
//		if (this.ID == parseInt(c)) {
//			b = {contactID:this.ID, areaAID:this.AID, areaBID:this.BID, areaCID:this.CID, shipAddress:"" + this.Address.toText() + "", shipZip:"" + this.Zip + "", shipName:"" + this.Name.toText() + "", shipMobile:"" + this.Mobile + "", shipTel:"" + this.Tel + ""};
//		}
//	});
    if (b != null) {
        $("#btnAddnewAddr").hide();
        a.setDataSource(b);
        a.bindTemplateModify();
        a.showEditable();
        divConsignee.show();
    } else {
        divConsignee.hide();
        return;
    }
}

function addressSetDefault(b) {
    if (!isLoaded) {
        return;
    }
    $.ajax({
        url: "/user/setDefaultAddress.html",
        data: "userId=" + userId + "&id=" + b,
        success: function (data) {
            if (data == "success") {
                $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                divConsignee.empty().hide();
                isLoaded = true;
                getAddressListData();
                addresslistDiv.show();
                $("#btnAddnewAddr").show();
            }
        },
        error: function () {
            alert("操作失败！请稍后再试！");
        }
    });
}

function addressDelete(b) {
    if (!isLoaded) {
        return;
    }
    if (theAddressNum < 2) {
        alert("对不起，您至少要保留一个收货地址");
    } else {
        var a = function () {
            $.ajax({
                url: "/user/delAddress.html",
                data: "id=" + b,
                success: function (data) {
                    if (data == "success") {
                        $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                        isLoaded = true;
                        getAddressListData();
                        addresslistDiv.show();
                    }
                },
                error: function () {
                    alert("操作失败！请稍后再试！");
                }
            });
        };
        $.PageDialog.showConfirm("您确定要删除此配送地址吗？", a);

    }
    return false;
}

