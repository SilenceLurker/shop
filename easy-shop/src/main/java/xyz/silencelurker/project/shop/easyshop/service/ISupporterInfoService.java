package xyz.silencelurker.project.shop.easyshop.service;

import xyz.silencelurker.project.shop.easyshop.entity.SupporterInfo;

/**
 * @author Silence_Lurker
 */
public interface ISupporterInfoService {
    /**
     * register a new Supporter info
     * 
     * @param newInfo
     */
    void registerSupporterInfo(SupporterInfo newInfo);

    /**
     * find supporter info by id
     * 
     * @param accountId
     * @return
     */
    boolean checkSupporterInfo(int accountId);
}
