package xyz.silencelurker.project.shop.easyshop.entity;

import java.util.Map;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class OrderInfo {
    @Id
    @UuidGenerator
    private String id;
    private Integer accountId;
    private String sendInfoId;
    private SavedItems items;
}
