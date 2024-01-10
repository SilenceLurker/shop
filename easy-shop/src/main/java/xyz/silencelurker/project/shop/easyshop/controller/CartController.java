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
import xyz.silencelurker.project.shop.easyshop.entity.Cart;
import xyz.silencelurker.project.shop.easyshop.service.ICartService;

import org.springframework.web.bind.annotation.PostMapping;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

/**
 * @author Silence_Lurker
 */
@CrossOrigin
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
        cartService.addProduction(id, itemId, count);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PostMapping("/deleteProduction")
    public ResponseEntity<?> deleteProduction(String id, Long itemId) {
        try {
            cartService.deleteProduction(id, itemId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
