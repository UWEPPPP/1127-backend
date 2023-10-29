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
    @TableId(type = IdType.AUTO)
    private Long id;


}
