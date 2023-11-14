package xyz.silencelurker.project.shop.sendapi.service;

import java.util.List;

import xyz.silencelurker.project.shop.sendapi.entity.Send;

/**
 * @author Silence_Lurker
 */
public interface ISendService {

    /**
     * Add a new Send Address
     * 
     * @param newSend
     * @return
     */
    Void addNewSend(Send newSend);

    /**
     * <p>
     * Warning! This Method is ONLY For Test And Debug .
     * </p>
     * Find Send Info By Id
     * 
     * @param id
     * @return
     */
    Send selectSendById(Integer id);

    /**
     * Find All Info By Id
     * 
     * @param userId
     * @return
     */
    List<Send> selectAllSendByUserId(Integer userId);

}
