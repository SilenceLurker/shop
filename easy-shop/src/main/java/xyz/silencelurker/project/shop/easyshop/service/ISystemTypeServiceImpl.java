package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.SystemType;
import xyz.silencelurker.project.shop.easyshop.repository.SystemTypeRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class ISystemTypeServiceImpl implements ISystemTypeService {
    @Resource
    private SystemTypeRepository systemTypeRepository;

    @Override
    public void addNewSystemType(SystemType newSystemType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewSystemType'");
    }

    @Override
    public SystemType getSystemType(Example<SystemType> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSystemType'");
    }

}
