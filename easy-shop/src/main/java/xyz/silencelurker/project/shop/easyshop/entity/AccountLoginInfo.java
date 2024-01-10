package xyz.silencelurker.project.shop.easyshop.entity;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

/**
 * <p>
 * Save in Redis
 * </p>
 * 
 * @author Silence_Lurker
 */
@Data
@RedisHash
public class AccountLoginInfo {
    private int acccountId;
    private String token;
}
