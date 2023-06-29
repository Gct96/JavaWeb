package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderItem;

/**
 * @author keyboardhero
 * @create 2023-05-23 15:49
 */
public interface OrderItemDAO {
    //添加订单项
    void addOrderItem(OrderItem orderItem);
}
