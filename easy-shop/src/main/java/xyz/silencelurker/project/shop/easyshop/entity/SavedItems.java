package xyz.silencelurker.project.shop.easyshop.entity;

import java.util.Map;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Embeddable
public class SavedItems {
    // @ElementCollection
    private Map<Long, Short> items;

}
