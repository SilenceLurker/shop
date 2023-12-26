package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public abstract class BaseAccountLoginInfo {
    @Id
    int accountId;
    String password;

}
