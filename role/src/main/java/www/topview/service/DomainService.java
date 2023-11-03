package www.topview.service;

import www.topview.dto.AddCompanyDTO;
import www.topview.entity.model.RegisterCptModel;
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
     * add company
     *
     * @param addCompanyDTO add company dto
     * @throws WeIdentityException we identity exception
     */
    public void addCompany(AddCompanyDTO addCompanyDTO) throws WeIdentityException;

    /**
     * delete company
     *
     * @param companyId company id
     * @throws WeIdentityException we identity exception
     */
    public void deleteCompany(int companyId) throws WeIdentityException;

    /**
     * get Template
     *
     * @return {@link String}
     */
    public String getCptTemplate();

    /**
     * register cpt
     *
     * @param model model
     * @return {@link CptInfoVO}
     * @throws WeIdentityException weidentity exception
     */
    public CptInfoVO registerCpt(RegisterCptModel model) throws WeIdentityException;

    /**
     * get company list
     *
     * @return {@link List}<{@link Company}>
     */
    public List<CompanyVO> getCompanyList();
}
