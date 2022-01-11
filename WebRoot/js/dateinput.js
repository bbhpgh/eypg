DateInput = (function (A) {
    function B(C, D) {
        if (typeof (D) != "object") {
            D = {};
        }
        A.extend(this, B.DEFAULT_OPTS, D);
        this.input = A(C);
        this.bindMethodsToObj("show", "hide", "hideIfClickOutside", "keydownHandler", "selectDate");
        this.build();
        this.selectDate();
        this.hide();
    }

    B.DEFAULT_OPTS = {
        month_names: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        short_month_names: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        short_day_names: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
        start_of_week: 1
    };
    B.prototype = {
        build: function () {
            var C = A("<p class=\"month_nav\"><span class=\"button prev\" title=\"[Page-Up]\">&#171;</span> <span class=\"month_name\"></span> <span class=\"button next\" title=\"[Page-Down]\">&#187;</span></p>");
            this.monthNameSpan = A(".month_name", C);
            A(".prev", C).click(this.bindToObj(function () {
                this.moveMonthBy(-1);
            }));
            A(".next", C).click(this.bindToObj(function () {
                this.moveMonthBy(1);
            }));
            var D = A("<p class=\"year_nav\"><span class=\"button prev\" title=\"[Ctrl+Page-Up]\">&#171;</span> <span class=\"year_name\"></span> <span class=\"button next\" title=\"[Ctrl+Page-Down]\">&#187;</span></p>");
            this.yearNameSpan = A(".year_name", D);
            A(".prev", D).click(this.bindToObj(function () {
                this.moveMonthBy(-12);
            }));
            A(".next", D).click(this.bindToObj(function () {
                this.moveMonthBy(12);
            }));
            var E = A("<div class=\"nav\"></div>").append(C, D);
            var F = "<table><thead><tr>";
            A(this.adjustDays(this.short_day_names)).each(function () {
                F += "<th>" + this + "</th>";
            });
            F += "</tr></thead><tbody></tbody></table>";
            this.dateSelector = this.rootLayers = A("<div class=\"date_selector\"></div>").append(E, F).insertAfter(this.input);
            if (A.browser.msie && A.browser.version < 7) {
                this.ieframe = A("<iframe class=\"date_selector_ieframe\" frameborder=\"0\" src=\"#\"></iframe>").insertBefore(this.dateSelector);
                this.rootLayers = this.rootLayers.add(this.ieframe);
                A(".button", E).mouseover(function () {
                    A(this).addClass("hover");
                });
                A(".button", E).mouseout(function () {
                    A(this).removeClass("hover");
                });
            }
            this.tbody = A("tbody", this.dateSelector);
            this.input.change(this.bindToObj(function () {
                this.selectDate();
            }));
            this.selectDate();
        }, selectMonth: function (D) {
            var J = new Date(D.getFullYear(), D.getMonth(), 1);
            if (!this.currentMonth || !(this.currentMonth.getFullYear() == J.getFullYear() && this.currentMonth.getMonth() == J.getMonth())) {
                this.currentMonth = J;
                var I = this.rangeStart(D), H = this.rangeEnd(D);
                var C = this.daysBetween(I, H);
                var F = "";
                for (var E = 0; E <= C; E++) {
                    var G = new Date(I.getFullYear(), I.getMonth(), I.getDate() + E, 12, 0);
                    if (this.isFirstDayOfWeek(G)) {
                        F += "<tr>";
                    }
                    if (G.getMonth() == D.getMonth()) {
                        F += "<td class=\"selectable_day\" date=\"" + this.dateToString(G) + "\">" + G.getDate() + "</td>";
                    } else {
                        F += "<td class=\"unselected_month\" date=\"" + this.dateToString(G) + "\">" + G.getDate() + "</td>";
                    }
                    if (this.isLastDayOfWeek(G)) {
                        F += "</tr>";
                    }
                }
                this.tbody.empty().append(F);
                this.monthNameSpan.empty().append(this.monthName(D));
                this.yearNameSpan.empty().append(this.currentMonth.getFullYear());
                A(".selectable_day", this.tbody).click(this.bindToObj(function (K) {
                    this.changeInput(A(K.target).attr("date"));
                }));
                A("td[date=" + this.dateToString(new Date()) + "]", this.tbody).addClass("today");
                A("td.selectable_day", this.tbody).mouseover(function () {
                    A(this).addClass("hover");
                });
                A("td.selectable_day", this.tbody).mouseout(function () {
                    A(this).removeClass("hover");
                });
            }
            A(".selected", this.tbody).removeClass("selected");
            A("td[date=" + this.selectedDateString + "]", this.tbody).addClass("selected");
        }, selectDate: function (C) {
            if (typeof (C) == "undefined") {
                C = this.stringToDate(this.input.val());
            }
            if (!C) {
                C = new Date();
            }
            this.selectedDate = C;
            this.selectedDateString = this.dateToString(this.selectedDate);
            this.selectMonth(this.selectedDate);
        }, changeInput: function (C) {
            this.input.val(C).change();
            this.hide();
        }, show: function () {
            this.rootLayers.css("display", "block");
            A([window, document.body]).click(this.hideIfClickOutside);
            this.input.unbind("focus", this.show);
            A(document.body).keydown(this.keydownHandler);
            this.setPosition();
        }, hide: function () {
            this.rootLayers.css("display", "none");
            A([window, document.body]).unbind("click", this.hideIfClickOutside);
            this.input.focus(this.show);
            A(document.body).unbind("keydown", this.keydownHandler);
        }, hideIfClickOutside: function (C) {
            if (C.target != this.input[0] && !this.insideSelector(C)) {
                this.hide();
            }
        }, insideSelector: function (C) {
            var D = this.dateSelector.position();
            D.right = D.left + this.dateSelector.outerWidth();
            D.bottom = D.top + this.dateSelector.outerHeight();
            return C.pageY < D.bottom && C.pageY > D.top && C.pageX < D.right && C.pageX > D.left;
        }, keydownHandler: function (C) {
            switch (C.keyCode) {
                case 9:
                case 27:
                    this.hide();
                    return;
                    break;
                case 13:
                    this.changeInput(this.selectedDateString);
                    break;
                case 33:
                    this.moveDateMonthBy(C.ctrlKey ? -12 : -1);
                    break;
                case 34:
                    this.moveDateMonthBy(C.ctrlKey ? 12 : 1);
                    break;
                case 38:
                    this.moveDateBy(-7);
                    break;
                case 40:
                    this.moveDateBy(7);
                    break;
                case 37:
                    this.moveDateBy(-1);
                    break;
                case 39:
                    this.moveDateBy(1);
                    break;
                default:
                    return;
            }
            C.preventDefault();
        }, stringToDate: function (C) {
            var D;
            if (D = C.match(/^(\d{1,2}) ([^\s]+) (\d{4,4})$/)) {
                return new Date(D[3], this.shortMonthNum(D[2]), D[1], 12, 0);
            } else {
                return null;
            }
        }, dateToString: function (C) {
            return C.getDate() + " " + this.short_month_names[C.getMonth()] + " " + C.getFullYear();
        }, setPosition: function () {
            var C = this.input.offset();
            this.rootLayers.css({top: C.top + this.input.outerHeight(), left: C.left});
            if (this.ieframe) {
                this.ieframe.css({width: this.dateSelector.outerWidth(), height: this.dateSelector.outerHeight()});
            }
        }, moveDateBy: function (D) {
            var C = new Date(this.selectedDate.getFullYear(), this.selectedDate.getMonth(), this.selectedDate.getDate() + D);
            this.selectDate(C);
        }, moveDateMonthBy: function (D) {
            var C = new Date(this.selectedDate.getFullYear(), this.selectedDate.getMonth() + D, this.selectedDate.getDate());
            if (C.getMonth() == this.selectedDate.getMonth() + D + 1) {
                C.setDate(0);
            }
            this.selectDate(C);
        }, moveMonthBy: function (C) {
            var D = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth() + C, this.currentMonth.getDate());
            this.selectMonth(D);
        }, monthName: function (C) {
            return this.month_names[C.getMonth()];
        }, bindToObj: function (D) {
            var C = this;
            return function () {
                return D.apply(C, arguments);
            };
        }, bindMethodsToObj: function () {
            for (var C = 0; C < arguments.length; C++) {
                this[arguments[C]] = this.bindToObj(this[arguments[C]]);
            }
        }, indexFor: function (E, D) {
            for (var C = 0; C < E.length; C++) {
                if (D == E[C]) {
                    return C;
                }
            }
        }, monthNum: function (C) {
            return this.indexFor(this.month_names, C);
        }, shortMonthNum: function (C) {
            return this.indexFor(this.short_month_names, C);
        }, shortDayNum: function (C) {
            return this.indexFor(this.short_day_names, C);
        }, daysBetween: function (D, C) {
            D = Date.UTC(D.getFullYear(), D.getMonth(), D.getDate());
            C = Date.UTC(C.getFullYear(), C.getMonth(), C.getDate());
            return (C - D) / 86400000;
        }, changeDayTo: function (C, D, E) {
            var F = E * (Math.abs(D.getDay() - C - (E * 7)) % 7);
            return new Date(D.getFullYear(), D.getMonth(), D.getDate() + F);
        }, rangeStart: function (C) {
            return this.changeDayTo(this.start_of_week, new Date(C.getFullYear(), C.getMonth()), -1);
        }, rangeEnd: function (C) {
            return this.changeDayTo((this.start_of_week - 1) % 7, new Date(C.getFullYear(), C.getMonth() + 1, 0), 1);
        }, isFirstDayOfWeek: function (C) {
            return C.getDay() == this.start_of_week;
        }, isLastDayOfWeek: function (C) {
            return C.getDay() == (this.start_of_week - 1) % 7;
        }, adjustDays: function (E) {
            var D = [];
            for (var C = 0; C < E.length; C++) {
                D[C] = E[(C + this.start_of_week) % 7];
            }
            return D;
        }
    };
    A.fn.date_input = function (C) {
        return this.each(function () {
            new B(this, C);
        });
    };
    A.date_input = {
        initialize: function (C) {
            A("input.date_input").date_input(C);
        }
    };
    return B;
})(jQuery);
jQuery.extend(DateInput.DEFAULT_OPTS, {
    month_names: ["\u4e00\u6708", "\u4e8c\u6708", "\u4e09\u6708", "\u56db\u6708", "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708", "\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708", "\u5341\u4e00\u6708", "\u5341\u4e8c\u6708"],
    short_day_names: ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"]
});
jQuery.extend(DateInput.DEFAULT_OPTS, {
    stringToDate: function (A) {
        var B;
        if (B = A.match(/^(\d{4,4})-(\d{2,2})-(\d{2,2})$/)) {
            return new Date(B[1], B[2] - 1, B[3]);
        } else {
            return null;
        }
    }, dateToString: function (A) {
        var B = (A.getMonth() + 1).toString();
        var C = A.getDate().toString();
        if (B.length == 1) {
            B = "0" + B;
        }
        if (C.length == 1) {
            C = "0" + C;
        }
        return A.getFullYear() + "-" + B + "-" + C;
    }
});

