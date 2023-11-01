package www.topview.service.impl;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webank.weid.protocol.base.CptBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import www.topview.constant.PathConstant;
import www.topview.dao.*;
import www.topview.entity.dto.AddCompanyDTO;
import www.topview.entity.model.AccountModel;
import www.topview.entity.model.RegisterCptModel;
import www.topview.entity.po.*;
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
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DomainMapper domainMapper;
    @Autowired
    private DomainAdminInfoMapper domainAdminMapper;
    @Autowired
    private CompanyAdminMapper companyAdminMapper;

    @Override
    public void addCompany(AddCompanyDTO addCompanyDTO) throws WeIdentityException {
        //由于公司表跟worker表中的主键具有相互依赖,故进行间接创建
        //根据accountAddress进行公司Id的创建
        AccountModel accountModel = weIdentityService.createWeId();
        Integer passer = addCompanyDTO.getPasser();
        User user1 = userMapper.selectById(passer);
        Assert.notNull(user1, "该用户不存在");
        Assert.notNull(domainAdminMapper.selectOne(new QueryWrapper<DomainAdminInfo>().eq("weid", user1.getWeId())), "不是domain管理员！");
        //TODO 调用合约
        String contractAddr = null;

        User user = new User(
                null,
                addCompanyDTO.getFounderName(),
                addCompanyDTO.getFounderPassword(),
                accountModel.getWeId(),
                accountModel.getAccountAddress(),
                accountModel.getPublicKey(),
                accountModel.getPrivateKey()
        );
        Assert.isTrue(userMapper.insert(user) == 1, "企业管理员注册失败");
        Company company = new Company(
                null,
                user.getId(),
                //TODO 等待调用合约
                contractAddr,
                addCompanyDTO.getDomainId(),
                addCompanyDTO.getCompanyName()
        );
        //  进行企业注册
        Assert.isTrue(companyMapper.insert(company) == 1, "公司创建失败,数据库更新时发生异常");
        // 进行企业管理员的注册
        //根据公司Id进行userId的创建
        CompanyAdminInfo admin = new CompanyAdminInfo(
                null,
                accountModel.getWeId(),
                company.getId(),
                addCompanyDTO.getDomainId()
        );
        Assert.isTrue(companyAdminMapper.insert(admin) == 1, "企业管理员注册失败");
    }

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
        User admin = userMapper.selectById(id);
        Assert.notNull(admin, "id不存在");
        QueryWrapper<DomainAdminInfo> adminWrapper = new QueryWrapper<>();
        adminWrapper.eq("weid", admin.getWeId());
        Assert.notNull(domainAdminMapper.selectOne(adminWrapper), "该用户不是域管理员");
        String privateKey = CryptoUtil.decrypt(admin.getPrivateKey(), PathConstant.PATH_PRIVATE_KEY);
        CptBaseInfo cptBaseInfo = weIdentityService.registerCpt(admin.getWeId(), privateKey, model.getClaim());
        CptInfoVO cptInfoVO = new CptInfoVO();
        cptInfoVO.setCptVersion(cptBaseInfo.getCptVersion())
                .setCptId(cptBaseInfo.getCptId());
        return cptInfoVO;
    }

    @Override
    public List<CompanyVO> getCompanyList() {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token
        String id = null;
        QueryWrapper<Domain> domainQueryWrapper = new QueryWrapper<>();
        domainQueryWrapper.eq("domain_admin_id", id);
        Domain domain = domainMapper.selectOne(domainQueryWrapper);
        Assert.notNull(domain, "该域不存在");
        QueryWrapper<Company> companyWrapper = new QueryWrapper<>();
        companyWrapper.eq("domain_id", domain.getId());
        List<Company> companies = companyMapper.selectList(companyWrapper);
        List<CompanyVO> companyList = new ArrayList<>();
        for (Company company : companies) {
            User admin = userMapper.selectById(company.getRegisterId());
            Assert.notNull(admin, "该公司管理员不存在");
            CompanyVO companyVO = new CompanyVO();
            companyVO.setCompanyName(company.getCompanyName())
                    .setCompanyContractAddress(company.getContractAddress())
                    .setAdminName(admin.getUsername())
                    .setAdminWeId(admin.getWeId())
                    .setCompanyId(company.getId());
            companyList.add(companyVO);
        }
        return companyList;
    }

}
