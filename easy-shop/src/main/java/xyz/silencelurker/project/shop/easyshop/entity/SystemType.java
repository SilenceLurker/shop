package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Embeddable
public class SystemType {
    @Id
    private short id;
    private String type;
}
