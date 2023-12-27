package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class MemoryAndDisk {
    public static final int MEMORY_MOVE = 10;
    public static final short OTHER_MENORY = (short) 0b1111110000000000;
    public static final short OTHER_DISK = 0b1111111111;

    @Id
    private Short id;
    private String name;
    private Short memory;
    private Short disk;

    @Override
    public String toString() {
        return "Memory : " + ((memory + 1) << 1) + " Disk : " + ((disk + 1) << 4);
    }

    public void setMemory(Short memory) {
        this.memory = (short) (memory << MEMORY_MOVE);
    }
}