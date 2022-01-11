var GetLogoInfo = function (B, C, D, A) {
    GetJPData("http://poster.1yyg.com", C, "ID=" + B, function (G) {
        var H = "";
        if (G.state == 0) {
            var F = G.listItems;
            for (var E = 0; E < F.length; E++) {
                if (F[E].type == 1) {
                    H += "<embed src=\"" + F[E].src + "\" quality=\"high\" pluginspage=\"http://www.adobe.com/shockwave/download/download.cgi?p1_prod_version=shockwaveflash\" type=\"application/x-shockwave-flash\" ";
                    if (D) {
                        H += " wmode=\"Transparent\"";
                    }
                    H += " width=\"" + F[E].width + "\" height=\"" + F[E].height + "\"></embed>";
                } else {
                    H += "<a href=\"" + F[E].url + "\" target=\"_blank\"><img src=\"" + F[E].src + "\" alt=\"" + F[E].alt.reAjaxStr() + "\" width=\"" + F[E].width + "\" height=\"" + F[E].height + "\" /></a>";
                }
            }
        }
        A(H);
    });
};

