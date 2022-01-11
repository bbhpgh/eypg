package com.eypg.util;

public class PaginationUtil {
    public static void main(String[] args) {
        System.out.println(getPaginationHtml(600, 30, 1, 2, 5, "/xxx/ddd/{page}/sss"));
    }

    /**
     * 接收参数，生成分页的静态HTML字符串。
     *
     * @param allTotal       总记录数
     * @param currentRows    每页显示数
     * @param currentPage    当前页
     * @param edgeEntries    边缘页数
     * @param displayEntries 主体页数
     * @param link_to        链接(需要加{page}才能显示连接的页码)
     * @return 分页的html字符串
     */
    public static String getPaginationHtml(Integer allTotal, Integer currentRows, Integer currentPage, Integer edgeEntries, Integer displayEntries, String link_to) {
        StringBuffer sb = new StringBuffer();
        //获取总页码  
        int total = 0;
        if (allTotal % currentRows > 0) {
            total = (int) Math.floor(allTotal / currentRows + 1);
        } else {
            total = (int) Math.floor(allTotal / currentRows);
        }
        //判断如果选中页码大于总页码。则显示总页码数  
        if (total < currentPage) {
            currentPage = total;
        }
        //左边显示总数量和总页数  
        sb.append("<div class=\"pages\"><ul id=\"pagination\" class=\"pageULEx\">");
        if (allTotal > 0) {
            sb.append("<li class=\"total_page\">页次<i>" + currentPage + "/" + total + "</i>&nbsp;&nbsp;共<i>" + allTotal + "</i>条记录</li>");
        }
        //上一页  
        if (currentPage == 1) {
            sb.append("<li class=\"prev_page page_curgray\"><a><i>&lt;</i>上一页</a></li>");
//        	sb.append("<li class=\"curr_page\">1</li>");
        } else {
            if (allTotal > 0) {
                sb.append("<li class=\"prev_page\"><a href=\"" + link_to + (currentPage - 1) + ".html" + "\">上一页</a></li>");
            }
        }
        //判断总页码是否大于需显示的页码数  
        if ((edgeEntries * 2) + displayEntries > total) {
            //显示数大于总页数  
            for (int i = 1; i <= total; i++) {
                if (i == currentPage) {
                    sb.append("<li class=\"curr_page\">" + (i) + "</li>");
                } else {
                    sb.append("<li><a href=\"" + link_to + i + ".html" + "\">" + i + "</a></li>");
                }
            }
        } else if (currentPage <= 5) {
            //页码小于或者等于5时  
            Integer currentTotal = 1;
            if (currentPage < 4) {
                currentTotal = 4;
            } else {
                currentTotal = currentPage + 1;
            }
            for (int i = 1; i <= currentTotal; i++) {
                if (i == currentPage) {
                    sb.append("<li class=\"curr_page\">" + (i) + "</li>");
                } else {
                    sb.append("<li><a href=\"" + link_to + i + ".html" + "\">" + i + "</a></li>");
                }
            }

            sb.append("<li>...</li>");
//            sb.append("<li><a href=\""+link_to+(total - 1)+".html"+"\">"+(total - 1)+"</a></li>");
            sb.append("<li><a href=\"" + link_to + total + ".html" + "\">" + total + "</a></li>");
        } else if ((total - 4) <= currentPage) {
            sb.append("<li><a href=\"" + link_to + 1 + ".html" + "\">1</a></li>");
//            sb.append("<li><a href=\""+link_to+2+".html"+"\">2</a></li>");  
            sb.append("<li>...</li>");

            Integer currentTotal = 0;
            if ((total - 1) > currentPage) {
                currentTotal = currentPage - 1;
            } else {
                currentTotal = total - 2;
            }
            for (int i = currentTotal; i <= total; i++) {
                if (i == currentPage) {
                    sb.append("<li class=\"curr_page\">" + i + "</li>");
                } else {
                    sb.append("<li><a href=\"" + link_to + i + ".html" + "\">" + i + "</a></li>");
                }
            }
        } else {
            sb.append("<li><a href=\"" + link_to + 1 + ".html" + "\">1</a></li>");
//            sb.append("<li><a href=\""+link_to+2+".html"+"\">2</a></li>");  
            sb.append("<li>...</li>");

//            sb.append("<li><a href=\""+link_to+(currentPage - 3)+".html"+"\">"+(currentPage - 3)+"</a></li>"); 
            sb.append("<li><a href=\"" + link_to + (currentPage - 2) + ".html" + "\">" + (currentPage - 2) + "</a></li>");
            sb.append("<li><a href=\"" + link_to + (currentPage - 1) + ".html" + "\">" + (currentPage - 1) + "</a></li>");

            sb.append("<li class=\"curr_page\">" + currentPage + "</li>");

            sb.append("<li><a href=\"" + link_to + (currentPage + 1) + ".html" + "\">" + (currentPage + 1) + "</a></li>");
            sb.append("<li><a href=\"" + link_to + (currentPage + 2) + ".html" + "\">" + (currentPage + 2) + "</a></li>");

            sb.append("<li>...</li>");
//            sb.append("<li><a href=\""+link_to+(total - 1)+".html"+"\">"+(total - 1)+"</a></li>");
            sb.append("<li><a href=\"" + link_to + total + ".html" + "\">" + total + "</a></li>");
        }
        //下一页  
        if (currentPage == total) {
            sb.append("<li class=\"prev_page page_curgray\"><a>下一页<i>&gt;</i></a></li>");
//        	sb.append("<li class=\"next_page\"><a href=\"javascript:void(0);\">下一页</a></li>"); 
        } else {
            sb.append("<li class=\"next_page\"><a href=\"" + link_to + (currentPage + 1) + ".html" + "\">下一页</a></li>");
        }
        sb.append("</ul></div>");
        return sb.toString();
    }
}
