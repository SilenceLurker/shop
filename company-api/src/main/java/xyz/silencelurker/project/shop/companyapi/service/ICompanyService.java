package xyz.silencelurker.project.shop.companyapi.service;

import org.springframework.lang.Nullable;

import xyz.silencelurker.project.shop.companyapi.entity.Company;

/**
 * @author Silence_Lurker
 */
public interface ICompanyService {
    /**
     * Get a Company Info By Id
     * 
     * @param id
     * @return
     */
    Company selectCompanyById(Integer id);

    /**
     * Save a new Company
     * 
     * @param newCompany
     * @param inviteCode
     * @return
     */
    Boolean saveNewCompany(Company newCompany, @Nullable String inviteCode);

    /**
     * Company Account Login Method
     * 
     * @param id
     * @param password
     * @return
     */
    String companyLogin(Integer id, String password);

    /**
     * Get Invite Code By Company Id
     * 
     * @param id
     * @return
     */
    String getInviteCodeById(Integer id);

}
