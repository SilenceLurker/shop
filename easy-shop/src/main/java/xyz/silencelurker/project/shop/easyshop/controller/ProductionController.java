package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Brand;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.entity.SystemType;
import xyz.silencelurker.project.shop.easyshop.entity.Type;
import xyz.silencelurker.project.shop.easyshop.service.IBrandService;
import xyz.silencelurker.project.shop.easyshop.service.IProductionService;
import xyz.silencelurker.project.shop.easyshop.service.ISystemTypeService;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Silence_Lurker
 */
@Log4j2
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@ApiResponses
@RequestMapping("/production")
@RestController
public class ProductionController {
    @Resource
    private IProductionService productionService;

    @GetMapping("/details")
    public ResponseEntity<?> details(@RequestParam int subId, @CookieValue String token) {
        var map = decodeToken(token);

        return ResponseEntity.ok(productionService.findProductionBySubId(subId, Integer.parseInt(map.get("id"))));
    }

    @GetMapping("/getAllProduction")
    public ResponseEntity<?> getAllProduction(@RequestParam(required = false, defaultValue = "-1") Integer page,
            @RequestParam(required = false, defaultValue = "-1") Integer size) {
        if (size < 0) {
            return ResponseEntity.ok().body(productionService.getAllProduction());
        }
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        return ResponseEntity.ok().body(productionService.getAllProduction(pageable));
    }

    @Resource
    private IBrandService brandService;
    @Resource
    private ISystemTypeService systemTypeService;

    @PostMapping("/selectProductions")
    public ResponseEntity<?> postMethodName(@RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String brand,
            @RequestParam(required = false, defaultValue = "") Short type,
            @RequestParam(required = false, defaultValue = "") String system) {

                log.info(name + " " + brand + " " + type + " " + system + "-----------------------------------------------");

        var macher = ExampleMatcher.matching();

        Brand brandEntity = null;
        Type typeEntity = null;

        if(!brand.isEmpty()){
            brandEntity = brandService.getBrandByName(brand);
            log.info(brandEntity);
            macher.withMatcher("brand", GenericPropertyMatchers.caseSensitive());
        }
        if(type != null){
            typeEntity = Type.getTypeByCode(type);
            log.info(typeEntity);
            macher.withMatcher("type", GenericPropertyMatchers.regex());
        }
        
        var temp = new SystemType();
        temp.setType(system);
        SystemType systemEntity = null;

                if(!system.isEmpty()){
                    systemEntity =  systemTypeService.getSystemType(Example.of(temp, ExampleMatcher.matching().withIgnoreNullValues().withMatcher("type", GenericPropertyMatchers.contains()).withIgnorePaths("id")));
                    log.info(systemEntity);
                    macher.withMatcher("system", GenericPropertyMatchers.caseSensitive());
                }

        var production = new Production();

        if(!name.isEmpty()){
            production.setName(name);
            macher.withMatcher("name", GenericPropertyMatchers.contains());
        }

        production.setBrand(brandEntity == null ? null : brandEntity);
        production.setType(typeEntity == null ? null : typeEntity);
        production.setSystem(systemEntity == null ? null : systemEntity);

        macher = macher.withIgnorePaths("enable","subId","price","time","id");

        Example<Production> example = Example.of(production, macher.withIgnoreNullValues());

        log.info(example);

        var result = productionService.getAllByExample(example);
        log.info(result);

        if(name.isEmpty() & system.isEmpty() & type == null & brand.isEmpty()){
            log.info("ALL");
            result = productionService.getAllProduction();
        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getProduction")
    public ResponseEntity<?> getProduction(@RequestParam String brand, @RequestParam int subId) {
        var brandEntity = brandService.getBrandByName(brand);

        log.info(brandEntity);

        var production = new Production();
        production.setSubId((short) subId);
        production.setBrand(brandEntity);

        log.info(production);

        Example<Production> example = Example.of(production, ExampleMatcher.matching().withIgnorePaths("id","time","enable","price").withIgnoreNullValues());

        log.info(example);

        return ResponseEntity.ok().body(productionService.getAllByExample(example));
    }

}
