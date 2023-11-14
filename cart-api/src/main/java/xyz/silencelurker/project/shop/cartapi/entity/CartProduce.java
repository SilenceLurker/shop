package xyz.silencelurker.project.shop.cartapi.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Builder
public class CartProduce {
    private int id;
    private int number;
    private int colorId;
    private int memoryId;
}
