package www.topview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@SpringBootApplication
@ServletComponentScan("www.topview.controller")
@EnableFeignClients(basePackages = {"www.topview.feign"})
@MapperScan("www.topview.mapper")
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
