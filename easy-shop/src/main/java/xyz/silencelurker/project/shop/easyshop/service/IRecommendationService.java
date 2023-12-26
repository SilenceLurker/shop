package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

/**
 * @author Silence_Lurker
 */
public interface IRecommendationService {
    /**
     * 设定推荐产品
     * 
     * @param id
     * @param productions
     * @param logo
     */
    void setRecommendations(int id, List<Long> productions, String logo);

    /**
     * 改变推荐产品状态
     * 
     * @param id
     * @param enable
     * @return
     */
    boolean changeRecommendationStatus(int id, boolean enable);
}
