package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int supporterId;
    private long productionId;
    private String logo;
    private boolean enable;
}
