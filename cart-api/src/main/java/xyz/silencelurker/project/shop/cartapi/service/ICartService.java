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
}
