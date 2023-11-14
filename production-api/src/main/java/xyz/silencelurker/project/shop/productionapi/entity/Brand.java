package xyz.silencelurker.project.shop.productionapi.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Builder
public class Brand implements Serializable {
    @Id
    @GeneratedValue
    private Integer brandId;
    private String brandName;
    private String logo;
    private String transversalImage;
    private String verticalImage;
}
