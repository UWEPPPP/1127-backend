package www.topview.service.impl;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webank.weid.protocol.base.CptBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import www.topview.constant.PathConstant;
import www.topview.dao.CompanyMapper;
import www.topview.dao.UserMapper;
import www.topview.entity.model.RegisterCptModel;
import www.topview.entity.po.Company;
import www.topview.entity.po.User;
import www.topview.entity.vo.CompanyVO;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;
import www.topview.service.DomainService;
import www.topview.service.WeIdentityService;
import www.topview.util.CryptoUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String getCptTemplate() {
        FileReader fileReader = new FileReader(cptTemplatePath);
        return fileReader.readString();
    }

    @Override
    public CptInfoVO registerCpt(RegisterCptModel model) throws WeIdentityException {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token
        String id = null;
        User user = userMapper.selectById(id);
        Assert.notNull(user, "id不存在");
        String privateKey = CryptoUtil.decrypt(user.getPrivateKey(), PathConstant.PATH_PRIVATE_KEY);
        CptBaseInfo cptBaseInfo = weIdentityService.registerCpt(user.getWeIdUser(), privateKey, model.getClaim());
        CptInfoVO cptInfoVO = new CptInfoVO();
        cptInfoVO.setCptVersion(cptBaseInfo.getCptVersion())
                .setCptId(cptBaseInfo.getCptId());
        return cptInfoVO;
    }

    @Override
    public List<CompanyVO> getCompanyList() {
        String id = null;
        QueryWrapper<Company> companyWrapper = new QueryWrapper<>();
        companyWrapper.eq("domain_id", "待定");
        List<Company> companies = companyMapper.selectList(companyWrapper);
        List<CompanyVO> companyList = new ArrayList<>();
        for (Company company : companies) {
            User user = userMapper.selectById(company.getRegisterId());
            CompanyVO companyVO = new CompanyVO();
            companyVO.setCompanyName(company.getCompanyName())
                    .setCompanyContractAddress(company.getContractAddress())
                    .setAdminName(user.getUsername())
                    .setAdminWeId(user.getWeIdUser())
                    .setCompanyId(company.getId());
            companyList.add(companyVO);
        }
        return companyList;
    }

}
