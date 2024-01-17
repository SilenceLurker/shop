package xyz.silencelurker.project.shop.easyshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import xyz.silencelurker.project.shop.easyshop.entity.Supporter;
import xyz.silencelurker.project.shop.easyshop.entity.User;
import xyz.silencelurker.project.shop.easyshop.service.IAccountLoginInfoService;
import xyz.silencelurker.project.shop.easyshop.service.IUserService;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@ApiResponses
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private IAccountLoginInfoService accountLoginInfoService;

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate template;

    @Data
    public static class LoginUser {
        @Nullable
        String id;
        String name;
        String email;
        String password;

        public LoginUser() {
            super();
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(@CookieValue String token) {
        String data;
        if (accountLoginInfoService.checkLogin(token) == -1) {
            data = "Account info not exist!";
            return ResponseEntity.ok().body(data);
        } else {
            data = "Account already Login in";
            return ResponseEntity.badRequest().body(data);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginUser user) {
        Map<String, String> data = new HashMap<>(2);

        data.put("id", userService.register(user.getName(), user.getPassword(), user.getEmail()));
        data.put("data", "Already Sent a confirm email to your email address.");

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/checkRegister")
    public ResponseEntity<?> checkRegister(String email) {
        User user = new User();
        user.setEmail(email);
        var target = accountLoginInfoService.checkByExample(user);

        if (target != null) {
            return ResponseEntity.ok().build();
        }

        Supporter supporter = new Supporter();
        supporter.setEmail(email);
        target = accountLoginInfoService.checkByExample(supporter);

        if (target != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam(required = true, defaultValue = "") String id,
            @RequestParam String passwd) {

                if(id == null){
                    return null;
                }

        var checkCode = template.opsForValue().get(id);

        if (passwd.equalsIgnoreCase(checkCode)) {
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser userLoginInfo, HttpServletResponse response,
            HttpServletRequest req) throws UnsupportedEncodingException {
        var domain = req.getHeader("Origin");
        log.info(domain);
        var id = userLoginInfo.getId();
        var name = userLoginInfo.getName();
        var email = userLoginInfo.getEmail();
        var passwd = userLoginInfo.getPassword();
        log.info(id + name + email + passwd);
        var userData = new HashMap<String, String>(5);
        userData.put("id", id);
        userData.put("name", name);
        userData.put("email", email);
        userData.put("time", System.currentTimeMillis() + "");
        var token = buildToken(userData);
        if (!userLoginInfo.getId().isEmpty() || userLoginInfo.id == null) {
            try {
                log.info("Login by id");
                var user = userService.loginById(id, passwd);
                userData.put("name", user.getName());
                userData.put("email", user.getEmail());
                token = buildToken(userData);
            } catch (Exception e) {
                log.info("user not found!");
                return ResponseEntity.notFound().build();
            }
        } else if (!userLoginInfo.getName().isEmpty()) {
            try {
                log.info("Login by name");
                var user = userService.loginByName(name, passwd);
                userData.put("id", user.getAccountId() + "");
                userData.put("email", user.getEmail());
                token = buildToken(userData);
            } catch (Exception e) {
                log.info("user not found!");
                return ResponseEntity.notFound().build();
            }
        } else if (!userLoginInfo.getEmail().isEmpty()) {
            try {
                log.info("Login by email");
                var user = userService.loginByEmail(email, passwd);
                userData.put("name", user.getName());
                userData.put("id", user.getAccountId() + "");
                token = buildToken(userData);
            } catch (Exception e) {
                log.info("user not found!");
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
        var cokie = new Cookie("token", URLEncoder.encode(token, "utf-8"));
        cokie.setMaxAge(60 * 24 * 60);
        cokie.setPath("/");
        cokie.setSecure(true);
        cokie.setAttribute("SameSite", "None");
        response.addCookie(cokie);
        return ResponseEntity.ok().body(token);
    }

}
