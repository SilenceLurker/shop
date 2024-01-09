package xyz.silencelurker.project.shop.easyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.silencelurker.project.shop.easyshop.entity.Supporter;

/**
 * @author Silence_Lurker
 */
@Repository
public interface SupporterRepository extends JpaRepository<Supporter, Integer> {

}
