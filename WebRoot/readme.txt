js css 压缩
java -jar yuicompressor-2.4.2.jar index.js -o index.min.js --charset utf-8

103.6.220.132   fb7c17f5

inux下实时查看tomcat运行日志
1、先切换到：cd usr/local/tomcat5/logs
2、tail -f catalina.out
3、这样运行时就可以实时查看运行日志了
4、退出：ctrl+c

无法make 
yum -y install gcc automake autoconf libtool make
安装g++:
yum -y install gcc gcc-c++

yum install -y httpd-devel pcre perl pcre-devel zlib zlib-devel GeoIP GeoIP-devel

nginx 无法启动
/usr/local/nginx/sbin/nginx: error while loading shared libraries: libpcre.so.1: cannot open shared object file: No such file or directory


1.解决apr not found问题——————>

   [root@yahoo test]# tar -zxf apr-1.4.5.tar.gz

   [root@yahoo apr-1.4.5]# ./configure --prefix=/usr/local/apr

   [root@yahoo apr-1.4.5]# make

   [root@yahoo apr-1.4.5]# make install

 2.解决APR-util not found问题>>>>

   [root@yahoo test]# tar -zxf apr-util-1.3.12.tar.gz

   [root@yahoo apr-util-1.3.12]# ./configure --prefix=/usr/local/apr-util -with- apr=/usr/local/apr/bin/apr-1-config

  [root@yahoo apr-util-1.3.12]# make
  [root@yahoo apr-util-1.3.12]# make install

3.编译Apache

  [root@yahoo httpd-2.3.12-beta]# ./configure --prefix=/usr/local/apache2 --with-apr=/usr/local/apr --with-apr-util=/usr/local/apr-util/

 [root@yahoo httpd-2.3.12-beta]# make
 [root@yahoo httpd-2.3.12-beta]# make install

[root@yahoo httpd-2.3.12-beta]# /usr/local/apache2/bin/apachectl start

在IE中输入地址 ，显示
It works!

ln -s /usr/local/lib/libpcre.so.1 /lib64
32位系统则：
ln -s /usr/local/lib/libpcre.so.1 /lib



linux命令
清空文件夹:   rm -rf 文件夹/*
安装rpm文件 : rpm -ivh MySQL-client-4.0.16-0.i386.rpm
安装sh文件: sudo sh *.sh
解压缩：tar -zxvf apache-tomcat-6.0.10.tar.gz ；//解压 
范例：
zip命令可以用来将文件压缩成为常用的zip格式。unzip命令则用来解压缩zip文件。
1. 我想把一个文件abc.txt和一个目录dir1压缩成为yasuo.zip：
＃ zip -r yasuo.zip abc.txt dir1
2.我下载了一个yasuo.zip文件，想解压缩：
# unzip yasuo.zip
3.我当前目录下有abc1.zip，abc2.zip和abc3.zip，我想一起解压缩它们：
＃ unzip abc\?.zip
注释：?表示一个字符，如果用*表示任意多个字符。
4.我有一个很大的压缩文件large.zip，我不想解压缩，只想看看它里面有什么：
# unzip -v large.zip
5.我下载了一个压缩文件large.zip，想验证一下这个压缩文件是否下载完全了
# unzip -t large.zip
6.我用-v选项发现music.zip压缩文件里面有很多目录和子目录，并且子目录中其实都是歌曲mp3文件，我想把这些文件都下载到第一级目录，而不是一层一层建目录：
# unzip -j music.zip

linux MySQL
启动mysql /etc/init.d/mysql start
停止 /usr/bin/mysqladmin -u root -p shutdown

3、自动启动

1)察看mysql是否在自动启动列表中

[root@test1 local]#　/sbin/chkconfig –-list

2)把MySQL添加到你系统的启动服务组里面去

[root@test1 local]#　/sbin/chkconfig　–-add　mysql

3)把MySQL从启动服务组里面删除。

[root@test1 local]#　/sbin/chkconfig　–-del　mysql


1、数据库目录
/var/lib/mysql/
2、配置文件
/usr/share/mysql(mysql.server命令及配置文件)
3、相关命令
/usr/bin(mysqladmin mysqldump等命令)
4、启动脚本
/etc/rc.d/init.d/(启动脚本文件mysql的目录)
 
 