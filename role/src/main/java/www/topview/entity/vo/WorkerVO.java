package www.topview.entity.vo;

import lombok.Data;
import www.topview.entity.po.Worker;

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

    public WorkerVO(Worker worker) {
        this.userId = worker.getId();
        this.userName = worker.getUsername();
        this.userWeId = worker.getWeIdUser();
        this.userGroup = worker.getGroupName();
    }
}
