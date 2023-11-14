package xyz.silencelurker.project.shop.companyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.silencelurker.project.shop.companyapi.entity.Company;

/**
 * @author Silence_Lurker
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
