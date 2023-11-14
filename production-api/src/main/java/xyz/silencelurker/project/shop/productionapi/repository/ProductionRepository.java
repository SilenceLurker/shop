package xyz.silencelurker.project.shop.productionapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.silencelurker.project.shop.productionapi.entity.Production;

/**
 * @author Silence_Lurker
 */
@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer> {
    /**
     * 搜索
     * 
     * @param brand
     * @param pageable
     * @return
     */
    Page<Production> findAllByBrandInOrderBySales(Iterable<Integer> brand, Pageable pageable);

    /**
     * 搜索名字
     * 
     * @param brand
     * @return
     */
    List<Production> findAllByBrandInOrderBySales(Iterable<Integer> brand);
}
