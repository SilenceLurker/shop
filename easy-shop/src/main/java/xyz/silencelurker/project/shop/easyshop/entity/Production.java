package xyz.silencelurker.project.shop.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Production {
    /**
     * WTF???
     */
    @Id
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Color.class)
    private Color color;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SystemType.class)

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private SystemType system;
    private Type type;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MemoryAndDisk.class)
    private MemoryAndDisk memoryAndDisk;
    private boolean enable;
    private short subId;
    private double price;
    private long time;

    public Production() {
        this.time = System.currentTimeMillis();
    }

    public boolean getEnable() {
        return enable;
    }
}
