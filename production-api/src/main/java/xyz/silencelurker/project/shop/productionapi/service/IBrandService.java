package xyz.silencelurker.project.shop.productionapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xyz.silencelurker.project.shop.productionapi.entity.Brand;

/**
 * @author Silence_Lurker
 */
public interface IBrandService {
    /**
     * 
     * 通过传入的name查询全部包含的名字
     * 
     * @param name
     * @param pageable
     * @return
     */
    Page<Brand> getAllBrandByName(String name, Pageable pageable);

    /**
     * 通过Brand传入的name查询全部相似
     * 
     * @param name
     * @return
     */
    List<Brand> getAllBrandByName(String name);

    /**
     * get All
     * 
     * @return
     */
    List<Brand> getAllBrand();
}
