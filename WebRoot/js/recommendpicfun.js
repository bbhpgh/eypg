$.fn.jcarousel = function (j) {
    var d = {
        itemWidth: 150, showNum: 5, initIndex: 1, initCallback: function () {
        }
    };
    j = j || {};
    $.extend(d, j);
    var c = this;
    var b = 0;
    var f = 0;
    var g = new h(this.parent().prev().find("a"));
    g.setEnabled();
    var e = new i(this.parent().next().find("a"));
    e.setEnabled();
    var a = $("li", this).length;
    f = a * d.itemWidth;
    b = d.itemWidth;
    c.width(f).css("left", "0px");
    c.show("fast", d.initCallback);

    function h(k) {
        this.btn = k;
        this.setDisabled = function () {
            this.btn.removeClass("Roll_enabled").unbind();
        };
        this.setEnabled = function () {
            this.btn.addClass("Roll_enabled").unbind().bind("click", function () {
                c.animate({left: "-" + b + "px"}, "fast", function () {
                    c.append(c.children().eq(0)).css("left", "0px");
                });
            });
        };
    }

    function i(k) {
        this.btn = k;
        this.setDisabled = function () {
            this.btn.removeClass("Roll_enabled").unbind();
        };
        this.setEnabled = function () {
            this.btn.addClass("Roll_enabled").unbind().bind("click", function () {
                c.prepend(c.children().eq(9)).css("left", "-" + b + "px");
                c.animate({left: "0px"}, "fast", function () {
                });
            });
        };
    }
};
$("#ulRecommend").jcarousel({itemWidth: 182, initIndex: 1, showNum: 5});

