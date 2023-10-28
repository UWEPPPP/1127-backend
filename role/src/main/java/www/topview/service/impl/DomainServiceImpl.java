package www.topview.service.impl;

import cn.hutool.core.io.file.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import www.topview.service.DomainService;
import www.topview.service.WeIdentityService;

import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Service
public class DomainServiceImpl implements DomainService {
    @Value("${weidentity.cpt_template_path}")
    private String cptTemplatePath;
    @Autowired
    WeIdentityService weIdentityService;

    @Override
    public String getCptTemplate() {
        FileReader fileReader = new FileReader(cptTemplatePath);
        return fileReader.readString();
    }

    @Override
    public Boolean registerCpt(String publisher, String privateKey, Map<String, Object> claim) {
        return null;
    }
}
