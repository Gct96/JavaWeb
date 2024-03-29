package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;

import javax.servlet.http.HttpSession;

/**
 * @author keyboardhero
 * @create 2023-04-10 16:48
 */
public class CartController {

    private CartItemService cartItemService;
    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart"; // /pages/cart/cart.html
    }

    public String addCart(Integer bookId, HttpSession session){
        //1.用户的购物车中已经有这本书  2.没有这本书
        //两种情况对应DAO中的两个操作
        User user = (User) session.getAttribute("currUser");
        CartItem cartItem=new CartItem(new Book(bookId),1,user);
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "redirect:cart.do";
    }
}
