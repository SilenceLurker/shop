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
public class Color implements Serializable {
    @Id
    @GeneratedValue
    private Integer colorId;
    private String colorName;
    private String image;
}
