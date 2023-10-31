import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.exception.WeIdentityException;
import www.topview.mapper.WorkerMapper;
import www.topview.service.AccountService;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */


@SpringBootTest(classes = {www.topview.AccountApplication.class})

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class RegisterTest {

    @Autowired
    WorkerMapper workerMapper;

    @Autowired
    AccountService accountService;

    @Test
    public void RegisterTest() throws WeIdentityException {

//        Worker worker = new Worker(1, "1", "1", "11", "1", "1", "1", 1);


//        workerMapper.insert(worker);
//        System.out.println(accountService.userRegister(new UserRegisterBO("testUser1","testUser1")));
    }


}
