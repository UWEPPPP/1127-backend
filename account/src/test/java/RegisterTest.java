import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.entity.bo.UserRegisterBO;
import www.topview.entity.po.User;
import www.topview.exception.WeIdentityException;
import www.topview.mapper.UserMapper;
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
    UserMapper userMapper;

    @Autowired
    AccountService accountService;

    @Test
    public void RegisterTest() throws WeIdentityException {

        User user = new User(1,"1","1","11","1","1","1");


        userMapper.insert(user);
//        System.out.println(accountService.userRegister(new UserRegisterBO("testUser1","testUser1")));
    }


}
