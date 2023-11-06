package www.topview.asset.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于显示资产详细信息的vo
 *
 * @author lql
 * @date 2023/10/30
 */
@Data
@Accessors(chain = true)
public class AssetDetailsVO {
    /**
     * 主键
     */
    private Integer id;

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
     * 文件uri
     */
    private String uri;

    /**
     * 创建者地址
     */
    private String creatorAddress;

    /**
     * 创建时间
     */
    private String createTime;
}
