package www.topview.asset.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 对应资产表的po类
 *
 * @author lql
 * @date 2023/10/29
 */
@Data
@TableName("tb_asset")
public class Asset {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资产名字
     */
    private String name;

    /**
     * 创建者名字
     */
    private String creatorName;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 公司/企业名
     */
    private String companyName;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 操作次数
     */
    private Integer operateTimes;

    /**
     * 资产状态（0 => 有效;1 => 无效;2 => 其他）
     */
    private Integer status;
}
