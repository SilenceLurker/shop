package xyz.silencelurker.project.shop.easyshop.entity;

import java.util.Map;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class Cart {
    @Id
    @Column(name = "cart_id")
    @UuidGenerator
    private String id;
    private Integer userId;
    @ElementCollection
    private Map<Long, Short> items;
}
