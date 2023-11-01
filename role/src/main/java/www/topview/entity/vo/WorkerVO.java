package www.topview.entity.vo;

import lombok.Data;
import www.topview.entity.po.User;
import www.topview.entity.po.WorkerInfo;

/**
 * worker vo
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Data
public class WorkerVO {
    private Integer userId;
    private String userName;
    private String userWeId;
    private String userGroup;

    public WorkerVO(WorkerInfo worker, User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.userWeId = user.getWeId();
        this.userGroup = worker.getGroupName();
    }
}
