package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Type;
import xyz.silencelurker.project.shop.easyshop.repository.TypeRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class ITypeServiceImpl implements ITypeService {
    @Resource
    private TypeRepository typeRepository;

    @Override
    public Type getTypeByCode(int code) {
        if (Type.OfficialFlipMachine.getTypeCode() == code) {
            return Type.OfficialFlipMachine;
        } else if (Type.OfficialMachine.getTypeCode() == code) {
            return Type.OfficialMachine;
        } else {
            return Type.OfficialSecondHand;
        }
    }

}
