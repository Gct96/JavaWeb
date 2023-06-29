package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartItemDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author keyboardhero
 * @create 2023-04-10 17:07
 */
public class CartItemServiceImpl implements CartItemService {

    private CartItemDAO cartItemDAO;
    private BookService bookService;
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //判断当前用户的购物车是否有这本书的item，有->update,无->add
        if(cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap==null){
                cartItemMap=new HashMap<>();
            }

            if(cartItemMap.containsKey(cartItem.getBook().getId())){ //有->update
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                updateCartItem(cartItemTemp);
            }else{//无->add
                addCartItem(cartItem);
            }
        }else{//连购物车都没有的情况
            addCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);//只有id
        for(CartItem cartItem:cartItemList){
            Book book=bookService.getBook(cartItem.getBook().getId());//获取book
            cartItem.setBook(book);
        }
        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList=getCartItemList(user);
         //把list转化成map再封装到cart
        Map<Integer,CartItem> cartItemMap=new HashMap<>();
        //准备Map
        for(CartItem cartItem:cartItemList){
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart=new Cart();
        cart.setCartItemMap(cartItemMap);

        return cart;
    }
}
