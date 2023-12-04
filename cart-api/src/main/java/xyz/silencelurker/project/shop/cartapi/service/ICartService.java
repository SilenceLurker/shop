package xyz.silencelurker.project.shop.cartapi.service;

import java.util.List;

import xyz.silencelurker.project.shop.cartapi.entity.Cart;

/**
 * @author Silence_Lurker
 */
public interface ICartService {
    /**
     * Select Cart By Id
     * 
     * @param id
     * @return
     */
    Cart getCartById(int id);

    /**
     * <p>
     * This Method is only for Test And Debug!!!
     * </p>
     * 通过ID列表获取多个购物车信息
     * 
     * @param ids
     * @return
     */
    List<Cart> getCartByIds(List<Integer> ids);

    /**
     * 更新Cart
     * 
     * @param newCart
     * @return
     */
    Cart updateCart(Cart newCart);

    /**
     * 更新Cart某商品数量
     * 
     * @param userId
     * @param id
     * @param number
     * @return
     */
    Cart updateCart(Integer userId, Integer id, Integer number);

    /**
     * 添加新商品至购物车
     * 
     * @param userId
     * @param id
     * @param number
     * @param colorId
     * @param memoryId
     * @return
     */
    Cart addNewProduction(Integer userId, Integer id, Integer number, Integer colorId, Integer memoryId);
}
