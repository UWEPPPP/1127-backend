package www.topview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.mapper.WeIdToCptMapper;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private WeIdToCptMapper cptMapper;
    @RequestMapping("/")
    public void test() {
        System.out.println(("----- selectAll method test ------"));
        cptMapper.selectList(null).forEach(System.out::println);
    }
}
