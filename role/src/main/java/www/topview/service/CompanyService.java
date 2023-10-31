package www.topview.service;


import www.topview.entity.vo.WorkerVO;

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
     * delete worker
     *
     * @param workerId worker id
     */
    public void deleteWorker(int workerId);

    /**
     * get worker list
     *
     * @return {@link List}<{@link WorkerVO}>
     */
    public List<WorkerVO> getWorkerList();
}
