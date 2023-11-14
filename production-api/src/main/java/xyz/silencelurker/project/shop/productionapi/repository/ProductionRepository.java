package xyz.silencelurker.project.shop.productionapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xyz.silencelurker.project.shop.productionapi.entity.Production;

/**
 * @author Silence_Lurker
 */
@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer> {

}
