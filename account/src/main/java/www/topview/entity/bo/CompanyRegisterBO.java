package www.topview.entity.bo;

import com.webank.weid.protocol.base.Credential;
import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
public class CompanyRegisterBO {

    /**
     * 账号,由申请者线下进行提供
     */
    private String username;

    /**
     * 密码,由申请者线下进行提供
     */
    private String password;

    public CompanyRegisterBO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CompanyRegisterBO() {
    }
}
