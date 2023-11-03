package www.topview.service;

import www.topview.dto.AddCompanyDTO;
import www.topview.entity.bo.RegisterCptBO;
import www.topview.entity.po.Company;
import www.topview.entity.vo.CompanyVO;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;

import java.util.List;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
public interface DomainService {
    /**
     * add company
     *
     * @param addCompanyDTO add company dto
     * @throws WeIdentityException we identity exception
     */
    void addCompany(AddCompanyDTO addCompanyDTO) throws WeIdentityException;

    /**
     * delete company
     *
     * @param companyId company id
     */
    void deleteCompany(int companyId);

    /**
     * get Template
     *
     * @return {@link String}
     */
    String getCptTemplate();

    /**
     * register cpt
     *
     * @param model model
     * @return {@link CptInfoVO}
     * @throws WeIdentityException weidentity exception
     */
    CptInfoVO registerCpt(RegisterCptBO model) throws WeIdentityException;

    /**
     * get company list
     *
     * @return {@link List}<{@link Company}>
     */
    List<CompanyVO> getCompanyList();
}
