package com.atguigu.myssm.trans;

import java.sql.Connection;

/**
 * @author keyboardhero
 * @create 2023-03-05 16:26
 */
public class TransactionManager {
    private ThreadLocal<Connection> threadLocal=new ThreadLocal<>();

    //开启事务
    public void beginTrans(){

    }
    //提交事务
    public void commit(){

    }
    //回滚事务
    public void rollback(){

    }
}
