package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public final class Supporter extends BaseAccountLoginInfo {
    private String name;
    private String logo;
    private String info;
    private String email;
    private String unitName;
    private String unitAddress;
}
