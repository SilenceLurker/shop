package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.Data;
import xyz.silencelurker.project.shop.easyshop.entity.Brand;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.entity.Supporter;
import xyz.silencelurker.project.shop.easyshop.entity.SupporterInfo;
import xyz.silencelurker.project.shop.easyshop.service.IBrandService;
import xyz.silencelurker.project.shop.easyshop.service.IColorService;
import xyz.silencelurker.project.shop.easyshop.service.IMemoryAndDiskService;
import xyz.silencelurker.project.shop.easyshop.service.IProductionService;
import xyz.silencelurker.project.shop.easyshop.service.IRecommendationService;
import xyz.silencelurker.project.shop.easyshop.service.ISupporterInfoService;
import xyz.silencelurker.project.shop.easyshop.service.ISupporterService;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Silence_Lurker
 */

@ApiResponses
@CrossOrigin
@RestController
@RequestMapping("/supporter")
public class SupporterController {
    @Resource
    private ISupporterService supporterService;
    @Resource
    private ISupporterInfoService supporterInfoService;
    @Resource
    private IRecommendationService recommendationService;
    @Resource
    private IProductionService productionService;

    @PostMapping("/enable")
    public ResponseEntity<?> enable(@RequestBody Supporter supporter) {
        var info = new SupporterInfo();
        info.setAccountId(supporter.getAccountId());
        info.setSupporter(true);

        supporterInfoService.registerSupporterInfo(info);

        var brand = new Brand();
        brand.setName(supporter.getUnitName());
        supporter.setBrand(brand);

        supporterService.enableSupporterAccount(supporter);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/supporterCheck")
    public ResponseEntity<?> supporterCheck(@CookieValue String token) {
        var supporter = supporterService.supporterLoginIn(token);

        if (supporter == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Supporter supporter) {

        var oldSupporterInfo = supporterService.updateSupporterInfo(supporter);

        return ResponseEntity.ok().body(oldSupporterInfo);
    }

    @Data
    private class Recommendations {
        private int id;
        private List<Long> productions;
        private String logo;
    }

    @PostMapping("/setRecommendations")
    public ResponseEntity<?> postMethodName(@RequestBody Recommendations recommendation, @CookieValue String token) {

        recommendationService.setRecommendations(recommendation.id, recommendation.getProductions(),
                recommendation.logo);

        return ResponseEntity.ok().build();
    }

    public class TargetProduction {
        int id;
        String name;
        int brand;
        int color;
        int system;
        int type;
        boolean enable;
        int memoryAndDisk;
        double price;
    }

    @Resource
    private IBrandService brandService;
    @Resource
    private IMemoryAndDiskService memoryAndDiskService;
    @Resource
    private IColorService colorService;

    @PostMapping("/createProduction")
    public ResponseEntity<?> createProduction(@RequestBody TargetProduction production, @CookieValue String token) {
        var supporter = supporterService.supporterLoginIn(token);

        Brand brand = supporter.getBrand();

        var newPro = new Production();

        newPro.setBrand(brand);

        var subId = productionService
                .getAllByExample(Example.of(newPro, ExampleMatcher.matching().withIgnoreNullValues())).size();

        productionService.createProduction(null);

        return ResponseEntity.notFound().build();
    }

}
