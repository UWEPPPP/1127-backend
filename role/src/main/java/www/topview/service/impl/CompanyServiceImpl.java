package www.topview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘家辉
 * @date 2023/10/30
 */
@Service
public class CompanyServiceImpl implements www.topview.service.CompanyService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    WeIdentityServiceImpl weIdentityService;
}
