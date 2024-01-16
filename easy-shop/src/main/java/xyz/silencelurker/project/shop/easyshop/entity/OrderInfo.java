package xyz.silencelurker.project.shop.easyshop.entity;

import java.util.Map;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CollectionTable;
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
    @ElementCollection
    @CollectionTable(name = "order_items")
    private Map<Long, Short> items;
    private String sendInfoId;
}
