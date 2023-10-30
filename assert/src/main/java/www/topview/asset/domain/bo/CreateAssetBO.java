package www.topview.asset.domain.bo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 创建asset
 *
 * @author lql
 * @date 2023/10/29
 */
@Data
public class CreateAssetBO {
    /**
     * 文件
     */
    @NotNull
    private MultipartFile file;

    /**
     * 资产名
     */
    @NotBlank
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
