package xyz.silencelurker.project.shop.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class MemoryAndDisk {
    @Transient
    @JsonIgnore
    public static final int MEMORY_MOVE = 10;
    @Transient
    @JsonIgnore
    public static final Short OTHER_MENORY = (short) 0b1111110000000000;
    @Transient
    @JsonIgnore
    public static final Short OTHER_DISK = 0b1111111111;

    @Id
    @Column(name = "memory_and_disk_id")
    private Short id;
    private String name;
    private Short memory;
    private Short disk;

    @Override
    public String toString() {
        return "Memory : " + ((memory + 1) << 1) + " Disk : " + ((disk + 1) << 4);
    }

    public void setMemory(Short memory) {
        if (OTHER_MENORY.equals(memory)) {
            this.memory = OTHER_MENORY;
        }
        this.memory = (short) (memory << MEMORY_MOVE);
    }
}