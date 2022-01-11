package com.eypg.util;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrettyDateFormat extends SimpleDateFormat {

    private static final long serialVersionUID = 3740476016364838854L;

    private Pattern pattern = Pattern.compile("('*)(#{1,2}|@)");
    private FormatType formatType = FormatType.DEAFULT;
    private SimpleDateFormat simpleDateFormat;

    private enum FormatType {
        DEAFULT, TIME, DAY
    }

    ;

    /**
     * 构造器
     * <p>
     * format中的@表示[XXX秒前,XXX分钟前,XXX小时前(最多是23小时前)]
     * <p>
     * format中的#表示[空字串(表示今天),昨天,前天]
     * <p>
     * format中的##表示[今天,昨天,前天]
     *
     * @param format     和SimpleDateFormat中的格式设置基本上是一样的,只是多的@格式 #格式和##格式
     * @param fullFormat 和SimpleDateFormat中的格式设置是一样的
     */
    public PrettyDateFormat(String format, String fullFormat) {
        super(fullFormat);
        Matcher m = pattern.matcher(format);
        while (m.find()) {
            if (m.group(1).length() % 2 == 0) {
                if ("@".equals(m.group(2))) {
                    if (formatType == FormatType.DAY) {
                        throw new IllegalArgumentException("#和@模式字符不能同时使用.");
                    }
                    formatType = FormatType.TIME;
                } else {
                    if (formatType == FormatType.TIME) {
                        throw new IllegalArgumentException("#和@模式字符不能同时使用.");
                    }
                    formatType = FormatType.DAY;
                }
            }
        }
        this.simpleDateFormat = new SimpleDateFormat(format.replace("'", "''"));
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException("暂时还不支持的操作");
    }

    /*
     * (non-Javadoc)
     *
     * @see java.text.SimpleDateFormat#format(java.util.Date,
     *      java.lang.StringBuffer, java.text.FieldPosition)
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo,
                               FieldPosition pos) {
        if (formatType == FormatType.DEAFULT) {
            return super.format(date, toAppendTo, pos);
        }

        long curTime = System.currentTimeMillis();

        long diffDay = 0L;
        long diffSecond = 0L;
        if (formatType == FormatType.TIME) {
            diffSecond = (curTime - date.getTime()) / 1000L;
            if (diffSecond < 0 || diffSecond >= 86400) {
                return super.format(date, toAppendTo, pos);
            }
        }
        if (formatType == FormatType.DAY) {
            Calendar curDate = new GregorianCalendar();
            curDate.setTime(new Date(curTime));
            curDate.set(Calendar.HOUR_OF_DAY, 23);
            curDate.set(Calendar.MINUTE, 59);
            curDate.set(Calendar.SECOND, 59);
            curDate.set(Calendar.MILLISECOND, 999);
            diffDay = (curDate.getTimeInMillis() - date.getTime()) / 86400000L;
            if (diffDay < 0 || diffDay > 2) {
                return super.format(date, toAppendTo, pos);
            }
        }
        StringBuffer sb = new StringBuffer();
        Matcher m = pattern.matcher(simpleDateFormat.format(date));
        if (m.find()) {
            String group2 = m.group(2);
            String replacement = "";
            while (true) {
                if ("@".equals(group2)) {
                    if (diffSecond < 60) {
                        replacement = diffSecond == 0 ? "1秒前" : diffSecond
                                + "秒前";
                    } else if (diffSecond < 3600) {
                        replacement = diffSecond / 60 + "分钟前";
                    } else if (diffSecond < 86400) {
                        replacement = diffSecond / 3600 + "小时前";
                    }
                } else {
                    if (diffDay == 0) {
                        replacement = group2.length() == 2 ? "今天" : "";
                    } else if (diffDay == 1) {
                        replacement = "昨天";
                    } else {
                        replacement = "前天";
                    }
                }
                m.appendReplacement(sb, replacement);
                if (!m.find()) {
                    break;
                }
            }
            m.appendTail(sb);
        }
        return toAppendTo.append(sb.toString());
    }

    public static void format(long curTime, String format, String fullFormat) {
        System.out.println("    format: " + format);
        System.out.println("fullFormat: " + fullFormat);
        System.out.println();

        Date date2 = new Date(curTime - 30 * 1000L); // 30秒前
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(sdf.format(date2) + " 格式化为 : ");
        System.out.println(new PrettyDateFormat(format, fullFormat).format(date2));

        date2 = new Date(curTime - 3600 * 1000L * 6); // 六小时前
        System.out.print(sdf.format(date2) + " 格式化为 : ");
        System.out.println(new PrettyDateFormat(format, fullFormat).format(date2));

        date2 = new Date(curTime - 3600 * 1000L * 20); // 20小时前
        System.out.print(sdf.format(date2) + " 格式化为 : ");
        System.out.println(new PrettyDateFormat(format, fullFormat).format(date2));

        date2 = new Date(curTime - 3600 * 1000L * 54); // 54小时前
        System.out.print(sdf.format(date2) + " 格式化为 : ");
        System.out.println(new PrettyDateFormat(format, fullFormat).format(date2));

        date2 = new Date(curTime - 3600 * 1000L * 78); // 78小时前
        System.out.print(sdf.format(date2) + " 格式化为 : ");
        System.out.println(new PrettyDateFormat(format, fullFormat).format(date2));
        System.out.println("========================================================");

    }

    public static void main(String[] args) {
        long curTime = System.currentTimeMillis();

        format(curTime, "#a H点", "yy-MM-dd a H点");

        format(curTime, "##a H点", "yy-MM-dd a H点");

        format(curTime, "# HH:mm:dd", "yy-MM-dd HH:mm:dd");

        format(curTime, "# a HH:mm:dd", "yy-MM-dd HH:mm:dd");

        format(curTime, "## HH:mm", "yy-MM-dd a HH:mm");

        format(curTime, "## a HH:mm", "yy-MM-dd a HH:mm");

        format(curTime, "##", "yyyy-MM-dd");

        format(curTime, "@", "yyyy-MM-dd HH:mm:ss");
    }

}
