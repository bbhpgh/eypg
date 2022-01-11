$(document).ready(function () {
    var l = $("#liAddAddrTools");
    var p = $("#addrIDOther");
    var o = $("#liAddAddrBox");
    var i = $("#btnSubmitCart");
    var userId = $("#userId").val();
    p.unbind("click").bind("click", function () {
        o.show();
        l.hide();
        $("#msgAddrOver").hide();
        $("[name=selectAddrID]").attr("checked", false);
        $.ajax({
            url: "/user/getProvinceList.html",
            success: function (data) {
                data = eval('(' + data + ')');
                var html = "<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>";
                for (var i = 0; i < data.length; i++) {
                    html += "<option value=\"" + data[i][0] + "\">" + data[i][1] + "</option>";
                }
                $("#shipAreaA").html(html);
            },
            error: function () {
                alert("获取地区失败，请稍后再试！");
            }
        });
        $("#shipAreaB").html("<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>");
        $("#shipAreaC").html("<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>");

        $("#shipAreaA").change(function () {
            $.ajax({
                url: "/user/getCityList.html",
                data: "id=" + $("#shipAreaA").val(),
                success: function (data) {
                    data = eval('(' + data + ')');
                    var html = "<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>";
                    for (var i = 0; i < data.length; i++) {
                        html += "<option value=\"" + data[i][0] + "\">" + data[i][1] + "</option>";
                    }
                    $("#shipAreaB").html(html);
                    $("#shipAreaC").html("<option value=\"0\">\u3000\u3000\u3000\u3000\u3000</option>");
                },
                error: function () {
                    alert("获取地区失败，请稍后再试！");
                }
            });
        });

        $("#shipAreaB").change(function () {
            var i = $("#shipAreaB").val();
            var itext = $("#shipAreaB option:selected").text();
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
                    $("#shipAreaC").html(html);
                },
                error: function () {
                    alert("获取地区失败，请稍后再试！");
                }
            });
        });
    });
    $("[name=selectAddrID]").unbind("click").bind("click", function () {
        o.hide();
        l.show();
        $("#addrIDOther").attr("checked", false);
    });
    $("#btn_consignee_save").unbind("click").bind("click", function () {
        if ($("#shipAreaA").val() == 0 || $("#shipAreaB").val() == 0 || $("#shipAreaC").val() == 0) {
            $("#shipAreaValidMsg").html("<p class=\"error\"><s></s>请正确选择省市区</p>");
            return false;
        } else {
            $("#shipAreaValidMsg").html("");
        }
        if ($("#shipAddress").val() == "") {
            $("#shipAddressValidMsg").html("<p class=\"error\"><s></s>请输入街道地址，不要重复输入省市区名称</p>");
            return false;
        } else {
            $("#shipAddressValidMsg").html("");
        }
        if ($("#shipName").val() == "") {
            $("#shipNameValidMsg").html("<p class=\"error\"><s></s>请输入收货人姓名</p>");
            return false;
        } else {
            $("#shipNameValidMsg").html("");
        }
        if ($("#shipMobile").val() == "") {
            $("#shipMobileValidMsg").html("<p class=\"error\"><s></s>请输入手机号码</p>");
            return false;
        } else {
            $("#shipMobileValidMsg").html("");
        }

        var o = {
            userId: userId,
            province: $("#shipAreaA > option:checked").text(),
            city: $("#shipAreaB > option:checked").text(),
            district: $("#shipAreaC > option:checked").text(),
            address: $("#shipAddress").val(),
            zipCode: $("#shipZip").val() == "" ? 0 : $("#shipZip").val(),
            consignee: $("#shipName").val(),
            phone: $("#shipMobile").val()
        };
        o = oString(o);
        alert(o);
        $.ajax({
            url: "/user/addAddress.html",
            data: "userJSON=" + encodeURIComponent(o),
            success: function (data) {
                if (data == "success") {
                    $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                    location.reload();
                } else if (data == "sizeError") {
                    $.PageDialog("<dl class=\"sAltFail\"><dd>对不起，最多只能录入4个收货地址，请删除其中的某个收货地址！</dd></dl>", {
                        W: 430,
                        H: 50
                    });
                }
            },
            error: function () {
                alert("网络错误，请与管理员联系！");
                location.reload();
            }
        });
    });
    $("#btn_consignee_cancle").unbind("click").bind("click", function () {
        o.hide();
        l.show();
        $("[name=selectAddrID]:eq(0)").attr("checked", true);
        $("#addrIDOther").attr("checked", false);
    });

    i.click(function () {
        var addVal = $('input:radio[name="selectAddrID"]:checked').val();
        if (addVal == null) {
            $.PageDialog("<dl class=\"sAltFail\"><dd>请选择一个收货地址！</dd></dl>", {W: 250, H: 50});
            return false;
        } else {
            var o = {
                userId: userId,
                id: addVal,
                orderRemarks: $("#shipRemark").val(),
                postDate: $("#shipTime > option:checked").val(),
                hidOrderNO: $("#hidOrderNO").val()
            };
            o = oString(o);
            $.ajax({
                url: "/user/OrderDetailAddAddress.html",
                data: "userJSON=" + encodeURIComponent(o),
                success: function (data) {
                    if (data == "success") {
                        $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                        location.reload();
                    }
                },
                error: function () {
                    alert("网络错误，请与管理员联系！");
                    location.reload();
                }
            });
        }
    });

    var K = function () {
        var hidOrderNO = $("#hidOrderNO").val()
        $.ajax({
            url: "/user/confirmOrderDetail.html",
            data: "id=" + hidOrderNO,
            success: function (data) {
                if (data == "success") {
                    $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功！</dd></dl>", {W: 160, H: 50, autoClose: true});
                    location.reload();
                } else {
                    $.PageDialog("<dl class=\"sAltFail\"><dd>确认收货失败，请确定已经发货</dd></dl>", {W: 280, H: 50});
                }
            },
            error: function () {
                alert("网络错误，请与管理员联系！");
                location.reload();
            }
        });
        return false;
    };

    $("[name=btnReceived]").click(function () {
        $.PageDialog.showConfirm("您确定收到货了吗?", K);
    });


    function oString(O) {
        var S = [];
        for (var i in O) {
            O[i] = typeof O[i] == 'string' ? '"' + O[i] + '"' : (typeof O[i] == 'object' ? oString(O[i]) : O[i]);
            S.push(i + ':' + O[i]);
        }
        return '{' + S.join(',') + '}';
    }
});
