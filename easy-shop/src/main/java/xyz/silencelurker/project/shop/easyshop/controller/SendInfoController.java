package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
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
@CrossOrigin
@ApiResponses
@RestController
@RequestMapping("/sendInfo")
public class SendInfoController {
    @Resource
    private ISendInfoService sendInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> createSendInfo(@RequestBody SendInfo newInfo) {

        sendInfoService.createNewSendInfo(newInfo);

        return ResponseEntity.ok().body("Success!");
    }

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestBody SendInfo info, @CookieValue String token) {
        var tokenMap = decodeToken(token);

        var targetId = tokenMap.get("id");

        if (targetId.equals(info.getAccountId())) {
            return ResponseEntity.ok().body(sendInfoService.findAllByUserId(Integer.parseInt(targetId)));
        }

        return ResponseEntity.notFound().build();
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
