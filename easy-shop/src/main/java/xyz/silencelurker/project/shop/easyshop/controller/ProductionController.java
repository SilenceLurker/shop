package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.service.IProductionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Silence_Lurker
 */
@ApiResponses
@RequestMapping("/production")
@RestController
public class ProductionController {
    @Resource
    private IProductionService productionService;

    @GetMapping("/details")
    public ResponseEntity<?> details(@RequestParam int subId, @CookieValue String token) {

        return ResponseEntity.notFound().build();
    }

}
