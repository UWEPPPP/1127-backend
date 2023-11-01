import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.entity.dto.AddWorkerDTO;
import www.topview.exception.WeIdentityException;
import www.topview.service.CompanyService;

/**
 * company test
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@SpringBootTest(classes = {www.topview.RoleApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class CompanyTest {
    @Autowired
    private CompanyService companyService;

    @Test
    public void test() {
        AddWorkerDTO addWorkerDTO = new AddWorkerDTO();
        addWorkerDTO.setPasser(1)
                .setUsername("Test哥")
                .setPassword("123456")
                .setDomainId(1)
                .setCompanyId(1)
                .setGroupName("测试组");
        try {
            companyService.addWorker(addWorkerDTO);
        } catch (WeIdentityException e) {
            log.info("weidentity异常");
        }
    }
}
