package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Cart;
import xyz.silencelurker.project.shop.easyshop.repository.CartRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class ICartServiceImpl implements ICartService {
    @Resource
    private CartRepository cartRepository;

    @Override
    public String createCart(Cart newCart) {
        cartRepository.save(newCart);
        return "success!";
    }

    @Override
    public void addProduction(String cartId, long productionId, short count) {
        var cart = cartRepository.findById(cartId).get();
        if (cart == null) {
            return;
        }

        var items = cart.getItems();
        short num = items.get(productionId) == null ? 0 : items.get(productionId);
        items.put(productionId, (short) (num + count));
        cart.setItems(items);

        cartRepository.save(cart);
    }

    @Override
    public void deleteProduction(String id, long productionId) {
        var cart = cartRepository.findById(id).get();
        if (cart == null) {
            return;
        }
        var items = cart.getItems();
        items.remove(productionId);
        cart.setItems(items);
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart(int accountId) {
        return cartRepository.findAll();
    }

}
