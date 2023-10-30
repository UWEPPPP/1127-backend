package www.topview.service.impl;

import cn.hutool.core.io.file.FileReader;
import com.webank.weid.protocol.base.CptBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import www.topview.entity.model.RegisterCptModel;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;
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
    public CptInfoVO registerCpt(RegisterCptModel model) throws WeIdentityException {
        String header = request.getHeader("token");

        //TODO 尚未完成 等待token
        String weid = null;
        String privateKey = null;
        CptBaseInfo cptBaseInfo = weIdentityService.registerCpt(weid, privateKey, model.getClaim());
        CptInfoVO cptInfoVO = new CptInfoVO();
        cptInfoVO.setCptVersion(cptBaseInfo.getCptVersion())
                .setCptId(cptBaseInfo.getCptId());
        return cptInfoVO;
    }

}
