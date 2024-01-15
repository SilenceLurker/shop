package xyz.silencelurker.project.shop.easyshop.entity;

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
public class Production {
    /**
     * WTF???
     */
    @Id
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Color.class)
    private Color color;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SystemType.class)
    private SystemType system;
    private Type type;
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
