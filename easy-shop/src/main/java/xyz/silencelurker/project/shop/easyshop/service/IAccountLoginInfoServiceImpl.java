package xyz.silencelurker.project.shop.easyshop.service;

import static xyz.silencelurker.project.shop.easyshop.utils.TokenUtil.decodeToken;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.BaseAccountLoginInfo;
import xyz.silencelurker.project.shop.easyshop.entity.Supporter;
import xyz.silencelurker.project.shop.easyshop.entity.User;
import xyz.silencelurker.project.shop.easyshop.repository.SupporterRepository;
import xyz.silencelurker.project.shop.easyshop.repository.UserRepository;

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
        var id = decodeToken(token).get("id");

        return Integer.parseInt(id);

    }

    @Deprecated
    @Override
    public void loginOut(String token) {

    }

    @Deprecated
    @Override
    public String login(String data, String password, String token) {
        return token;
    }

    @Resource
    private UserRepository userRepository;
    @Resource
    private SupporterRepository supporterRepository;

    @Override
    public BaseAccountLoginInfo checkByExample(BaseAccountLoginInfo exampleInfo) {
        User user = new User();
        user.setEmail(exampleInfo.getEmail());
        var uExample = Example.of(user, ExampleMatcher.matching().withIgnoreNullValues());
        var uTarget = userRepository.findAll(uExample).get(0);
        if (uTarget != null) {
            return uTarget;
        }

        Supporter supporter = new Supporter();
        supporter.setEmail(exampleInfo.getEmail());
        var sExample = Example.of(supporter);
        var sTarget = supporterRepository.findAll(sExample).get(0);
        if (sTarget != null) {
            return sTarget;
        }
        return null;
    }

}
