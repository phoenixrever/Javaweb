package com.phoenixhell.javatomcat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        //获取在web.xml 中配置的上下文参数 所有servlet都可以获取
        ServletContext servletContext = getServletContext();
        String application = servletContext.getInitParameter("application");
        System.out.println("MyServlet 获取在web.xml 中配置的上下文参数======>"+application);

        String message="MyServlet";

        //接受HelloServlet 转发过来的请求
        String key = (String) request.getAttribute("key");
        String forward = (String) request.getAttribute("forward");
        message=forward;
        System.out.println("接受转发的key数据------------->"+key);
        System.out.println("接受转发的forward数据------------->"+message);
        // MyServlet
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
