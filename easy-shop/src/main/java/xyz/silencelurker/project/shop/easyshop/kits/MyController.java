package xyz.silencelurker.project.shop.easyshop.kits;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * just with always use annoncation
 * 
 * In Testing
 * 
 * @author Silence_Lurker
 */
@ApiResponses
@CrossOrigin
@RequestMapping()
@RestController
public @interface MyController {
    String[] path() default {};
}
