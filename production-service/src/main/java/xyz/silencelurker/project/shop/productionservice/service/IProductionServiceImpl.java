package xyz.silencelurker.project.shop.productionservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.productionapi.entity.Production;
import xyz.silencelurker.project.shop.productionapi.service.IBrandService;
import xyz.silencelurker.project.shop.productionapi.service.IProductionService;
import xyz.silencelurker.project.shop.productionservice.repository.IProductionRepository;

/**
 * @author Silence_Lurker
 */
@DubboService(version = "0.0.1-SNAPSHOT")
public class IProductionServiceImpl implements IProductionService {

    @Resource
    private IProductionRepository productionRepository;
    @Resource
    private IBrandService brandService;

    @Override
    public Production selectProductionById(Integer id) {
        return productionRepository.findById(id).get();
    }

    @Override
    public List<Production> selectProductionByIds(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        var productions = productionRepository.findAllById(idList);

        return productions;
    }

    @Override
    public List<Production> selectProductionByIds(List<Integer> ids) {
        return productionRepository.findAllById(ids);
    }

    @Override
    public Page<Production> getProductionByIdsWithCount(List<Integer> ids, int pageCount, int nowPage) {
        var page = Pageable.ofSize(pageCount);

        for (int i = 0; i < nowPage; i++) {
            if (page.getPageNumber() >= nowPage) {
                break;
            }
            page.next();
        }

        // productionRepository.f

        return productionRepository.findByIdIn(ids, page);

    }

    @Override
    public Void saveProduction(Production newProduction) {
        productionRepository.save(newProduction);

        return null;
    }

    @Override
    public Production deleteProduction(Production targetProduction) {
        var pro = productionRepository.findById(targetProduction.getId()).get();
        productionRepository.delete(targetProduction);
        return pro;
    }

    @Override
    public Production deleteProductionById(Integer id) {
        var pro = productionRepository.findById(id).get();

        productionRepository.deleteById(id);

        return pro;

    }

    @Override
    public List<Production> getProduceList(String brand) {

        var list = brandService.getAllBrandByName(brand);

        List<Integer> idList = new ArrayList<>();

        for (var item : list) {
            idList.add(item.getBrandId());
        }

        return productionRepository.findAllByBrandInOrderBySales(idList);
    }

    @Override
    public Page<Production> getProduceList(String brand, int nubmer) {

        var pageable = Pageable.ofSize(nubmer);

        var list = brandService.getAllBrandByName(brand);

        List<Integer> idList = new ArrayList<>();

        for (var item : list) {
            idList.add(item.getBrandId());
        }

        return productionRepository.findAllByBrandInOrderBySales(idList, pageable);
    }

    @Override
    public List<Production> searchAllFormateProduction(String name, String brand, String ram, String system,
            String type, String order, int lowPrice, int highPrice) {
        // TODO Auto-generated method stub
        // TODO: Are You Kidding Me?
        throw new UnsupportedOperationException("Unimplemented method 'searchAllFormateProduction'");
    }

}