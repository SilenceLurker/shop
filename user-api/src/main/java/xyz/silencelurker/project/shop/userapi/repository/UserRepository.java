package xyz.silencelurker.project.shop.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.silencelurker.project.shop.userapi.entity.User;

/**
 * @author Silence_Lurker
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
