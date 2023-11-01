package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("worker_info")
public class WorkerInfo {

    /**
     * 用户主键
     */
    @TableId
    private Integer id;
    /**
     * we id
     */
    @TableField("weid")
    private String weId;
    /**
     * group name
     */
    private String groupName;
    /**
     * company id
     */
    private Integer companyId;
    /**
     * domain id
     */
    private Integer domainId;


}
