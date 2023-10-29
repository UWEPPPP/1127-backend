package www.topview;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan({"www.topview.mapper"})
public class RoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }
}