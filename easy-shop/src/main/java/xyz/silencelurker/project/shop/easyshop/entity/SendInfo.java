package xyz.silencelurker.project.shop.easyshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class SendInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String accountId;
    private String name;
    private String phone;
    private String local;
    private String address;
    private String tag;
    private boolean defaultSelected;
}
