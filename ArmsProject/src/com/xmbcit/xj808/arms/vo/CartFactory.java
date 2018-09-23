package com.xmbcit.xj808.arms.vo;

import com.xmbcit.xj808.arms.entity.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车创建
 */
public class CartFactory {
    /*购物车工厂类*/
    private volatile static CartFactory factory = null;

    public synchronized static CartFactory getFactory() {
        if (factory == null)
            factory = new CartFactory();
        return factory;
    }


    private volatile Map<String, Cart> cartMap;//购物车集合

    private CartFactory() {
        cartMap = new HashMap<>();
    }

    /**
     * 获取购物车！不过不存在，则新建一个!
     *
     * @param customer
     * @return 如果参数为空，无法创建购物车
     */
    public synchronized Cart getCart(Customer customer) {
        if (customer == null)
            return null;
        Cart cart = cartMap.get(customer.getId());
        if(cart == null ){
            cart = new Cart();
            cart.setCostomer(customer);
            cartMap.put(customer.getId(),cart);//加入
        }
        return cart;
    }

}
