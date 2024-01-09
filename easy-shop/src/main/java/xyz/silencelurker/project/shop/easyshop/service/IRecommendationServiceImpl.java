package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import xyz.silencelurker.project.shop.easyshop.entity.Recommendation;
import xyz.silencelurker.project.shop.easyshop.repository.RecommendationRepository;

/**
 * @author Silence_Lurker
 */
@Service
public class IRecommendationServiceImpl implements IRecommendationService {
    @Resource
    private RecommendationRepository recommendationRepository;

    @Override
    public void setRecommendations(int supporterId, List<Long> productions, String logo) {
        var recommendation = new Recommendation();

        recommendation.setEnable(false);
        recommendation.setLogo(logo);
        recommendation.setSupporterId(supporterId);

        for (Long pId : productions) {
            recommendation.setProductionId(pId);
            recommendationRepository.save(recommendation);
        }

    }

    @Override
    public boolean changeRecommendationStatus(int id, int recommendationId, boolean enable) {
        var recommendation = recommendationRepository.findById(recommendationId).get();
        recommendation.setEnable(!recommendation.isEnable());
        recommendationRepository.save(recommendation);
        return recommendation.isEnable();

    }

}
