package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import xyz.silencelurker.project.shop.easyshop.entity.Brand;

/**
 * @author Silence_Lurker
 */
public interface IBrandService {

    /**
     * find by name
     * 
     * @param name
     * @return
     */
    Brand getBrandByName(String name);

    /**
     * find by id
     * 
     * @param id
     * @return
     */
    Brand getBrandById(int id);

    /**
     * get All
     * 
     * @return
     */
    List<Brand> getAll();

    /**
     * save a new Brand
     * 
     * @param brand
     */
    void save(Brand brand);
}
