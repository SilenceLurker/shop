package xyz.silencelurker.project.shop.easyshop.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class SendInfo {
    @Id
    @UuidGenerator
    private String id;
    private String accountId;
    private String name;
    private String phone;
    private String local;
    private String address;
    private String tag;
    private boolean defaultSelected;
}
