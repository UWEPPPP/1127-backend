package www.topview.service;

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
     * @param publisher
     * @param privateKey
     * @param claim
     * @return {@link Boolean}
     */
    public Boolean registerCpt(String publisher, String privateKey, Map<String, Object> claim);
}
