package com.eypg.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 在线人数的统计
 */
public class OnlineCounterListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent hse) {
        OnlineCounter.raise();
    }

    public void sessionDestroyed(HttpSessionEvent hse) {
        OnlineCounter.reduce();
    }
} 


/*
    在网站中经常需要进行在线人数的统计。过去的一般做法是结合登录和退出功能，即当用户输入用户名密码进行登录的时候计数器加1，然后当用户点击退出按钮退出系统的时候计数器减1。这种处理方式存在一些缺点，例如：用户正常登录后，可能会忘记点击退出按钮，而直接关闭浏览器，导致计数器减1的操作没有及时执行；网站上还经常有一些内容是不需要登录就可以访问的，在这种情况下也无法使用上面的方法进行在线人数统计。
　　我们可以利用Servlet规范中定义的事件监听器（Listener）来解决这个问题，实现更准确的在线人数统计功能。对每一个正在访问的用户，J2EE应用服务器会为其建立一个对应的HttpSession对象。当一个浏览器第一次访问网站的时候，J2EE应用服务器会新建一个HttpSession对象，并触发HttpSession创建事件，如果注册了HttpSessionListener事件监听器，则会调用HttpSessionListener事件监听器的sessionCreated方法。相反，当这个浏览器访问结束超时的时候，J2EE应用服务器会销毁相应的HttpSession对象，触发HttpSession销毁事件，同时调用所注册HttpSessionListener事件监听器的sessionDestroyed方法。
　　可见，对应于一个用户访问的开始和结束，相应的有sessionCreated方法和sessionDestroyed方法执行。这样，我们只需要在HttpSessionListener实现类的sessionCreated方法中让计数器加1，在sessionDestroyed方法中让计数器减1，就轻松实现了网站在线人数的统计功能。
　　下面就是利用HttpSessionListener实现在线人数统计的一个例子
　　首先，编写一个简单的计数器，代码如下：
  package gongfei.cmc.articles.onlinecounter; 
  public class OnlineCounter { 
      private static long online = 0;     
      public static long getOnline() { 
          return online; 
      }     
      public static void raise(){ 
          online++; 
      }  
      public static void reduce(){ 
          online--; 
     } 
  } 

　　然后，编写HttpSessionListener实现类，在这个实现类的sessionCreated方法中调用OnlineCounter的raise方法，在sessionDestroyed方法中调用OnlineCounter的reduce方法，代码如下：
  package gongfei.cmc.articles.onlinecounter; 
  import javax.servlet.http.HttpSessionEvent; 
  import javax.servlet.http.HttpSessionListener; 
  public class OnlineCounterListener implements HttpSessionListener { 
      public void sessionCreated(HttpSessionEvent hse) { 
          OnlineCounter.raise(); 
      } 
      public void sessionDestroyed(HttpSessionEvent hse) { 
          OnlineCounter.reduce(); 
      } 
  } 

　　再然后，把这个HttpSessionListener实现类注册到网站应用中，也就是在网站应用的web.xml中加入如下内容：
  <web-app> 
      …… 
      <listener> 
          <listener-class> 
              gongfei.cmc.articles.example.OnlineCounterListener 
          </listener-class> 
      </listener> 
      …… 
  </web-app> 

**/