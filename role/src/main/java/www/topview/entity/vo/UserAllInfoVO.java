package www.topview.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * user all info vo
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@Accessors(chain = true)
public class UserAllInfoVO {
    private Integer userId;
    private String weid;
    private String name;
    private String password;
    private String address;
    private String publicKey;
    private String privateKey;
    /**
     * role info
     * role == 0
     * groupName companyId companyName domainName domainId
     * role == 1
     * companyId companyName domainName domainId
     * role == 2
     * domainName domainId
     */
    private Map<String, Object> roleInfo;

}
