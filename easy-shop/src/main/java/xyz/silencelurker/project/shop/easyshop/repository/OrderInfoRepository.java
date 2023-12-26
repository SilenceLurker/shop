package xyz.silencelurker.project.shop.easyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.silencelurker.project.shop.easyshop.entity.OrderInfo;

/**
 * @author Silence_Lurker
 */
public interface OrderInfoRepository extends JpaRepository<OrderInfo, String> {

}
