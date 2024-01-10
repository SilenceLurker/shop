package xyz.silencelurker.project.shop.easyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.silencelurker.project.shop.easyshop.entity.Recommendation;

/**
 * @author Silence_Lurker
 */

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

}
