package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Table(name = "supporter")
@EqualsAndHashCode(callSuper = true)
public class Supporter extends BaseAccountLoginInfo {
    private String name;
    private String logo;
    private String info;
    private String email;
    @OneToOne
    private Brand brand;
    private String unitName;
    private String unitAddress;
}
