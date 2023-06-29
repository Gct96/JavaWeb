package com.atguigu.book.pojo;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

/**
 * 我们应该还需要设计一个Cart类，代表购物车这个实体
 * @author keyboardhero
 * @create 2023-04-10 17:21
 */
public class Cart {

    private Map<Integer,CartItem> cartItemMap; //购物车中购物项的集合,key是book的id
    private Double totalMoney;
    private Integer totalCount;                //购物项的数量，即cartItemMap的size
    private Integer totalBookCount;        //购物车中书本的总数量
    public Cart(){

    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney=0.00;
        DecimalFormat df = new DecimalFormat("0.00");
        if(cartItemMap!=null && cartItemMap.size()>0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer,CartItem> cartItemEntry:entries){
                CartItem cartItem = cartItemEntry.getValue();
                //totalMoney+=(Double)(cartItem.getBook().getPrice()*cartItem.getBuyCount());
                totalMoney+=Double.parseDouble(df.format(cartItem.getBook().getPrice()*cartItem.getBuyCount()));
            }
        }
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTotalCount() {
        totalCount=0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            totalCount=cartItemMap.size();
        }


        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount=0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            for(CartItem cartItem :cartItemMap.values()){
                totalBookCount=totalBookCount+cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
