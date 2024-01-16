package xyz.silencelurker.project.shop.easyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.repository.ProductionRepository;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterRepository;

/**
 * @author Silence_Lurker
 */
@Log4j2
@Service
public class IProductionServiceImpl implements IProductionService {

    @Resource
    private ProductionRepository productionRepository;
    @Resource
    private SupporterRepository supporterRepository;

    @Override
    public void createProduction(Production newProduction) {

        var production = new Production();

        production.setBrand(newProduction.getBrand());
        production.setColor(newProduction.getColor());
        production.setEnable(newProduction.getEnable());
        production.setId(newProduction.getId());
        production.setMemoryAndDisk(newProduction.getMemoryAndDisk());
        production.setName(newProduction.getName());
        production.setPrice(newProduction.getPrice());
        production.setSubId(newProduction.getSubId());
        production.setSystem(newProduction.getSystem());
        production.setType(newProduction.getType());

        productionRepository.save(production);
    }

    @Override
    public List<Production> findProductionBySubId(int subId, int accountId) {
        Production production = new Production();

        var account = supporterRepository.findById(accountId).get();
        var brand = account.getBrand();

        production.setBrand(brand);

        var example = Example.of(production, ExampleMatcher.matching().withIgnoreNullValues());

        var productions = productionRepository.findAll(example);

        var lists = new ArrayList<Production>();

        for (Production p : productions) {
            if ((p.getSubId() & subId) == subId) {
                lists.add(p);
            }
        }
        return lists;
    }

    @Override
    public boolean changeProductonStatus(long id) {
        var production = productionRepository.findById(id).get();

        production.setEnable(!production.getEnable());

        productionRepository.save(production);

        return production.getEnable();

    }

    @Override
    public List<Production> getAllProduction() {
        return productionRepository.findAll();
    }

    @Override
    public Page<Production> getAllProduction(Pageable pageable) {
        log.info(pageable);

        return productionRepository.findAll(pageable);
    }

    @Override
    public List<Production> getAllByExample(Example<Production> example) {
        return productionRepository.findAll(example);
    }

    @Override
    public Page<Production> getAllByExample(Example<Production> example, Pageable pageable) {
        return productionRepository.findAll(example, pageable);
    }

    @Override
    public void deleteById(String id) {
        productionRepository.deleteById(Long.parseLong(id));
    }

}
