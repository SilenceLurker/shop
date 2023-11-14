package xyz.silencelurker.project.shop.userapi.service;

import xyz.silencelurker.project.shop.userapi.entity.User;

/**
 * @author Silence_Lurker
 */
public interface IUserService {
    /**
     * Check Login status
     * 
     * @param userId
     * @return
     */
    Boolean isLogin(int userId);

    /**
     * 
     * get Login Info Method
     * 
     * @param check
     * @return
     */
    User loginInfo(String check);

    /**
     * Register Method
     * 
     * @param newUser
     * @return
     */
    Boolean register(User newUser);

    /**
     * Login Method
     * 
     * @param id
     * @param passwd
     * @return
     */
    String login(String id, String passwd);
}
