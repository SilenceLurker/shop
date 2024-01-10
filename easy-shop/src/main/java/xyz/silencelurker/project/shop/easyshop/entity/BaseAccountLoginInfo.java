package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public abstract class BaseAccountLoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int accountId;
    String password;

}
