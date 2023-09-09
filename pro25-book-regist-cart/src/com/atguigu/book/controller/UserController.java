package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserController {

    private UserService userService ;
    private CartItemService cartItemService ;

    public String login(String uname , String pwd , HttpSession session){

        User user = userService.login(uname, pwd);
        if(user!=null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser",user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String regist(String verifyCode, String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptchaCodeobj=session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptchaCodeobj==null || !verifyCode.equals(kaptchaCodeobj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>" +
                    "alert('验证码不正确!');" +

                    "</script>");
            //return null;
            return "user/regist";
        }else{
            if(verifyCode.equals(kaptchaCodeobj)){
                userService.regist(new User(uname,pwd,email,0));
                return "user/login";
            }
        }

        return "user/login";
    }

    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if(user!=null){
            //表示用户名已经被占用，不可以注册
            return "json:{'uname':'1'}";   //传了一个字符串给客户端, (注：封装原因没去实现)
        }else{
            //用户名可以注册
            return "json:{'uname':'0'}";
        }
    }
}
