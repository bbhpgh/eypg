var CBLFun = null;
$(document).ready(function () {
    var a = $skin;
    var b = function () {
        $("div.subMenu").find("a").each(function (e, d) {
            $(this).click(function () {
                if (e == 0) {
                    $(this).attr("class", "current").siblings().attr("class", "");
                    $("#PostList0").show();
                    $("#PostList1").hide();
                    $("#divPageNav0").show();
                    $("#divPageNav1").hide();
                } else {
                    $(this).attr("class", "current").siblings().attr("class", "current2");
                    $("#PostList0").hide();
                    $("#PostList1").show();
                    $("#divPageNav0").hide();
                    $("#divPageNav1").show();
                }
//				CBLFun.listDomObj = $("#PostList" + e);
//				CBLFun.pageNavObj = $("#divPageNav" + e);
//				CBLFun._SendData.type = parseInt(e);
                if ($("#PostList" + e).html() == "") {
                    CBLFun.initData();
                }
            });
        });
//		var c = function () {
//			var e = "http://www.1ypg.com";
//			var n = 6;
//			var f = 1;
//			var k = 0;
//			var j = 0;
        this.listDomObj = $("#PostList0");
        this.pageNavObj = $("#divPageNav0");
//			this._SendData = {type:0, FIdx:1, EIdx:n, isCount:1};
//			var i = new JQAjax("/DataServer/GetMemberCenterUserPostSingle");
//			var h = "<ul class=\"listTitle\"><li class=\"single-img\">\u6652\u5355\u56fe\u7247</li><li class=\"single-xx-has\">\u6652\u5355\u4fe1\u606f</li><li class=\"sdzt\">\u6652\u5355\u72b6\u6001</li><li class=\"single-Control\">\u64cd\u4f5c</li></ul>";
//			var g = "<ul class=\"single-tit listTitle\"><li class=\"single-img\">\u5546\u54c1\u56fe\u7247</li><li class=\"single-xx-not\">\u5546\u54c1\u4fe1\u606f</li><li class=\"single-Control\">\u64cd\u4f5c</li></ul>";
//			var l = function () {
//				if (!isLoaded) {
//					return false;
//				}
//				i.OnSuccess(function (p) {
//					var q = p.d.Code;
//					if (q == 0) {
//						eval("p=" + p.d.Str);
//						if (CBLFun._SendData.isCount == 1) {
//							if (CBLFun._SendData.type == 0) {
//								k = p.totalCount;
//								$("#postCount").html("(" + k + ")");
//								if (parseInt(p.unPostCount) != 0) {
//									$("#unPostCount").html("(" + p.unPostCount + ")");
//								}
//							} else {
//								j = p.totalCount;
//							}
//						}
//						if (CBLFun._SendData.type == 0) {
//							o(p);
//						} else {
//							m(p);
//						}
//					} else {
//						if (q == -1) {
//							if (CBLFun._SendData.type == 0) {
//								$("#postCount").html("");
//								if (p.d.Num != 0) {
//									$("#unPostCount").html("(" + p.d.Num + ")");
//								} else {
//									$("#unPostCount").html("");
//								}
//								CBLFun.listDomObj.append("<div class=\"tips-con\"><i></i>\u6682\u65e0\u8bb0\u5f55\uff01</div>");
//							} else {
//								$("#unPostCount").html("");
//								CBLFun.listDomObj.append("<div class=\"tips-con\"><i></i>\u6682\u65e0\u8bb0\u5f55\uff01</div>");
//							}
//						} else {
//							if (q == 10) {
//								location.reload();
//							} else {
//								FailDialog("\u8bf7\u6c42\u5931\u8d25!");
//							}
//						}
//					}
//				});
//				i.OnSend($.toJSON(CBLFun._SendData), "json", true);
//			};
//			var o = function (p) {
//				var t = p.listItems;
//				var s = "";
//				for (var r = 0; r < t.length; r++) {
//					if (t[r].postState < 2) {
//						s = "<a href=\"/PostSingleDetail-" + t[r].postID + ".html\"";
//					} else {
//						s = "<a href=\"http://post.1ypg.com/Detail-" + t[r].postID + ".html\" target=\"_blank\"";
//					}
//					var q = "<ul><li class=\"single-img\">" + s + "><img alt=\"\" src=\"http://file.1ypg.com/UserPost/Small/" + t[r].postPic + "\"></a></li><li class=\"single-xx-has\"><p>" + s + t[r].postID + " class=\"blue\">" + t[r].postTitle + "</a> <span class=\"gray02\">" + t[r].postTime + "</span></p>" + t[r].postContent.reAjaxStr() + "</li>";
//					if (t[r].postState == 0) {
//						q += "<li class=\"sdzt\"><p class=\"gray02\">\u5ba1\u6838\u4e2d...</p></li>";
//						q += "<li class=\"single-Control\"><a href=\"/PostSingleDetail-" + t[r].postID + ".html\" class=\"blue\" title=\"\u8be6\u60c5\">\u8be6\u60c5</a>";
//					} else {
//						if (t[r].postState == 1) {
//							q += "<li class=\"sdzt\"><p class=\"red\">\u5ba1\u6838\u672a\u901a\u8fc7</p>" + t[r].postFailReason.reAjaxStr() + "</li>";
//							q += "<li class=\"single-Control\"><a href=\"/PostSingleEdit-" + t[r].postID + ".html\" class=\"blue\" title=\"\u7f16\u8f91\">\u7f16\u8f91</a>";
//						} else {
//							if (t[r].postState == 2) {
//								q += "<li class=\"sdzt\"><p class=\"green\">\u5ba1\u6838\u901a\u8fc7</p></li>";
//								q += "<li class=\"single-Control\"><a href=\"http://post.1ypg.com/Detail-" + t[r].postID + ".html\" target=\"_blank\" class=\"blue\" title=\"\u8be6\u60c5\">\u8be6\u60c5</a>";
//							}
//						}
//					}
//					q += "</li></ul>";
//					CBLFun.listDomObj.append(q);
//				}
//				if (k > n) {
//					CBLFun.pageNavObj.show().append(GetAjaxPageNationEx(k, n, 5, f, "CBLFun.gotoPageIndex"), false);
//				}
//			};
//			var m = function (p) {
//				var s = p.listItems;
//				for (var r = 0; r < s.length; r++) {
//					var q = "<ul><li class=\"single-img\"><a href=\"" + e + "/product/" + s[r].codeID + ".html\" target=\"_blank\"><img alt=\"\" src=\"http://goodsimg.1ypg.com/goodspic/pic-70-70/" + s[r].goodsPic + "\"></a></li><li class=\"single-xx-not\"><p class=\"get-name\"><a target=\"_blank\" href=\"" + e + "/product/" + s[r].codeID + ".html\" class=\"blue\">(\u7b2c" + s[r].codePeriod + "\u671f)" + s[r].goodsSName + "</a></p><p class=\"buy-money\">\u4ef7\u503c\uff1a<span class=\"money\"><i class=\"rmb\"></i>" + s[r].codePrice + "</span></p><p class=\"buy-code\">\u5e78\u8fd0\u4e91\u8d2d\u7801\uff1a" + s[r].codeRNO + "</p><p class=\"buy-time\">\u63ed\u6653\u65f6\u95f4\uff1a" + s[r].codeRTime + "</p> </li><li class=\"single-Control\"><a href=\"/PostSingleAdd-" + s[r].codeID + ".html\" class=\"blue\">\u7acb\u5373\u6652\u5355</a></li></ul>";
//					CBLFun.listDomObj.append(q);
//				}
//				if (j > n) {
//					CBLFun.pageNavObj.show().html(GetAjaxPageNationEx(j, n, 5, f, "CBLFun.gotoPageIndex"), false);
//				}
//			};
//			var d = function (p, q) {
//				f = Math.floor(q / n);
//				CBLFun._SendData.FIdx = p;
//				CBLFun._SendData.EIdx = q;
//				if (f > 1) {
//					CBLFun._SendData.isCount = 0;
//				} else {
//					CBLFun._SendData.isCount = 1;
//				}
//				if (CBLFun._SendData.type == 0) {
//					CBLFun.listDomObj.html(h);
//				} else {
//					CBLFun.listDomObj.html(g);
//				}
//				CBLFun.pageNavObj.html("");
//				l();
//			};
//			this.gotoPageIndex = function (p, q) {
//				d(p, q);
//			};
//			this.initData = function () {
//				d(1, n);
//			};
//		};
//		CBLFun = new c();
        isLoaded = true;
//		CBLFun.initData();
    };
    b();
});

