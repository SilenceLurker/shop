package xyz.silencelurker.project.shop.cookietest.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Silence_Lurker
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/setToken")
    public String cookieTest(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "Save Completed!";
    }
}
