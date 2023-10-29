package www.topview.service;

import www.topview.entity.model.RegisterCptModel;

import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
public interface DomainService {
    /**
     * get Template
     * @return {@link String}
     */
    public String getCptTemplate();

    /**
     * @param model
     * @return {@link Boolean}
     */
    public Boolean registerCpt(RegisterCptModel model);
}
