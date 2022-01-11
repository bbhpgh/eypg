package com.eypg.util;


public class WeiboHelper {
    private static String str62keys = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        System.err.println(Id2Mid("3564096450009560"));
        System.err.println(Mid2Id("zqTrn02uc"));
    }

    public static String IntToEnode62(Integer int10) {
        String s62 = "";
        int r = 0;
        while (int10 != 0) {
            r = Integer.parseInt(String.valueOf((int10 % 62)));
            s62 = str62keys.charAt(r) + s62;
            String s = String.valueOf(Math.floor(int10 / 62));
            s = s.substring(0, s.length() - 2);
//            System.err.println(s);
            int10 = Integer.parseInt(s);
        }
        return s62;
    }

    public static Integer Encode62ToInt(String str62) {
        Integer i10 = 0;
        for (int i = 0; i < str62.length(); i++) {
            double n = str62.length() - i - 1;
//            i10 += Convert.ToInt64(str62keys.IndexOf(str62.charAt(i)) * Math.pow(62, n));
            i10 += str62keys.indexOf(str62.charAt(i)) * Math.pow(62, n);
        }
        return i10;
    }

    public static String Mid2Id(String str62) //yvr29p8dG  
    {
        String id = "";
        for (int i = str62.length() - 4; i > -4; i = i - 4) //从最后往前以4字节为一组读取字符
        {
            int offset = i < 0 ? 0 : i;
            int len = i < 0 ? str62.length() % 4 : offset + 4;
            String str = Encode62ToInt(str62.substring(offset, len)).toString();
//             System.out.println(offset+"GGG"+str);
            if (offset > 0) //若不是第一组，则不足7位补0
            {
                while (str.length() < 7) {
                    str = '0' + str;
                }
            }
            id = str + id;
        }
        return id;
    }

    public static String Id2Mid(String str10) //3474920895989800 
    {
        String mid = "";
        for (int i = str10.length() - 7; i > -7; i = i - 7) //从最后往前以7字节为一组读取字符  
        {
            int offset = i < 0 ? 0 : i;
            int len = i < 0 ? str10.length() % 7 : offset + 7;
            String str = IntToEnode62(Integer.parseInt(str10.substring(offset, len)));
            if (offset > 0) //若不是第一组，则不足7位补0
            {
                while (str.length() < 4) {
                    str = '0' + str;
                }
            }
            mid = str + mid;
        }
        return mid;
    }

}
