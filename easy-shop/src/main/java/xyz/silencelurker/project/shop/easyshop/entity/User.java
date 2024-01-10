package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Silence_Lurker
 */
@Data
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends BaseAccountLoginInfo {
    private String name;
    private String nickName;
    private String info;
    private boolean sex;
    private String email;

}
