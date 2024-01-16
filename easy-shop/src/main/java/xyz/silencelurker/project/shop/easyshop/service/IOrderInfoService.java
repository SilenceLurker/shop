package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import xyz.silencelurker.project.shop.easyshop.entity.Cart;
import xyz.silencelurker.project.shop.easyshop.entity.OrderInfo;

/**
 * @author Silence_Lurker
 */
public interface IOrderInfoService {
    /**
     * create order by cart;
     * 
     * @param cart
     * @return
     */
    OrderInfo createOrderInfo(Cart cart);

    /**
     * get order info by id
     * 
     * @param id
     * @return
     */
    OrderInfo getOrderInfo(String id);

    /**
     * get all
     * 
     * @param accountId
     * @return
     */
    List<OrderInfo> getAllOrderInfoByAccountId(int accountId);

    /**
     * save
     * 
     * @param newInfo
     */
    void save(OrderInfo newInfo);
}
