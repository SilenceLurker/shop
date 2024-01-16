package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Cart;
import xyz.silencelurker.project.shop.easyshop.entity.OrderInfo;
import xyz.silencelurker.project.shop.easyshop.repository.OrderInfoRepository;

/**
 * @author Silence_Lurker
 */
@Log4j2
@Service
public class IOrderInfoServiceImpl implements IOrderInfoService {
    @Resource
    private OrderInfoRepository orderiInfoRepository;

    @Override
    public OrderInfo createOrderInfo(Cart cart) {
        var order = new OrderInfo();

        order.setItems(cart.getItems());
        order.setAccountId(cart.getUserId());

        return order;
    }

    @Override
    public OrderInfo getOrderInfo(String id) {
        return orderiInfoRepository.findById(id).get();
    }

    @Override
    public List<OrderInfo> getAllOrderInfoByAccountId(int accountId) {
        var order = new OrderInfo();
        order.setAccountId(accountId);

        var example = Example.of(order, ExampleMatcher.matching().withIgnorePaths("id", "items", "sendInfoId"));

        return orderiInfoRepository.findAll(example);
    }

    @Override
    public void save(OrderInfo newInfo) {
        log.info(newInfo);
       orderiInfoRepository.save(newInfo);
    }

}
