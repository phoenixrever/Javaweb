package com.phoenixhell.javatomcat;

import java.io.*;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *
 * @WebServlet 是注解版
 * 没有注解的话需要到web.xml 里面配置
 *
 */
//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;


    /**
     * 1、可以获取Servlet 程序的别名servlet-name 的值
     * 2、获取初始化参数init-param
     * 3、获取ServletContext 对象
     */
    public void init() {
        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println(servletContext);

        String username = servletConfig.getInitParameter("username");
        System.out.println(username);

        String servletName = servletConfig.getServletName();
        System.out.println(servletName);

        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        //如果重写了父类的init方法 需要再重写方法里面调用父类的init方法 super
        //在init里面是没有问题的
        ServletConfig servletConfig = getServletConfig();
        String username = servletConfig.getInitParameter("username");
        System.out.println("doGet---------"+username);

        //1 servletContext 获取在web.xml 中配置的上下文参数
        ServletContext servletContext = servletConfig.getServletContext();
        String application = servletContext.getInitParameter("application");
        System.out.println("获取在web.xml 中配置的上下文参数======>"+application);

        //2 servletContext 获取当前工程路径
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath); ///java_tomcat

        //3 servletContext 获取当前工程绝对路径
        // 斜杠被服务器解析地址为:http://ip:port/工程名/ 映射到IDEA 代码的web 目录<br/>
        String realPath = servletContext.getRealPath("/");
        Set<String> resourcePaths = servletContext.getResourcePaths("/");

        System.out.println(realPath); //D:\code\Javaweb\java-tomcat\target\java-tomcat-1.0-SNAPSHOT\
        //  [/META-INF/, /index.jsp, /WEB-INF/]
        System.out.println(resourcePaths);


        //4、像Map 一样存取数据
        servletContext.setAttribute("key","value");


        //请求的转发
        request.setAttribute("forward", "转发数据");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/MyServlet");
        requestDispatcher.forward(request,response);
        System.out.println("转发之后的代码还是会运行");

        // Hello
        //PrintWriter out = response.getWriter();
        //out.println("<html><body>");
        //out.println("<h1>" + message + "</h1>");
        //out.println("</body></html>");
    }

    public void destroy() {
    }
}