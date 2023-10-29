package www.topview.entity.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘家辉
 * @date 2023/10/29
 */
@Data
@Accessors(chain = true)
public class AccountModel {
    private String accountAddress;
    private String privateKey;
    private String publicKey;
    private String weId;
}
