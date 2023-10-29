package www.topview.controller;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.client.protocol.response.BlockNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
//    @RequestMapping("/hello")
//    public String hello() {
////       String configFile = TestController.class.getClassLoader().getResource("fisco.toml").getPath();
////        // 初始化BcosSDK
////        // 为群组group初始化client
////        BcosSDK bcos = BcosSDK.build(configFile);
////        Client client = bcos.getClient("group0");
////        return client.getChainId();
//    }
}
