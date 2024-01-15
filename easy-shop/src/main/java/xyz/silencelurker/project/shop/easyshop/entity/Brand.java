package xyz.silencelurker.project.shop.easyshop.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Brand implements Serializable{
    @Id
    @Column(name = "brand_id")
    private Integer id;
    @Column(name = "brand_name")
    private String name;

    @Transient
    @JsonIgnore
    public static final Short BRAND_AVAILABLE_BITE = 0b011111111111;
}
