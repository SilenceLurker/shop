package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.controller.OrderController.ResultOrderInfo.ResultEntry;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.service.ICartService;
import xyz.silencelurker.project.shop.easyshop.service.IOrderInfoService;
import xyz.silencelurker.project.shop.easyshop.service.IProductionService;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

import java.util.ArrayList;
import java.util.List;

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
    @Resource
    private IProductionService productionService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestParam String cartId, @RequestParam String sendInfoId,
            @CookieValue String token) {

        var cart = cartService.findById(cartId);

        var orderInfo = orderInfoService.createOrderInfo(cart);

        orderInfo.setSendInfoId(sendInfoId);

        var userInfo = decodeToken(token);

        orderInfo.setAccountId(Integer.parseInt(userInfo.get("id")));

        orderInfoService.save(orderInfo);

        log.info(orderInfo);

        cartService.deleteCart(cartId);

        return ResponseEntity.ok().body(orderInfo);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<?> getOrder(String orderId) {
        return ResponseEntity.ok(orderInfoService.getOrderInfo(orderId));

    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrderByUser(@CookieValue String token) {
        log.info(decodeToken(token));
        return ResponseEntity.ok()
                .body(orderInfoService.getAllOrderInfoByAccountId(Integer.parseInt(decodeToken(token).get("id"))));

    }

    @Data
    public static class ResultOrderInfo {
        String id;
        ResultEntry[] result;

        @Data
        public static class ResultEntry {
            Production production;
            Short count;

            public ResultEntry() {
                super();
            }
        }

        public ResultOrderInfo() {
            super();
        }
    }

    ExampleMatcher exampleProMacher = ExampleMatcher.matching()
            .withIgnorePaths("price", "time", "subId", "enable", "color", "brand", "system", "type", "memoryAndDisk")
            .withIgnoreNullValues();

    @GetMapping("/getAllOrderInfo")
    public ResponseEntity<?> getAllOrderInfoByUserToken(@CookieValue String token) {
        var info = decodeToken(token);

        var map = orderInfoService.getAllOrderInfoByAccountId(Integer.parseInt(info.get("id")));

        List<ResultEntry> entrys = new ArrayList<>();

        var it = map.iterator();

        var tResult = new ArrayList<ResultOrderInfo>();

        while (it.hasNext()) {
            var item = it.next();

            var tempOrder = new ResultOrderInfo();

            tempOrder.setId(item.getId());

            var odItems = item.getItems();

            var odit = odItems.entrySet().iterator();

            while (odit.hasNext()) {
                var odItem = odit.next();

                var entry = new ResultEntry();

                var target = new Production();
                target.setId(odItem.getKey());

                entry.setProduction(productionService.getAllByExample(Example.of(target, exampleProMacher)).get(0));
                entry.setCount(odItem.getValue());
                entrys.add(entry);
            }

            tempOrder.setResult(entrys.toArray(new ResultEntry[entrys.size()]));

            tResult.add(tempOrder);
        }

        return ResponseEntity.ok().body(tResult);
    }

}
