package xyz.silencelurker.project.shop.productionapi.entity;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Builder;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Builder
public class Production implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @MapsId
    @ManyToOne
    private Brand brand;
    private OriginType type;
    private String introduce;
    @Column(name = "system_Type")
    private SystemType system;
    private volatile Integer sales;
    private Float score;
    private List<MemoryAndPrice> memoryAndPrice;
    private List<Color> color;
    private List<String> topImage;
    private List<String> infoImage;
    private Long time;
}
