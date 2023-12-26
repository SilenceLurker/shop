package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Silence_Lurker
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public final class User extends BaseAccountLoginInfo {
    private String name;
    private String nickName;
    private String info;
    private boolean sex;
    private String email;

}
