package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * domain
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Accessors(chain = true)
@TableName("domain")
public class Domain {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String domainName;
    private String domainAddress;
    private Integer domainAdminId;
}
