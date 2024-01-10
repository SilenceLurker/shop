package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class SystemType {
    @Id
    private short id;
    private String type;
}
