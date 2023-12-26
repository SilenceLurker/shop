package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Short id;
    private String name;
}
