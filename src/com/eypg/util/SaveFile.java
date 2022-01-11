package com.eypg.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveFile {

    public void saveLoginFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\login.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveLoginErrorFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\loginError.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void saveProxyErrorFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\proxyError.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUpdatePwdFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\updatePwd.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveIpFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\ip.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUpdateErrorFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\updateError.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUpdateFaceFile(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\updateFace.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveLoginError(String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File("E:\\saveLoginError.txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void svaeWeibo(String fileName, String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File(fileName);
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 保存文本文件
     *
     * @param FileName
     * @param str
     */
    public synchronized void saveFile(String FileName, String str) {
        String s = new String();
        String s1 = new String();
        try {
            File f = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + FileName + ".txt");
            if (f.exists()) {
//		    System.out.print("文件存在");
            } else {
                System.out.print("文件不存在，已创建");
                f.createNewFile();//不存在就创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((s = input.readLine()) != null) {
                s1 += s + "\r\n";
            }
//		   System.out.println(s1);
            input.close();
            s1 += str;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
