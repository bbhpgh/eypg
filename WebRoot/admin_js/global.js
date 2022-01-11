function btn_iframef5() {
    window.parent.frames["iframe"].location.reload();
}

function btn_checkbom(url) {
    window.parent.frames["iframe"].location = url;
}

function btn_caches() {

}

function btn_map(url) {

    window.parent.frames["iframe"].location = url;
}


//JS 删除 API
//url 地址
//msgs 消息提示信息
function Del(url, msgs) {


    var t = $.layer({
        type: 0,
        area: ['auto', 'auto'],
        title: ['信息', true],
        border: [5, 0.5, '#7298a6', true],
        dialog: {
            msg: msgs,
            type: 4,
            btns: 2,
            btn: ['删除', '取消'],
            yes: function () {
                $.ajax({
                    async: false
                });
                $.post(url, {ajax: true}, function (data) {
                    if (data == 'no') {
                        layer.msg("删除失败!", 2, 8);
                    } else {
                        layer.msg("删除成功!", 2, 1);
                        window.parent.frames["iframe"].location = data;
                    }
                });
            },
            no: function () {
                layer.close(t);
            }
        }
    });
}


//JS 消息提示API
function message(msgs, type, time) {
    layer.msg(msgs, time, type);
}

//JS 新建浏览器标签API
function openwinx(url, name, w, h) {
    if (!w) w = screen.width - 4;
    if (!h) h = screen.height - 95;
    window.open(url, name, "top=100,left=400,width=" + w + ",height=" + h + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
}

$.focusblur = function (focusid) {
    var focusblurid = $(focusid);
    var defval = focusblurid.val();
    focusblurid.focus(function () {
        var thisval = $(this).val();
        if (thisval == defval) {
            $(this).val("");
        }
    });
    focusblurid.blur(function () {
        var thisval = $(this).val();
        if (thisval == "") {
            $(this).val(defval);
        }
    });
};