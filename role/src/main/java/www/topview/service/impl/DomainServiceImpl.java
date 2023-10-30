package www.topview.service.impl;

import cn.hutool.core.io.file.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import www.topview.entity.model.RegisterCptModel;
import www.topview.service.DomainService;
import www.topview.service.WeIdentityService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Service
public class DomainServiceImpl implements DomainService {

    @Value("${weIdentity.cpt_template_path}")
    private String cptTemplatePath;
    @Autowired
    WeIdentityService weIdentityService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public String getCptTemplate() {
        FileReader fileReader = new FileReader(cptTemplatePath);
        return fileReader.readString();
    }

    @Override
    public Boolean registerCpt(RegisterCptModel model) {
     //  weIdentityService.registerCpt( , , model.getClaim());

        return null;
    }

}
