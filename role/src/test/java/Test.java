import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.exception.WeIdentityException;
import www.topview.service.WeIdentityService;

@SpringBootTest(classes = {www.topview.RoleApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class Test {
    @Autowired
    WeIdentityService weIdentityService;
    @org.junit.Test
    public void test() {
        try {
           System.out.printf(weIdentityService.createWeId().toString());
        } catch (WeIdentityException e) {
           log.error("WeIdentity调用异常", e);
         }

    }
}
