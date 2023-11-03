package www.topview.service;


import www.topview.dto.AddWorkerDTO;
import www.topview.entity.vo.WorkerVO;
import www.topview.exception.WeIdentityException;

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
     *
     * @param addWorkerDTO add worker dto
     * @throws WeIdentityException weidentity exception
     */
    void addWorker(AddWorkerDTO addWorkerDTO) throws WeIdentityException;

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
