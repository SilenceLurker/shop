package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Brand;
import xyz.silencelurker.project.shop.easyshop.repository.BrandRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class IBrandServiceImpl implements IBrandService {
    @Resource
    private BrandRepository brandRepository;

    @Override
    public Brand getBrandByName(String name) {
        var brand = new Brand();
        brand.setName(name);

        return brandRepository.findOne(Example.of(brand, ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher("name", GenericPropertyMatchers.contains()))).get();
    }

    @Override
    public Brand getBrandById(int id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

}
