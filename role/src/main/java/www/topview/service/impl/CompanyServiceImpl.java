package www.topview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.topview.dao.mapper.ApplyMapper;
import www.topview.entity.vo.ApplyVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 刘家辉
 * @date 2023/10/30
 */
@Service
public class CompanyServiceImpl implements www.topview.service.CompanyService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public void addWorker() {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token


        //TODO 尚未完成 等待链端接口
        //调合约接口 将员工注册进合约
        //调数据库 将员工的公司信息加入表里


    }

    @Override
    public List<ApplyVO> getAllApplyFromWorker() {

        applyMapper.selectByMap(null);
        return null;
    }
}
