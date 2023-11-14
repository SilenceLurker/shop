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
public class MemoryAndPrice implements Serializable {
    @Id
    @GeneratedValue
    private Integer memoryId;
    private int ram;
    private int rom;
    private double current;
    private float original;
}
