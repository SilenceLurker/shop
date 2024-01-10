package xyz.silencelurker.project.shop.easyshop.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xyz.silencelurker.project.shop.easyshop.entity.Production;

/**
 * @author Silence_Lurker
 */
public interface IProductionService {
    /**
     * create new Production
     * 
     * @param newProduction
     */
    void createProduction(Production newProduction);

    /**
     * 通过subId和用户id查找产品
     * 
     * @param subId
     * @param accountId
     * 
     * @return
     */
    List<Production> findProductionBySubId(int subId, int accountId);

    /**
     * get All
     * 
     * @return
     */
    List<Production> getAllProduction();

    /**
     * 分页
     * 
     * @param pageable
     * @return
     */
    Page<Production> getAllProduction(Pageable pageable);

    /**
     * get by example
     * 
     * @param example
     * @return
     */
    List<Production> getAllByExample(Example<Production> example);

    /**
     * get by example
     * 
     * @param example
     * @param pageable
     * @return
     */
    Page<Production> getAllByExample(Example<Production> example, Pageable pageable);

    /**
     * change status
     * 
     * @param id
     * @return
     */
    boolean changeProductonStatus(long id);

}
