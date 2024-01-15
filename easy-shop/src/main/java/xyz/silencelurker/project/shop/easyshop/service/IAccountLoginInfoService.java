package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.data.domain.Example;

import jakarta.annotation.Nullable;
import xyz.silencelurker.project.shop.easyshop.entity.BaseAccountLoginInfo;

/**
 * @author Silence_Lurker
 */
public interface IAccountLoginInfoService {
    /**
     * 查询登录状态，若已登录则返回用户id
     * 
     * @param token
     * @return
     */
    Integer checkLogin(String token);

    /**
     * 登出，从Redis中移除登录信息
     * 
     * @param token
     */
    void loginOut(String token);

    /**
     * 登录方法，登录成功返回token
     * 
     * @param data     可以为id、name或email
     * @param password 密码
     * @param token    token，可为空，若空则生成一个UUID作为新的token
     * @return
     */
    String login(String data, String password, @Nullable String token);

    /**
     * find by example
     * 
     * @param example
     * @return
     */
    BaseAccountLoginInfo checkByExample(BaseAccountLoginInfo example);
}
