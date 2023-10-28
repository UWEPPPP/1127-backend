package www.topview.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("weid_to_cpt")
@Data
public class WeIdToCpt {
    private Long id;
    private String weid;
    private Integer cptId;
}
