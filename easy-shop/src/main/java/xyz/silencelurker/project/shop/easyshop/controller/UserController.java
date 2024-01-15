package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import lombok.Data;
import xyz.silencelurker.project.shop.easyshop.service.IUserService;
import xyz.silencelurker.project.shop.easyshop.service.MailSenderService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

/**
 * @author Silence_Lurker
 */

@ApiResponses
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private MailSenderService mailSenderService;
    @Resource
    private StringRedisTemplate template;

    @Data
    public static  class UserInfo {
        private String nickname;
        private String info;
        private boolean sex;

        public UserInfo(){
            super();
        }
    }

    @PostMapping("/info")
    public ResponseEntity<?> setInfo(@RequestBody UserInfo userInfo, @CookieValue String token) {

        var info = decodeToken(token);

        var user = userService.getUserInfo(Integer.parseInt(info.get("id")));

        user.setNickName(userInfo.getNickname());
        user.setInfo(userInfo.getInfo());
        user.setSex(userInfo.sex);

        userService.updateInfo(user);

        return ResponseEntity.badRequest().body(user);
    }

    @PostMapping("/emailUpdate")
    public ResponseEntity<?> emailUpdate(@RequestParam int accountId) {

        return ResponseEntity.ok().build();
    }

    @Data
    private class EmailUpdateInfo {
        private int accountId;
        private String code;
        private String newEmail;
    }

    @GetMapping("/emailUpdate/confirm")
    public ResponseEntity<?> confirmEmailUpdate(@RequestBody EmailUpdateInfo info) {
        boolean status = false;
        try {
            status = userService.emailUpdateConfirm(info.newEmail, info.code, info.accountId);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Check Code not match! Please Check Out!");
        }

        if (status) {
            return ResponseEntity.ok().body(userService.getUserInfo(info.accountId));
        }

        return ResponseEntity.badRequest().body("Check Code not match! Please Check Out!");

    }

}
