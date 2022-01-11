package com.eypg.test;

public class TestPage {

    public static void main(String[] args) {
        System.err.println(getPageRange("/admin_back/productList.action/", 15, 50, 5, 50));
    }

    public static String getPageRange(String jspName, int pageNo, int totalPage, int pageSize, int resultCount) {
        int startPageNo = (pageNo) / pageSize * pageSize;
        int endPageNo = startPageNo + pageSize;
        StringBuffer buf = new StringBuffer();
        if (startPageNo > 0)
            buf.append("<a href=\"" + jspName + "?pageNo=" + (startPageNo - pageSize + 1) + "\">[上一页]</a>");
        if (totalPage >= pageSize) {
            if (totalPage >= endPageNo)
                for (int i = 1; i <= pageSize; i++)
                    buf.append("<a href=\"" + jspName + "?pageNo=" + (startPageNo + i) +
                            "\">[" + (startPageNo + i) + "]</a>&nbsp;&nbsp;");
            else
                for (int i = 1; i <= totalPage - startPageNo; i++)
                    buf.append("<a href=\"" + jspName + "?pageNo=" + (startPageNo + i) +
                            "\">[" + (startPageNo + i) + "]</a>&nbsp;&nbsp;");

        } else
            for (int i = 1; i <= totalPage; i++)
                buf.append("<a href=\"" + jspName + "?pageNo=" + (startPageNo + i) + "\">[" + (startPageNo + i) + "]</a>&nbsp;&nbsp;");

        if (endPageNo != totalPage && endPageNo < totalPage)
            buf.append("<a href=\"" + jspName + "?pageNo=" + (endPageNo) + "\">[下一页]</a>");
        buf.append("&nbsp;&nbsp;&nbsp;当前页：" + pageNo + "&nbsp;&nbsp;总页数：" + totalPage + "&nbsp;&nbsp;共" + resultCount + "条记录");
        return buf.toString();
    }

}
