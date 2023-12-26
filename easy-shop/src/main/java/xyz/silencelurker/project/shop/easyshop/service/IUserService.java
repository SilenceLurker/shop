package xyz.silencelurker.project.shop.easyshop.service;

/**
 * @author Silence_Lurker
 */
public interface IUserService {
    /**
     * 
     * @param name
     * @param password
     * @param email
     * @return
     */
    String register(String name, String password, String email);

    /**
     * 
     * @param name
     * @param password
     * @return
     */
    String loginByName(String name, String password);

    /**
     * 
     * @param id
     * @param password
     * @return
     */
    String loginById(String id, String password);

    /**
     * 
     * @param email
     * @param password
     * @return
     */
    String loginByEmail(String email, String password);

}
