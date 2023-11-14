package xyz.silencelurker.project.shop.userapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

/**
 * MySQL + Redis
 * 
 * @author Silence_Lurker
 */
@Data
@Entity
@Builder
public class User {
    @Id
    private int id;
    private String name;
    private String information;
    /**
     * WTF???
     * Helicopter???
     * 256 kinds of sex type???
     * Never mind ,I just follow the document...
     */
    private byte sex;

    private String email;
    private String password;
    private String token;
    @Column(name = "utype")
    private Integer userType;

}
