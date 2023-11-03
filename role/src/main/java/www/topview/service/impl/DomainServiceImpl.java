package www.topview.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.lang.Assert;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webank.weid.protocol.base.CptBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.topview.constant.PathConstant;
import www.topview.constant.RoleConstant;
import www.topview.dao.*;
import www.topview.dto.AddCompanyDTO;
import www.topview.dto.ChainServiceDTO;
import www.topview.dto.PayLoad;
import www.topview.entity.model.AccountModel;
import www.topview.entity.model.RegisterCptModel;
import www.topview.entity.po.*;
import www.topview.entity.vo.CompanyVO;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.rpc.ContractService;
import www.topview.service.DomainService;
import www.topview.service.WeIdentityService;
import www.topview.util.CryptoUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Service
public class DomainServiceImpl implements DomainService {

    @Value("${weIdentity.cpt_template_path}")
    private String cptTemplatePath;
    @Autowired
    private WeIdentityService weIdentityService;
    @Autowired
    private WorkerInfoMapper workerInfoMapper;
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
    @Autowired
    private ContractService contractService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCompany(AddCompanyDTO addCompanyDTO) throws WeIdentityException {
        AccountModel accountModel = weIdentityService.createWeId();
        Integer passer = addCompanyDTO.getPasser();
        //链端调用
        Domain domain = domainMapper.selectOne(new QueryWrapper<Domain>().eq("domain_admin_id", passer));
        ChainServiceDTO chainServiceDTO = new ChainServiceDTO();
        chainServiceDTO.setUserId(passer).
                setContractName("DomainLogic").
                setFunctionName("registerCompany").
                setFunctionParams(new ArrayList<>() {
                    {
                        add(accountModel.getWeId());
                        add(addCompanyDTO.getCompanyName());
                        add(accountModel.getAccountAddress());
                    }
                }).
                setContractAddress(domain.getDomainAddress());
        CommonResult<Object> send = contractService.send(chainServiceDTO);
        Assert.isTrue(send.getCode() == 200, "合约调用失败");
        Map<String, Object> data = Convert.toMap(String.class, Object.class, send.getData());
        String companyAddr = Convert.toStr(data.get("companyAddr"));
        User user = new User(
                null,
                addCompanyDTO.getFounderName(),
                addCompanyDTO.getFounderPassword(),
                accountModel.getWeId(),
                accountModel.getAccountAddress(),
                accountModel.getPublicKey(),
                CryptoUtil.encrypt(accountModel.getPrivateKey(), PathConstant.PATH_PUBLIC_KEY)
                , RoleConstant.COMPANY_ADMIN
        );
        Assert.isTrue(userMapper.insert(user) == 1, "企业管理员注册失败");
        Company company = new Company(
                null,
                user.getId(),
                companyAddr,
                addCompanyDTO.getDomainId(),
                addCompanyDTO.getCompanyName()
        );
        Assert.isTrue(companyMapper.insert(company) == 1, "公司创建失败,数据库更新时发生异常");
        CompanyAdminInfo admin = new CompanyAdminInfo(
                null,
                accountModel.getWeId(),
                company.getId(),
                addCompanyDTO.getDomainId()
        );
        Assert.isTrue(companyAdminMapper.insert(admin) == 1, "企业管理员注册失败");


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompany(int companyId) {
        JWT jwt = JWT.of(request.getHeader("token"));
        PayLoad payload = (PayLoad) jwt.getPayload("payload");
        Integer id = payload.getUserId();
        CompanyAdminInfo companyAdmin = companyAdminMapper.selectOne(new QueryWrapper<CompanyAdminInfo>().eq("company_id", companyId));
        //链端调用
        Domain domain = domainMapper.selectOne(new QueryWrapper<Domain>().eq("domain_admin_id", id));
        ChainServiceDTO chainServiceDTO = new ChainServiceDTO();
        chainServiceDTO.setUserId(id).
                setContractName("DomainLogic").
                setFunctionName("removeCompany").
                setFunctionParams(new ArrayList<>() {
                    {
                        add(companyAdmin.getWeId());
                    }
                }).
                setContractAddress(domain.getDomainAddress());
        CommonResult<Object> send = contractService.send(chainServiceDTO);
        Assert.isTrue(send.getCode() == 200, "合约调用失败");
        int result = companyMapper.deleteById(companyId);
        Assert.isTrue(result == 1, "公司不存在或者删除失败");
        int delete1 = workerInfoMapper.delete(new QueryWrapper<WorkerInfo>().eq("company_id", companyId));
//        Assert.isTrue(companyId1 > 0, "公司不存在或者删除失败"); 有无员工？
        int delete2 = companyAdminMapper.delete(new QueryWrapper<CompanyAdminInfo>().eq("company_id", companyId));
        Assert.isTrue(delete2 > 0, "公司管理员删除失败：公司不存在或者删除失败");


    }

    @Override
    public String getCptTemplate() {
        FileReader fileReader = new FileReader(cptTemplatePath);
        return fileReader.readString();
    }

    @Override
    public CptInfoVO registerCpt(RegisterCptModel model) throws WeIdentityException {
        JWT jwt = JWT.of(request.getHeader("token"));
        PayLoad payload = (PayLoad) jwt.getPayload("payload");
        Integer id = payload.getUserId();
        User admin = userMapper.selectById(id);
        String privateKey = CryptoUtil.decrypt(admin.getPrivateKey(), PathConstant.PATH_PRIVATE_KEY);
        CptBaseInfo cptBaseInfo = weIdentityService.registerCpt(admin.getWeId(), privateKey, model.getClaim());
        CptInfoVO cptInfoVO = new CptInfoVO();
        cptInfoVO.setCptVersion(cptBaseInfo.getCptVersion())
                .setCptId(cptBaseInfo.getCptId());
        return cptInfoVO;
    }

    @Override
    public List<CompanyVO> getCompanyList() {
        JWT jwt = JWT.of(request.getHeader("token"));
        PayLoad payload = (PayLoad) jwt.getPayload("payload");
        Integer id = payload.getUserId();
        Domain domain = domainMapper.selectOne(new QueryWrapper<Domain>().eq("domain_admin_id", id));
        Assert.notNull(domain, "该域不存在");
        List<Company> companies = companyMapper.selectList(new QueryWrapper<Company>().eq("domain_id", domain.getId()));
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
