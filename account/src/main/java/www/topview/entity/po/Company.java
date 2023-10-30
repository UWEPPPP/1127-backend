package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */

@Data
@TableName("company")
public class Company {

    /**
     * 公司id(主键)
     */
    private Integer id;

    /**
     * 为公司生成一个weid
     */
    @TableField("weid_company")
    private String weIdCompany;

    /**
     * 申请的id,随机生成并返回给
     */
    @TableField("register_id")
    private Integer registerId;

    /**
     * 公司私钥
     */
    @TableField("private_key")
    private String privateKey;

    /**
     * 公司公钥
     */
    @TableField("public_key")
    private String publicKey;


    /**
     * 公司账户地址
     */
    @TableField("account_address")
    private String accountAddress;

    public Company(Integer id, String weIdCompany, Integer registerId, String privateKey, String publicKey, String accountAddress) {
        this.id = id;
        this.weIdCompany = weIdCompany;
        this.registerId = registerId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.accountAddress = accountAddress;
    }

    public Company() {
    }
}
