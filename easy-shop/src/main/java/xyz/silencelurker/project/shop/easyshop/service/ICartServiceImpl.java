package xyz.silencelurker.project.shop.easyshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Cart;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.repository.CartRepository;
import xyz.silencelurker.project.shop.easyshop.repository.ProductionRepository;

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
        items.get(productionId);
        cart.setItems(items);
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart(int accountId) {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(String id) {
        return cartRepository.findById(id).get();
    }

    @Resource
    private ProductionRepository productionRepository;

    @Override
    public Map<Production, Short> getProductionByCartId(String cartId) {
        var cart = findById(cartId);

        var itemsInfo = cart.getItems();
        var itemList = itemsInfo.keySet().iterator();

        var result = new HashMap<Production, Short>();

        while (itemList.hasNext()) {
            var item = itemList.next();

            result.put(productionRepository.findById(item).get(), itemsInfo.get(item));
        }

        return result;

    }

    @Override
    public void deleteCart(String cartId) {
        cartRepository.deleteById(cartId);
    }

}
