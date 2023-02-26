package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author keyboardhero
 * @create 2023-02-26 16:19
 */
/*
@WebServlet(urlPatterns = {"/demo01"} ,
        initParams = {
            @WebInitParam(name="hello",value="world"),
            @WebInitParam(name="uname",value="jim")
        }
        )
*/
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue1 = config.getInitParameter("hello");
        String initValue2 = config.getInitParameter("uname");
        System.out.println("initValue = " + initValue1);
        System.out.println("initValue = " + initValue2);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = " + contextConfigLocation);
    }
}

//Servlet生命周期：实例化、初始化、服务、销毁

