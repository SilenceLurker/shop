package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.service.ICartService;
import xyz.silencelurker.project.shop.easyshop.service.IOrderInfoService;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

/**
 * @author Silence_Lurker
 */
@Log4j2
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@ApiResponses
@RequestMapping("/order")
@RestController
public class OrderController {
    @Resource
    private ICartService cartService;
    @Resource
    private IOrderInfoService orderInfoService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(String cartId, String sendInfoId, @CookieValue String token) {
        var cart = cartService.findById(cartId);

        var orderInfo = orderInfoService.createOrderInfo(cart);

        orderInfo.setSendInfoId(sendInfoId);

        var userInfo = decodeToken(token);

        orderInfo.setAccountId(Integer.parseInt(userInfo.get("id")));

        cartService.deleteCart(cartId);

        return ResponseEntity.ok().body(orderInfo);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<?> getOrder(String orderId) {
        return ResponseEntity.ok(orderInfoService.getOrderInfo(orderId));

    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrderByUser(@CookieValue String token) {
        return ResponseEntity.ok()
                .body(orderInfoService.getAllOrderInfoByAccountId(Integer.parseInt(decodeToken(token).get("id"))));

    }

}
