package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import xyz.silencelurker.project.shop.easyshop.entity.Production;

/**
 * @author Silence_Lurker
 */
public interface IProductionService {
    /**
     * create new Production
     * 
     * @param newProduction
     */
    void createProduction(Production newProduction);

    /**
     * 通过subId和用户id查找产品
     * 
     * @param subId
     * @param accountId
     * 
     * @return
     */
    List<Production> findProductionBySubId(int subId, int accountId);

    /**
     * change status
     * 
     * @param id
     * @return
     */
    boolean changeProductonStatus(long id);

}
