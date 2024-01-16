package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.SendInfo;
import xyz.silencelurker.project.shop.easyshop.service.ISendInfoService;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

/**
 * @author Silence_Lurker
 */

@Async
@Log4j2
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@ApiResponses
@RestController
@RequestMapping("/sendInfo")
public class SendInfoController {
    @Resource
    private ISendInfoService sendInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> createSendInfo(@RequestBody SendInfo newInfo, @CookieValue String token) {

        newInfo.setAccountId(decodeToken(token).get("id"));

        log.info(newInfo);

        var infos = sendInfoService.findAllByUserId(Integer.parseInt(newInfo.getAccountId()));

        var defaultSelect = 0;

        var it = infos.iterator();

        while (it.hasNext()) {
            var item = it.next();

            if (item.isDefaultSelected()) {
                defaultSelect++;
                break;
            }
        }

        if (defaultSelect > 0) {
            newInfo.setDefaultSelected(true);
        }

        sendInfoService.createNewSendInfo(newInfo);

        return ResponseEntity.ok().body("Success!");
    }

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@CookieValue String token) {
        var tokenMap = decodeToken(token);

        log.info(tokenMap);

        return ResponseEntity.ok().body(sendInfoService.findAllByUserId(Integer.parseInt(tokenMap.get("id"))));
    }

    @DeleteMapping("/delete")
    @PostMapping("/delete")
    public ResponseEntity<?> postMethodName(@RequestBody SendInfo info) {

        SendInfo returnInfo = null;
        try {
            returnInfo = sendInfoService.deleteSendInfoById(info.getId());
        } catch (Exception e) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(returnInfo);

    }

}
