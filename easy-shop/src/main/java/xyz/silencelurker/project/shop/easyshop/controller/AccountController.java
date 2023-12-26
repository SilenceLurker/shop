package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import lombok.Data;
import xyz.silencelurker.project.shop.easyshop.service.IAccountLoginInfoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Silence_Lurker
 */
@RequestMapping("/account")
@RestController
public class AccountController {

    @Resource
    private IAccountLoginInfoService accountLoginInfoService;

    @Data
    private class LoginUser {
        String id;
        String name;
        String email;
        String password;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginData,
            @CookieValue(name = "token", required = false) String token) {

        throw new UnsupportedOperationException();
    }

}
