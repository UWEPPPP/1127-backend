package www.topview;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * role application
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"www.topview.feign"})
@MapperScan({"www.topview.dao"})
public class RoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }
}