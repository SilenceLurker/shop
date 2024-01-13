package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.service.IBrandService;

/**
 * @author Silence_Lurker
 */
@ApiResponses
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/brand")
@RestController
public class BrandController {
    @Resource
    public IBrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAllBrands() {
        return ResponseEntity.ok().body(brandService.getAll());

    }

}
