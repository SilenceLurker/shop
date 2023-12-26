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
    @Id
    private Short id;
    private String name;
    private Short memory;
    private Short disk;
}