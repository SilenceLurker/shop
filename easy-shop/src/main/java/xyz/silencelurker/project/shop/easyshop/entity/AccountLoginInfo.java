package xyz.silencelurker.project.shop.easyshop.entity;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * <p>
 * Save in Redis
 * </p>
 * 
 * @author Silence_Lurker
 */
@Data
@Entity
@RedisHash
public class AccountLoginInfo {
    @Id
    private int acccountId;
    private String token;
}
