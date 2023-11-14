package xyz.silencelurker.project.shop.productionservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xyz.silencelurker.project.shop.productionapi.entity.Production;
import xyz.silencelurker.project.shop.productionapi.repository.ProductionRepository;

/**
 * @author Silence_Lurker
 */
public interface IProductionRepository extends ProductionRepository {
    /**
     * 通过id列表查询并以页面形式返回
     * 
     * @param ids
     * @param pageable
     * @return
     */
    Page<Production> findByIdIn(Iterable<Integer> ids, Pageable pageable);
}
