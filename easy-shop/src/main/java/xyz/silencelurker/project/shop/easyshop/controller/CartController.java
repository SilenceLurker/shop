package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Cart;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.service.ICartService;

import org.springframework.web.bind.annotation.PostMapping;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Silence_Lurker
 */
@Log4j2
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@ApiResponses
@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    private ICartService cartService;

    @PostMapping("/createCart")
    public ResponseEntity<?> createCart(@CookieValue String token) {
        if (token.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var cart = new Cart();

        var map = decodeToken(token);
        var id = Integer.parseInt(map.get("id"));
        cart.setUserId(id);
        var cartId = cartService.createCart(cart);

        return ResponseEntity.ok().body(cartId);
    }

    @GetMapping("/getAllCart")
    public ResponseEntity<?> getAllCart(@CookieValue String token) {
        if (token.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var list = cartService.getAllCart(Integer.parseInt(decodeToken(token).get("id")));

        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/addProductions")
    public ResponseEntity<?> addProductions(String id, Long itemId, short count) {
        log.info(id);

        cartService.addProduction(id, itemId, count);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteProduction")
    @PostMapping("/deleteProduction")
    public ResponseEntity<?> deleteProduction(String id, Long itemId) {
        try {
            cartService.deleteProduction(id, itemId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cartItems")
    public ResponseEntity<?> getCartItems(@RequestParam String cartId) {
        return ResponseEntity.ok().body(cartService.findById(cartId));
    }

    @Data
    public static class ResultProduction{
        Production production;
        Short number;

        public ResultProduction(){
            super();
        }
    }


    @GetMapping("/cartItemList")
    public ResponseEntity<?> getCartItemList(@RequestParam String cartId) {
        log.info("000" + cartId + "000");
        var items =  cartService.getProductionByCartId(cartId);

        var it = items.entrySet().iterator();

        var result = new ArrayList<ResultProduction>();

        while(it.hasNext()){
            var item = it.next();

            var res = new ResultProduction();
            res.number = item.getValue();
            res.production = item.getKey();
            
            result.add(res);
        }

        return ResponseEntity.ok().body(result);
    }

}
