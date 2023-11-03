import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.constant.PathConstant;
import www.topview.entity.bo.LoginBO;
import www.topview.service.impl.AccountServiceImpl;
import www.topview.util.CryptoUtil;

import java.io.IOException;

/**
 * @author :Lictory
 * @date : 2023/11/02
 */

@Slf4j
@SpringBootTest(classes = {www.topview.AccountApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
public class LoginTest {

    @Autowired
    AccountServiceImpl accountService;
    @Test
    public void test1() throws IOException {
        accountService.login(new LoginBO("1", "1", 1));
    }

    @Test
    public void test2(){
        //mTAeZVPt5/sq4GSZigdZ1VXgfhHNMYl0/V3insPjeSRSOBHWI23AvvJJET1PVID9++HNhUtDnTV9LYM+/2o/03ejEBfcTuS+PYHsffGWjzztxfGxRkHM+4v/B8vA70UEU7/107Xa9AYE/vsrApv+jdk885w2mmd6ACVioi3NVZ0=
        //XkvNfyN/u1giUd+yLTS/wyok+upskD6kHxt4K0ZoX6ab4qimiO9jSbycn1WXGElk9n4ei8sbDC37twHIyE3KYnee8WR6fBt33jcfBzeyHOv2wD2eG77gq1QbU4xWrgyWry8IdXt+SJYoZ1BwcXwpk2M8LXj6M0Sh42+tyiVmPC0=

//        System.out.println(CryptoUtil.encrypt("1", PathConstant.PATH_PUBLIC_KEY));
//        System.out.println(CryptoUtil.decrypt("XkvNfyN/u1giUd+yLTS/wyok+upskD6kHxt4K0ZoX6ab4qimiO9jSbycn1WXGElk9n4ei8sbDC37twHIyE3KYnee8WR6fBt33jcfBzeyHOv2wD2eG77gq1QbU4xWrgyWry8IdXt+SJYoZ1BwcXwpk2M8LXj6M0Sh42+tyiVmPC0=",PathConstant.PATH_PRIVATE_KEY));
    }

}
