package www.topview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CompanyServiceImpl implements www.topview.service.CompanyService{
    @Autowired
    private HttpServletRequest request;
}
