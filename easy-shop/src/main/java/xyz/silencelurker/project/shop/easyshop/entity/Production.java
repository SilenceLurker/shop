package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
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
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Color color;
    @ManyToOne
    private SystemType system;
    @ManyToOne
    private Type type;
    private boolean enable;
    private short subId;
    private double price;
    private long time;

    public Production() {
        this.time = System.currentTimeMillis();
    }
}
