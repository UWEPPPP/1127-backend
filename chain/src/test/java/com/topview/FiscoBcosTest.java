package com.topview;

import com.topview.client.FiscoBcos;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * fisco连通性测试
 *
 * @author ashinnotfound
 * @date 2023/10/28
 */
@SpringBootTest
public class FiscoBcosTest {

    @Resource
    private FiscoBcos fiscoBcos;

    @Test
    public void connectionTest(){
        System.out.println(fiscoBcos.getClient().getBlockNumber().getBlockNumber());
    }
}
