package www.topview.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.topview.dao.*;
import www.topview.entity.po.*;
import www.topview.entity.vo.UserAllInfoVO;
import www.topview.service.SystemService;

import java.util.HashMap;
import java.util.Map;

/**
 * system service impl
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DomainMapper domainMapper;
    @Autowired
    private CompanyAdminMapper companyAdminMapper;
    @Autowired
    private DomainAdminInfoMapper domainAdminMapper;
    @Autowired
    private WorkerInfoMapper workerInfoMapper;

    @Override
    public UserAllInfoVO getUserAllInfo(Integer id) {
        User user = userMapper.selectById(id);
        Assert.notNull(user, "该用户不存在");
        UserAllInfoVO userAllInfoVO = new UserAllInfoVO();
        userAllInfoVO.setUserId(user.getId())
                .setName(user.getUsername())
                .setPassword(user.getPassword())
                .setAddress(user.getWeId())
                .setPublicKey(user.getPublicKey())
                .setPrivateKey(user.getPrivateKey())
                .setWeid(user.getWeId());
        Map<String, Object> map = new HashMap<>(5);
        Domain domain = new Domain();
        switch (user.getRole()) {
            case 0:
                WorkerInfo info = workerInfoMapper.selectOne(new QueryWrapper<WorkerInfo>().eq("weid", user.getWeId()));
                Company company = companyMapper.selectById(info.getCompanyId());
                domain = domainMapper.selectById(company.getDomainId());
                map.put("groupName", info.getGroupName());
                map.put("companyId", info.getCompanyId());
                map.put("companyName", company.getCompanyName());
                break;
            case 1:
                CompanyAdminInfo info1 = companyAdminMapper.selectOne(new QueryWrapper<CompanyAdminInfo>().eq("weid", user.getWeId()));
                Company company1 = companyMapper.selectById(info1.getCompanyId());
                domain = domainMapper.selectById(company1.getDomainId());
                map.put("companyId", info1.getCompanyId());
                map.put("companyName", company1.getCompanyName());
                break;
            case 2:
                DomainAdminInfo info2 = domainAdminMapper.selectOne(new QueryWrapper<DomainAdminInfo>().eq("weid", user.getWeId()));
                domain = domainMapper.selectById(info2.getDomainId());
                break;
            default:
        }
        Assert.notNull(domain.getDomainName(), "该用户不存在");
        map.put("domainId", domain.getId());
        map.put("domainName", domain.getDomainName());
        userAllInfoVO.setRoleInfo(map);
        return userAllInfoVO;
    }
}
