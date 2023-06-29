package com.atguigu.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicIconFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author keyboardhero
 * @create 2023-06-05 15:54
 */
@WebFilter(urlPatterns ={"*.do","*.html"},initParams = {
        @WebInitParam(name = "bai",value = "/pro24/page.do?operate=page&page=user/login,/pro24/user.do?null")
})
public class SessionFilter implements Filter {

    List<String> baiList=null;
    @Override
    public void init(FilterConfig config) throws ServletException {
        String bai = config.getInitParameter("bai");
        String[] baiArr=bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        String uri=request.getRequestURI();
        String queryString = request.getQueryString();
        String str=uri+"?"+queryString;
        if(baiList.contains(str)){
            filterChain.doFilter(request,response);
            return;
        }else { //请求的地址不在白名单中
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            if(currUserObj==null){ //没检测到用户
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{  //有用户
                filterChain.doFilter(request,response);
            }
        }




    }

    @Override
    public void destroy() {

    }
}
