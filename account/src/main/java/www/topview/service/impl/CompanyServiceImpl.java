package www.topview.service.impl;

import org.springframework.stereotype.Service;
import www.topview.dto.CompanyDTO;
import www.topview.entity.po.Company;
import www.topview.entity.po.WorkerInfo;
import www.topview.mapper.CompanyMapper;
import www.topview.mapper.WorkerInfoMapper;
import www.topview.service.CompanyService;

import javax.annotation.Resource;

/**
 * 企业接口实现类
 * @author lql
 * @date 2023/11/06
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private WorkerInfoMapper workerInfoMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public CompanyDTO getCompanyMessage(String userWeId) {
        WorkerInfo workerInfo = workerInfoMapper.selectByWeId(userWeId);
        Company company = companyMapper.selectById(workerInfo.getCompanyId());

        return new CompanyDTO()
                .setCompanyName(company.getCompanyName())
                .setContractAddress(company.getContractAddress())
                .setDomainId(company.getDomainId());
    }
}
