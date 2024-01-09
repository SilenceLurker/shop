package xyz.silencelurker.project.shop.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * 不是……谁家数据库brand还TM是保留关键字啊艹！
 */
@Table(name = "production_brand")
/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class Brand {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brand_name")
    private String name;

    @Transient
    @JsonIgnore
    public static final Short BRAND_AVAILABLE_BITE = 0b011111111111;
}
