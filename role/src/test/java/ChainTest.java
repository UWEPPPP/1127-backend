import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.dto.CreateProcessorDTO;
import www.topview.entity.model.AccountModel;
import www.topview.exception.WeIdentityException;
import www.topview.feign.ChainClient;
import www.topview.result.CommonResult;
import www.topview.service.WeIdentityService;

@SpringBootTest(classes = {www.topview.RoleApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class ChainTest {
    @Autowired
    private ChainClient processorClient;
    @Autowired
    private WeIdentityService weIdentityService;

    @Test
    public void test() throws WeIdentityException {
        AccountModel weId = weIdentityService.createWeId();
        CreateProcessorDTO createProcessorDTO = new CreateProcessorDTO();
        createProcessorDTO.setUserId(1);
        createProcessorDTO.setPrivateKey(weId.getPrivateKey());
        CommonResult<Void> voidCommonResult = processorClient.create(createProcessorDTO);
        System.out.println(voidCommonResult.getMessage());
    }
}
