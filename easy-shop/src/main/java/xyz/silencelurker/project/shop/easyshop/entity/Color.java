package xyz.silencelurker.project.shop.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
    @Column(name = "color_id")
    private int id;
    private String name;
    private String image;

    @Transient
    @JsonIgnore
    public static final int BRAND_LEFT_BITE = 17;
    @Transient
    @JsonIgnore
    public static final int COLOR_LEFT_BITE = 12;
    @Transient
    @JsonIgnore
    public static final int PRODUCTION_LEFT_BITY = 0;

    @Transient
    @JsonIgnore
    public static final int BRAND_AVAILABLE_BITE = 0b11111111111111100000000000000000;
    @Transient
    @JsonIgnore
    public static final int COLOR_AVAILABLE_BITE = 0b11111000000000000;
    @Transient
    @JsonIgnore
    public static final int PRODUCTION_AVAILABLE_BITE = 0b111111111111;
}
