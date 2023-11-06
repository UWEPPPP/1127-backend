package www.topview.asset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import www.topview.feign.ChainClient;
import www.topview.feign.CompanyClient;
import www.topview.feign.UserClient;

/**
 * @author lql
 * @date 2023/10/29
 */
@EnableDiscoveryClient
@EnableFeignClients(clients = {ChainClient.class, UserClient.class, CompanyClient.class})
@MapperScan("com.topview.asset.mapper")
@SpringBootApplication
public class AssetApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssetApplication.class,args);
    }
}
