package xyz.silencelurker.project.shop.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 这条我不确定是不是因为保留名称的问题所以不能直接用Color，如果不需要我会删了的
 */
@Table(name = "production_color")
/**
 * @author Silence_Lurker
 */
@Data
@Entity
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;

    @JsonIgnore
    public static final int BRAND_LEFT_BITE = 17;
    @JsonIgnore
    public static final int COLOR_LEFT_BITE = 12;
    @JsonIgnore
    public static final int PRODUCTION_LEFT_BITY = 0;

    @JsonIgnore
    public static final int BRAND_AVAILABLE_BITE = 0b11111111111111100000000000000000;
    @JsonIgnore
    public static final int COLOR_AVAILABLE_BITE = 0b11111000000000000;
    @JsonIgnore
    public static final int PRODUCTION_AVAILABLE_BITE = 0b111111111111;
}
