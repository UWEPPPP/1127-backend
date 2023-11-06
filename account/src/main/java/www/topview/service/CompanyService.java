package www.topview.service;

import www.topview.dto.CompanyDTO;

/**
 * 企业service
 * @author lql
 * @date 2023/11/06
 */
public interface CompanyService {
    /**
     * 获取企业信息
     * @param userWeId 用户weid
     * @return 公司/企业dto
     */
    CompanyDTO getCompanyMessage(String userWeId);
}
