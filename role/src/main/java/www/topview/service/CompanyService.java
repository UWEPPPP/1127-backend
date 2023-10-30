package www.topview.service;


import www.topview.entity.vo.ApplyVO;

import java.util.List;

/**
 * 公司服务
 *
 * @author 刘家辉
 * {@code @date} 2023/10/30
 */
public interface CompanyService {

    /**
     * add worker
     */
    public void addWorker();

    /**
     * get all apply from worker
     *
     * @return
     */
    public List<ApplyVO> getAllApplyFromWorker();
}
