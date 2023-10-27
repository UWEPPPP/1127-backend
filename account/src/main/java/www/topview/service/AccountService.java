package www.topview.service;

import www.topview.entity.bo.RegisterBO;
import www.topview.result.CommonResult;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */
public interface AccountService {

    /**
     * 注册
     * @param registerBO 包含注册信息
     * @return 返回注册结果
     */
     boolean register(RegisterBO registerBO);

}
