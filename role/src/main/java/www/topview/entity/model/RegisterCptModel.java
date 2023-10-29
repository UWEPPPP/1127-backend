package www.topview.entity.model;

import lombok.Data;

import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Data
public class RegisterCptModel {
    private String publisher;
    private Map<String, Object> claim;

}
