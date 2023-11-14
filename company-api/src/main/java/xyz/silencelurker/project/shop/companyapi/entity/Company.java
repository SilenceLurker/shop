package xyz.silencelurker.project.shop.companyapi.entity;

import java.io.Serializable;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

/**
 * @author Silence_Luker
 */
@Data
@Entity
@Builder
public class Company implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String password;
    private String email;
    @Nullable
    private String token;
    private String type;
    private String unitName;
    private String unitAddress;
    private String sector;
    private String invite;
    private String utype;
}
