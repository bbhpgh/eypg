var seltion;
var userUploadFun = null;
var userSaveFun = null;

function UploadPhotoScriptAPI(b, a) {
    if (b != "") {
        userUploadFun.ShowUploadErr(b);
    } else {
        $("#divImgTip").html("");
        userUploadFun.ShowUploadPic(a);
    }
}

$(function () {
    var a = $skin;
    var userId = $("#userId").val();
    var c = function () {
        var e = $("#pageForm");
        var g = e.find("div.localBtn");
        var h = e.find("div.upImgLoading");
        var j, f;
        var i = g.find("div.btn");
        g.parent().mousemove(function (m) {
            var l = $(window).width();
            if (f != l) {
                f = l;
                j = g.offset();
            }
            $("#FileControl").css({top: (m.pageY - j.top - 7) + "px", left: (m.pageX - j.left - 190) + "px"});
            i.attr("class", "btnhov");
        }).mouseout(function (l) {
            i.attr("class", "btn");
        });
        $("#fuploadFace").mouseover(function () {
            $(this).css("cursor", "pointer");
        });
        var d = function () {
            var l = function (n, p) {
                if (!p.width || !p.height) {
                    return;
                }
                var o = 160 / p.width;
                var m = 160 / p.height;
                $("#img160").css({
                    width: Math.round(o * 300),
                    height: Math.round(m * 300),
                    marginLeft: -Math.round(o * p.x1),
                    marginTop: -Math.round(m * p.y1)
                });
                o = 80 / p.width;
                m = 80 / p.height;
                $("#img80").css({
                    width: Math.round(o * 300),
                    height: Math.round(m * 300),
                    marginLeft: -Math.round(o * p.x1),
                    marginTop: -Math.round(m * p.y1)
                });
                o = 30 / p.width;
                m = 30 / p.height;
                $("#img30").css({
                    width: Math.round(o * 300),
                    height: Math.round(m * 300),
                    marginLeft: -Math.round(o * p.x1),
                    marginTop: -Math.round(m * p.y1)
                });
                $("#cutX").val(p.x1);
                $("#cutY").val(p.y1);
                $("#cutW").val(p.width);
                $("#cutH").val(p.height);
            };
            this.ShowUploadPic = function (o) {
                var m = $www;
                var n = m + o;
                e.get(0).reset();
                h.addClass("hidden");
                g.removeClass("hidden");
                $("#imgPhoto").attr("src", n).show();
                $("#img160").attr("src", n);
                $("#img80").attr("src", n);
                $("#img30").attr("src", n);
                $("#hidPicUrl").val(o);
                $("#imgPhoto").imgAreaSelect({aspectRatio: "1:1", handles: true, fadeSpeed: 200, onSelectChange: l});
                $("#imgPhoto").imgAreaSelect({x1: 75, y1: 75, x2: 235, y2: 235, onInit: l});
            };
            this.ShowUploadErr = function (m) {
                e.get(0).reset();
                h.addClass("hidden");
                g.removeClass("hidden");
                alert(m, 250);
            };
        };
        var k = function () {
            this.btnSaveUserUploadPhoto_click = function () {
                var p = $("#hidPicUrl").val();
                if (p == "") {
                    $.PageDialog("<dl class=\"sAltFail\"><dd>请先上传头像!</dd></dl>", {W: 160, H: 50, autoClose: true});
                    return false;
                }
                var n = $("#cutX").val();
                var s = $("#cutY").val();
                var q = $("#cutW").val();
                var m = $("#cutH").val();
                if (n == "" || s == "" || q == "" || m == "") {
                    $.PageDialog("<dl class=\"sAltFail\"><dd>请先选择裁剪区域!</dd></dl>", {W: 170, H: 50, autoClose: true});
                    return false;
                }
                var r = $("#hidUserPhotoName").val();
                var o = {
                    action: "updateFace",
                    userId: userId,
                    hidPicUrl: p,
                    oldSrc: r,
                    x1: n,
                    y1: s,
                    w: q,
                    h: m,
                    t: Math.random()
                };
                l(o);
            };
            var l = function (m) {
                $.ajax({
                    url: "/user/updateFace.action",
                    data: m,
                    beforeSend: function () {
                        $("#btnSavePhoto").attr("disabled", true).val("请稍候...");
                    },
                    success: function (n) {
                        $("#btnSavePhoto").attr("disabled", false).val("保存头像");
                        if (n == "success") {
                            $.PageDialog("<dl class=\"sAltOK\"><dd>操作成功!</dd></dl>", {W: 160, H: 50, autoClose: true});
                            setTimeout(function () {
                                window.location.href = $www + "/user/index.html";
                            }, 1500);
                        } else {
                            alert("保存失败！请稍候再试！");
                        }
                    }
                });
            };
        };
        userUploadFun = new d();
        userSaveFun = new k();
        $("#btnSavePhoto").bind("click", userSaveFun.btnSaveUserUploadPhoto_click);
        $("#fuploadFace").bind("change", function () {
            var m = $(this).val();
            if (m != "") {
                var l = (m.substr(m.length - 5)).substr((m.substr(m.length - 5)).indexOf(".") + 1).toLowerCase();
                if (l != "jpeg" && l != "jpg") {
                    $(this).val("");
                    $.PageDialog("<dl class=\"sAltFail\"><dd>只能上传jpeg或jpg图片!</dd></dl>", {
                        W: 250,
                        H: 50,
                        autoClose: true
                    });
                    return false;
                } else {
                    g.addClass("hidden");
                    h.removeClass("hidden");
                    e.submit();
                }
            } else {
                $.PageDialog("<dl class=\"sAltFail\"><dd>请选择要上传的图片!</dd></dl>", {W: 250, H: 50, autoClose: true});
                return false;
            }
        });

        $("#upFrame").load(function () {
            var data = $(window.frames['upFrame'].document.body).text();
            //若iframe携带返回数据，则显示在feedback中
            if (data != null) {
                UploadPhotoScriptAPI("", data);
            }
        });
    };
    c();
});

