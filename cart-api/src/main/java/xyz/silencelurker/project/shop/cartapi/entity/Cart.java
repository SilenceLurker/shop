package xyz.silencelurker.project.shop.cartapi.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Silence_Lurker
 */
@Data
@Entity
@Builder
public class Cart {
    @Id
    private int userId;
    private List<CartProduce> cartProduce;

}