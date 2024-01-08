package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;

import xyz.silencelurker.project.shop.easyshop.entity.Color;

/**
 * @author Silence_Lurker
 */
public interface IColorService {
    /**
     * create new color info
     * 
     * @param newColorInfo
     * @return
     */
    Color createNewColor(Color newColorInfo);

    /**
     * create new color info
     * 
     * @param id
     * @param name
     * @param image
     * @return
     */
    Color createNewColor(int id, String name, String image);

    /**
     * get All color info
     * 
     * @return
     */
    List<Color> findAllColor();

    /**
     * find match color
     * 
     * @param example
     * @return
     */
    List<Color> findColorByExample(Example<Color> example);
}
