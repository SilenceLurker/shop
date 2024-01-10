package xyz.silencelurker.project.shop.easyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.repository.ProductionRepository;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class IProductionServiceImpl implements IProductionService {

    @Resource
    private ProductionRepository productionRepository;
    @Resource
    private SupporterRepository supporterRepository;

    @Override
    public void createProduction(Production newProduction) {
        productionRepository.save(newProduction);
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

}
