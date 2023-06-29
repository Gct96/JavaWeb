package com.atguigu.book.controller;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author keyboardhero
 * @create 2023-06-02 15:38
 */
public class OrderController {

    private OrderService orderService;
    //结账
    public String checkout(HttpSession session){
        OrderBean orderBean=new OrderBean();
        Date now=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+Integer.toString(calendar.get(Calendar.YEAR))+Integer.toString(calendar.get(Calendar.MONTH)+1)+Integer.toString(calendar.get(Calendar.DATE))+Integer.toString(calendar.get(Calendar.HOUR_OF_DAY))+Integer.toString(calendar.get(Calendar.MINUTE))+Integer.toString(calendar.get(Calendar.SECOND)));
        orderBean.setOrderDate(now);

        User user=(User)session.getAttribute("currUser");
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);


        return "index";
    }
    //查看订单列表
    public String getOrderList(HttpSession session){
        User user=(User) session.getAttribute("currUser");

        List<OrderBean> orderList = orderService.getOrderList(user);
        //session.setAttribute("",orderList);
        user.setOrderList(orderList);

        session.setAttribute("currUser",user);
        return "order/order";
    }

}
