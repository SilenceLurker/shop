package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Supporter extends BaseAccountLoginInfo {
    private String name;
    private String logo;
    private String info;
    @OneToOne
    private Brand brand;
    private String unitName;
    private String unitAddress;
}
