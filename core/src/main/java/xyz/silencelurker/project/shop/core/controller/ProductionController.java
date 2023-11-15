package xyz.silencelurker.project.shop.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.transaction.NotSupportedException;
import xyz.silencelurker.project.shop.productionapi.service.IBrandService;
import xyz.silencelurker.project.shop.productionapi.service.IProductionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Silecne_Lurker
 */
@ApiResponses
@CrossOrigin
@RestController
@RequestMapping("/produce")
public class ProductionController {
    @Resource
    private IProductionService productionService;

    @Resource
    private IBrandService brandService;

    @ApiResponse
    @GetMapping(value = "/getBrandList")
    public ResponseEntity<?> getBrandList(@RequestParam String brand) throws NotSupportedException {
        var info = brandService.getAllBrandByName(brand);

        return ResponseEntity.ok(info);
    }

    @GetMapping("/getProduceList")
    public ResponseEntity<?> getProduceList(@RequestParam String brand,
            @RequestParam(required = false) Integer number) {
        if (number == null) {
            return ResponseEntity.ok(productionService.getProduceList(brand));
        }
        return ResponseEntity.ok(productionService.getProduceList(brand, number));
    }

}
