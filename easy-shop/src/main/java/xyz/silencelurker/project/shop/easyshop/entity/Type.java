package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Silence_Lurker
 */
@Entity
public enum Type {
    /**
     * 官方机
     */
    OfficialMachine("官方机", (short) 0b1),
    /**
     * 官翻机
     */
    OfficialFlipMachine("官翻机", (short) 0b10),
    /**
     * 品质二手
     */
    OfficialSecondHand("品质二手", (short) 0b100);

    @Id
    private short typeCode;
    private String type;

    private Type(String type, short typeCode) {
        this.type = type;
        this.typeCode = typeCode;
    }

    public String getType() {
        return type;
    }

    public short getTypeCode() {
        return typeCode;
    }

}
