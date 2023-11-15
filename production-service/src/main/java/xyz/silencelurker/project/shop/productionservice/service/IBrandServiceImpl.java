package xyz.silencelurker.project.shop.productionservice.service;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.productionapi.entity.Brand;
import xyz.silencelurker.project.shop.productionapi.repository.BrandRepository;
import xyz.silencelurker.project.shop.productionapi.service.IBrandService;

/**
 * @author Silence_Lurker
 */
@DubboService()
public class IBrandServiceImpl implements IBrandService {

    @Resource
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getAllBrandByName(String name) {
        return brandRepository.findAllByBrandNameContaining(name);
    }

    @Override
    public Page<Brand> getAllBrandByName(String name, Pageable pageable) {
        return brandRepository.findAllByBrandNameContaining(name, pageable);
    }

}
