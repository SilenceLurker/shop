package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Brand;
import xyz.silencelurker.project.shop.easyshop.entity.Color;
import xyz.silencelurker.project.shop.easyshop.entity.Production;
import xyz.silencelurker.project.shop.easyshop.entity.Supporter;
import xyz.silencelurker.project.shop.easyshop.entity.SystemType;
import xyz.silencelurker.project.shop.easyshop.entity.Type;
import xyz.silencelurker.project.shop.easyshop.service.IBrandService;
import xyz.silencelurker.project.shop.easyshop.service.IColorService;
import xyz.silencelurker.project.shop.easyshop.service.IMemoryAndDiskService;
import xyz.silencelurker.project.shop.easyshop.service.IProductionService;
import xyz.silencelurker.project.shop.easyshop.service.IRecommendationService;
import xyz.silencelurker.project.shop.easyshop.service.ISupporterInfoService;
import xyz.silencelurker.project.shop.easyshop.service.ISupporterService;
import xyz.silencelurker.project.shop.easyshop.service.ISystemTypeService;
import xyz.silencelurker.project.shop.easyshop.utils.ProductionUtil;

import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static xyz.silencelurker.project.shop.easyshop.utils.MemoryAndDiskUtil.*;
import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.buildToken;
import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.decodeToken;

/**
 * @author Silence_Lurker
 */
@Log4j2
@ApiResponses
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
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

    @Data
    public static class TempSupporter {
        Integer accountId;
        String password;
        String name;
        String logo;
        String info;
        String email;
        String unitName;
        String unitAddress;

        public TempSupporter() {
            super();
        }
    }

    public static final String SUPPORTER_FILE_LOCATION = "./supporterLogo";

    @PostMapping("/enable")
    public ResponseEntity<?> enable(@RequestBody TempSupporter tempSupporter,
            @CookieValue(required = false) String token,
            HttpServletResponse resp
    // , MultipartFile logoFile
    ) throws IOException {

        var dir = new File(SUPPORTER_FILE_LOCATION);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        var supporter = new Supporter();
        var accountId = Integer.parseInt(decodeToken(token).get("id"));
        var brand = new Brand();

        brand.setId(accountId);
        brand.setName(tempSupporter.getName());

        brandService.save(brand);

        supporter.setAccountId(accountId);
        supporter.setPassword(tempSupporter.getPassword());
        supporter.setName(tempSupporter.getPassword());
        supporter.setInfo(tempSupporter.getInfo());
        supporter.setBrand(brand);
        supporter.setEmail(tempSupporter.getEmail());
        supporter.setUnitAddress(tempSupporter.getUnitAddress());
        supporter.setUnitName(tempSupporter.getUnitName());

        log.info(tempSupporter);

        if (tempSupporter.getLogo().contains("http")) {
            supporter.setLogo(tempSupporter.getLogo());

            var tokenInfo = decodeToken(token);
            tokenInfo.put("enableSupporter", "true");
            token = buildToken(tokenInfo);

            var cookieToken = new Cookie("token", token);

            cookieToken.setMaxAge(60 * 24 * 60);
            cookieToken.setPath("/");
            cookieToken.setSecure(true);
            cookieToken.setAttribute("SameSite", "None");
            cookieToken.setHttpOnly(true);

            resp.addCookie(cookieToken);

            supporterService.enableSupporterAccount(supporter);

            return ResponseEntity.ok().build();
        }

        // var file = new File(SUPPORTER_FILE_LOCATION,accountId +
        // logoFile.getOriginalFilename());
        // tempSupporter.setLogo(accountId + logoFile.getOriginalFilename());

        // if(!file.exists()){
        // file.createNewFile();
        // }

        // FileOutputStream fos = new FileOutputStream(file);

        // fos.write(logoFile.getBytes());

        // fos.close();;

        // supporter.setLogo(accountId + logoFile.getOriginalFilename());

        var tokenInfo = decodeToken(token);
        tokenInfo.put("enableSupporter", "true");
        token = buildToken(tokenInfo);

        supporterService.enableSupporterAccount(supporter);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/logoFile")
    public ResponseEntity<?> getLogoFile(@CookieValue String token, String logo,
            @RequestParam(required = false) Integer accountId) {

        var file = new FileSystemResource(SUPPORTER_FILE_LOCATION + accountId + logo);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
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
    public static class Recommendations {
        private int id;
        private List<Long> productions;
        private String logo;

        public Recommendations() {
            super();
        }
    }

    @PostMapping("/setRecommendations")
    public ResponseEntity<?> postMethodName(@RequestBody Recommendations recommendation, @CookieValue String token) {

        recommendationService.setRecommendations(recommendation.id, recommendation.getProductions(),
                recommendation.logo);

        return ResponseEntity.ok().build();
    }

    @Data
    public static class TargetProduction {
        int subId;
        String name;
        int brand;
        int color;
        int system;
        int type;
        boolean enable;
        int memoryAndDisk;
        double price;

        public TargetProduction() {
            super();
        }
    }

    @Resource
    private IBrandService brandService;
    @Resource
    private IMemoryAndDiskService memoryAndDiskService;
    @Resource
    private IColorService colorService;

    @GetMapping("/initSubId")
    public ResponseEntity<?> getSubId(@CookieValue String token) {



        var supporter = supporterService.supporterLoginIn(token);
        log.info(supporter);
        Brand brand = supporter.getBrand();
        log.info(brand);
        var newPro = new Production();
        log.info(newPro);
        newPro.setBrand(brand);
        var subId = productionService
                .getAllByExample(Example.of(newPro, ExampleMatcher.matching().withIgnoreNullValues())).size();

        newPro.setSubId((short) subId);
        productionService.createProduction(newPro);

        return ResponseEntity.ok().body(subId);
    }

    @PostMapping("/addNewColor")
    public ResponseEntity<?> addNewColorWithProduction(@RequestParam int subId, @RequestBody Color color, @CookieValue String token) {

        var supporter = supporterService.supporterLoginIn(token);

        color.setId(((color.getId() << 12) | (subId | (supporter.getBrand().getId() << 17))));

        var colorInfo = colorService.createNewColor(color.getId(), color.getName(), color.getImage());

        return ResponseEntity.ok().body(colorInfo);
    }

    @Resource
    private ISystemTypeService systemTypeService;

    @PostMapping("/addSystemType")
    public ResponseEntity<?> addSystemType(@RequestBody SystemType newSystemType) {

        try {
            systemTypeService.addNewSystemType(newSystemType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body("sussess!");
    }

    @PostMapping("/createProduction")
    public ResponseEntity<?> createProduction(@RequestBody TargetProduction production, @CookieValue String token) {
        var supporter = supporterService.supporterLoginIn(token);

        Brand brand = supporter.getBrand();
        var newPro = new Production();
        newPro.setBrand(brand);
        var subId = productionService
                .getAllByExample(Example.of(newPro, ExampleMatcher.matching().withIgnoreNullValues())).size();

        newPro.setSubId((short) subId);
        var color = new Color();
        color.setId(production.color);
        newPro.setColor(
                colorService.findColorByExample(Example.of(color, ExampleMatcher.matching().withIgnoreNullValues()))
                        .get(0));
        newPro.setEnable(production.enable);
        var mem = buildMemoryAndDisk(production.memoryAndDisk);

        memoryAndDiskService.addMemoryAndDisk(mem);

        memoryAndDiskService.addMemoryAndDisk(mem);
        newPro.setMemoryAndDisk(mem);
        newPro.setName(production.name);
        var type = Type.getTypeByCode((short) production.type);


        log.info(type);
        newPro.setType(type);
        var systemType = new SystemType();
        systemType.setId((short) production.system);
        var systemTypeExample = Example.of(systemType, ExampleMatcher.matching().withIgnoreNullValues());
        newPro.setSystem(systemTypeService.getSystemType(systemTypeExample));
        newPro.setPrice(production.price);

        newPro.setId(ProductionUtil.productionIdBuild(newPro));

        productionService.createProduction(newPro);

        return ResponseEntity.ok().body(newPro);
    }

}
