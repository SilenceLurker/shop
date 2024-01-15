package xyz.silencelurker.project.shop.easyshop.service;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.decodeToken;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * @author Silence_Lurker
 */

@Service
public class IAccountLoginInfoServiceImpl implements IAccountLoginInfoService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @implNote 若查询到登录信息则返回用户ID，否则返回-1
     */
    @Override
    public Integer checkLogin(String token) {
        
        var id =  decodeToken(token).get("id");

        return Integer.parseInt(id);

    }

    @Override
    public void loginOut(String token) {

    }

    @Override
    public String login(String data, String password, String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

}
