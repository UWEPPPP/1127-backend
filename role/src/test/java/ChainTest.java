import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.rpc.ContractService;

@SpringBootTest(classes = {www.topview.RoleApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class ChainTest {
    @Autowired
    private ContractService contractService;

    @Test
    public void test() {
      
    }
}
