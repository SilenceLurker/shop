package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.service.IAccountLoginInfoService;
import xyz.silencelurker.project.shop.easyshop.service.IUserService;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Silence_Lurker
 */
@Log4j2
@CrossOrigin
@ApiResponses
@RequestMapping("/account")
@RestController
public class AccountController {

    @Resource
    private IAccountLoginInfoService accountLoginInfoService;

    @Resource
    private IUserService userService;

    @Resource
    private StringRedisTemplate template;

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

    @GetMapping("/check")
    public ResponseEntity<?> check(@CookieValue String token) {
        String data;
        if (accountLoginInfoService.checkLogin(token) == -1) {
            data = "Account info not exist!";
            return ResponseEntity.ok().body(data);
        } else {
            data = "Account already Login in";
            return ResponseEntity.status(HttpStatusCode.valueOf(502)).body(data);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginUser user) {
        Map<String, String> data = new HashMap<>(2);

        data.put("id", userService.register(user.getName(), user.getPassword(), user.getEmail()));
        data.put("data", "Already Sent a confirm email to your email address.");

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam String id, @RequestParam String passwd) {

        var checkCode = template.opsForValue().get(id);

        if (passwd.equalsIgnoreCase(checkCode)) {
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser userLoginInfo, HttpServletResponse response) {

        var id = userLoginInfo.getId();
        var name = userLoginInfo.getName();
        var email = userLoginInfo.getEmail();
        var passwd = userLoginInfo.getPassword();

        var userData = new HashMap<String, String>(5);

        userData.put("id", id);
        userData.put("name", name);
        userData.put("email", email);
        userData.put("time", System.currentTimeMillis() + "");

        var token = buildToken(userData);
        response.addCookie(new Cookie("SameSite", "None"));

        if (!userLoginInfo.getId().isEmpty()) {
            try {
                userService.loginById(id, passwd);

                response.addCookie(new Cookie("token", token));

                return ResponseEntity.ok().body(token);
            } catch (Exception e) {
                log.info("user not found!");
                return ResponseEntity.notFound().build();
            }
        } else if (!userLoginInfo.getName().isEmpty()) {
            try {
                userService.loginByName(name, passwd);

                response.addCookie(new Cookie("token", token));

                return ResponseEntity.ok().body(token);
            } catch (Exception e) {
                log.info("user not found!");
                return ResponseEntity.notFound().build();
            }
        } else if (!userLoginInfo.getEmail().isEmpty()) {
            try {
                userService.loginByEmail(email, passwd);

                response.addCookie(new Cookie("token", token));

                return ResponseEntity.ok().body(token);
            } catch (Exception e) {
                log.info("user not found!");
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
