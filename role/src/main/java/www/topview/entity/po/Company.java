package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("company")
public class Company {

    /**
     * 公司id(主键)
     */
    private Integer id;

    /**
     * 申请的id,随机生成并返回给
     */
    @TableField("register_id")
    private Integer registerId;

    /**
     * company name
     */
    private String companyName;


    /**
     * 公司账户地址
     */
    @TableField("contract_address")
    private String contractAddress;

    /**
     * domain id
     */
    private Integer domainId;


}