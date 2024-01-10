package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import xyz.silencelurker.project.shop.easyshop.entity.Cart;

/**
 * @author Silence_Lurker
 */
public interface ICartService {

    /**
     * save a new Cart
     * 
     * @param newCart
     * @return
     */
    String createCart(Cart newCart);

    /**
     * add production into cart
     * 
     * @param cartId
     * @param productionId
     * @param count
     */
    void addProduction(String cartId, long productionId, short count);

    /**
     * delete target production
     * 
     * @param id
     * @param productionId
     */
    void deleteProduction(String id, long productionId);

    /**
     * get all cart by this account
     * 
     * @param accountId
     * @return
     */
    List<Cart> getAllCart(int accountId);

}
