package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartItemDAO;
import com.atguigu.book.dao.OrderDAO;
import com.atguigu.book.dao.OrderItemDAO;
import com.atguigu.book.dao.impl.OrderItemDAOImpl;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.OrderItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;
import java.util.Map;

/**
 * @author keyboardhero
 * @create 2023-06-02 15:18
 */
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;


    @Override
    public void addOrderBean(OrderBean orderBean) {
        /*
        订单表添加一条记录
        订单详情表添加7条记录
        购物车项表中需要删除对应的7条记录
         */

        //订单表添加一条记录
        orderDAO.addOrderBean(orderBean);

        //订单详情表添加7条记录
        //orderBean中的orderItemList是空的，应根据用户的购物车中的购物车项去转换成每个的订单项
        User currUser=orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for(CartItem cartItem:cartItemMap.values()){
            OrderItem orderItem=new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //购物车项表中需要删除对应的7条记录
        for(CartItem cartItem:cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderDAO.getOrderList(user);
        for(OrderBean orderBean:orderBeanList){
            Integer totalBookCount=orderDAO.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }
        return orderBeanList;
    }
}
