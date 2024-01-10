package xyz.silencelurker.project.shop.easyshop.service;

import xyz.silencelurker.project.shop.easyshop.entity.Supporter;

/**
 * @author Silence_Lurker
 */
public interface ISupporterService {
    /**
     * 启用供应商账户
     * 
     * @param supporter
     */
    void enableSupporterAccount(Supporter supporter);

    /**
     * 更新供应商账户信息
     * 
     * @param newSupporterInfo
     * 
     * @return old info
     */
    Supporter updateSupporterInfo(Supporter newSupporterInfo);

    /**
     * Supporter login in
     * 
     * @param id
     * @param password
     * @return
     */
    Supporter supporterLoginIn(int id, String password);

    /**
     * Supporter Login in by token cookie
     * 
     * @param cookie
     * @return
     */
    Supporter supporterLoginIn(String cookie);
}
