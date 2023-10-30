package www.topview.asset.domain.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 创建asset
 *
 * @author lql
 * @date 2023/10/29
 */
@Data
public class CreateAssetRequest {
    /**
     * 文件
     */
    private MultipartFile file;

    /**
     * 资产名
     */
    private String name;

    /**
     * 组名（为空则为default）
     */
    private String groupName;

    /**
     * 资产描述
     */
    private String description;
}
