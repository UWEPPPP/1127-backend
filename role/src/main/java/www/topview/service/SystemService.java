package www.topview.service;

import www.topview.entity.vo.UserAllInfoVO;

/**
 * system service
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
public interface SystemService {

    /**
     * get user all info
     *
     * @param id id
     * @return {@link UserAllInfoVO}
     */
    UserAllInfoVO getUserAllInfo(Integer id);
}
