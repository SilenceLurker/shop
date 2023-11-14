package xyz.silencelurker.project.shop.sendapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Builder
public class Send {
    @Id
    private int id;
    private int userId;
    private String name;
    @Column(length = 13)
    private String phone;
    private String local;
    private String note;
    private String address;
    /**
     * Are You Kidding Me???
     */
    private boolean selected;

}