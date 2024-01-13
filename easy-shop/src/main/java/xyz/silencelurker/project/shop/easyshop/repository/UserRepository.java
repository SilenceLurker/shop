package xyz.silencelurker.project.shop.easyshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.silencelurker.project.shop.easyshop.entity.User;

/**
 * @author Silence_Lurker
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * id
     * 
     * @param password
     * @param accountId
     * @return
     */
    User findByPasswordAndAccountId(String password, int accountId);

    /**
     * name
     * 
     * @param password
     * @param name
     * @return
     */
    List<User> findByPasswordAndName(String password, String name);

    /**
     * email
     * 
     * @param password
     * @param email
     * @return
     */
    List<User> findByPasswordAndEmail(String password, String email);

}
