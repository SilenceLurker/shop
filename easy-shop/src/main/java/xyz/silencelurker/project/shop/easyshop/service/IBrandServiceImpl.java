package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Brand;
import xyz.silencelurker.project.shop.easyshop.repository.BrandRepository;

/**
 * @author Silence_Lurker
 */
@Log4j2
@Service
public class IBrandServiceImpl implements IBrandService {
    @Resource
    private BrandRepository brandRepository;

    @Override
    public Brand getBrandByName(String name) {
        var brand = new Brand();
        brand.setName(name);

        log.info(brand);

        return brandRepository.findAll(Example.of(brand, ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher("name", GenericPropertyMatchers.contains()))).get(0);
    }

    @Override
    public Brand getBrandById(int id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public void save(Brand brand) {
        if( brand == null){
            return;
        }
        brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

}
