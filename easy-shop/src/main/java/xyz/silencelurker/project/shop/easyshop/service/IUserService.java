package xyz.silencelurker.project.shop.easyshop.service;

import xyz.silencelurker.project.shop.easyshop.entity.User;

/**
 * @author Silence_Lurker
 */
public interface IUserService {
    /**
     * 注册
     * 
     * @param name
     * @param password
     * @param email
     * @return
     */
    String register(String name, String password, String email);

    /**
     * 通过用户名登录
     * 
     * @param name
     * @param password
     * @return
     */
    User loginByName(String name, String password);

    /**
     * 通过id登录
     * 
     * @param id
     * @param password
     * @return
     */
    User loginById(String id, String password);

    /**
     * 通过邮箱地址登录
     * 
     * @param email
     * @param password
     * @return
     */
    User loginByEmail(String email, String password);

    /**
     * 更新用户信息
     * 
     * @param newInfo
     * 
     * @return 用户信息
     */
    User updateInfo(User newInfo);

    /**
     * 邮箱更新
     * 
     * @param accountId
     * @return 一个随机特征值，可以为UUID或其他什么乱七八糟的东西
     */
    String emailUpdate(int accountId);

    /**
     * 更改邮箱确认
     * 
     * @param newEmail
     * @param checkCode
     * @param accountId
     * 
     * @return true为认证成功并更改，false为认证失败
     */
    boolean emailUpdateConfirm(String newEmail, String checkCode, int accountId);

    /**
     * get accountId user info
     * 
     * @param accountId
     * @return
     */
    User getUserInfo(int accountId);

}
