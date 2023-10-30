package www.topview.service;

import www.topview.entity.model.RegisterCptModel;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
public interface DomainService {
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
     * @throws WeIdentityException we identity exception
     */
    public CptInfoVO registerCpt(RegisterCptModel model) throws WeIdentityException;
}
