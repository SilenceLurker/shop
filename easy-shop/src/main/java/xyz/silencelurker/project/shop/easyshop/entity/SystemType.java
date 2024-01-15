package xyz.silencelurker.project.shop.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SystemType {
    @Id
    private short id;
    private String type;
}
