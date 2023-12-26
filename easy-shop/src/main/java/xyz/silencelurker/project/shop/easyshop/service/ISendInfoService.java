package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import xyz.silencelurker.project.shop.easyshop.entity.SendInfo;

/**
 * @author Silence_Lurker
 */
public interface ISendInfoService {
    /**
     * 创建新的寄送信息
     * 
     * @param newInfo
     * @return
     */
    boolean createNewSendInfo(SendInfo newInfo);

    /**
     * 通过用户ID查询素有的寄送信息
     * 
     * @param accountId
     * @return
     */
    List<SendInfo> findAllByUserId(int accountId);

    /**
     * 通过寄送信息id删除指定的寄送信息
     * 
     * @param id
     * @return
     */
    SendInfo deleteSendInfoById(String id);

}
