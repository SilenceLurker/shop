package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Id;

/**
 * @author Silence_Lurker
 */
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

    Type(){
        
    }

    Type(String type, short typeCode) {
        this.type = type;
        this.typeCode = typeCode;
    }

    public String getType() {
        return type;
    }

    public short getTypeCode() {
        return typeCode;
    }

    public static Type getTypeByCode(short code){
        if(code == OfficialFlipMachine.typeCode){
            return OfficialFlipMachine;
        }else if(code == OfficialMachine.typeCode){
            return OfficialMachine;
        }else{
            return OfficialSecondHand;
        }
    }

    public static Type getTypeByName(String name){
        if(name.strip().equals(OfficialFlipMachine.name())){
            return OfficialFlipMachine;
        }else if(name.strip().equals(OfficialMachine.name())){
            return OfficialMachine;
        }else{
            return OfficialSecondHand;
        }

    }
}
