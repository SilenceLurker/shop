package xyz.silencelurker.project.shop.productionapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.silencelurker.project.shop.productionapi.entity.Brand;

/**
 * @author Silence_Lurker
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    /**
     * 查询所有包含该字段的品牌
     * 
     * @param brandName
     * @param pageable
     * @return
     */
    Page<Brand> findAllByBrandNameContaining(String brandName, Pageable pageable);

    /**
     * 查询所有包含该字段的品牌
     * 
     * @param brandName
     * @return
     */
    List<Brand> findAllByBrandNameContaining(String brandName);
}
