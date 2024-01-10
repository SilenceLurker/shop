package xyz.silencelurker.project.shop.easyshop.service;

import org.springframework.data.domain.Example;

import xyz.silencelurker.project.shop.easyshop.entity.SystemType;

/**
 * @author Silence_Lurker
 */
public interface ISystemTypeService {

    /**
     * add new Type
     * 
     * @param newSystemType
     */
    void addNewSystemType(SystemType newSystemType);

    /**
     * get Type By Example
     * 
     * @param example
     * @return
     */
    SystemType getSystemType(Example<SystemType> example);

}
