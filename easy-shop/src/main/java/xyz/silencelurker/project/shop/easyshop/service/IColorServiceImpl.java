package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Color;
import xyz.silencelurker.project.shop.easyshop.repository.ColorRepository;

/**
 * @author Silence_Lurker
 */

@Service
public class IColorServiceImpl implements IColorService {
    @Resource
    private ColorRepository colorRepository;

    @Override
    public Color createNewColor(Color newColorInfo) {
        var entity = colorRepository.save(newColorInfo);

        return entity;
    }

    @Override
    public Color createNewColor(int id, String name, String image) {
        Color color = new Color();
        color.setId(id);
        color.setName(name);
        color.setImage(image);

        colorRepository.save(color);

        return color;
    }

    @Override
    public List<Color> findAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public List<Color> findColorByExample(Example<Color> example) {
        return colorRepository.findAll(example);
    }

}
