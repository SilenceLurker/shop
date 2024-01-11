package xyz.silencelurker.project.shop.easyshop.service;

import xyz.silencelurker.project.shop.easyshop.entity.Type;

/**
 * @author Silence_Lurker
 */
public interface ITypeService {
    /**
     * get formate type
     * 
     * @param code
     * @return
     */
    Type getTypeByCode(int code);

    /**
     * get by name
     * 
     * @param type
     * @return
     */
    Type getTypeByName(String type);

}
