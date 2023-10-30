package www.topview;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * role application
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"www.topview.dao.mapper"})
public class RoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }
}