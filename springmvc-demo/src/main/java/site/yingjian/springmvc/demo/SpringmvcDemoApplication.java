package site.yingjian.springmvc.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static site.yingjian.springmvc.demo.constant.Form.TRANS;

@SpringBootApplication
public class SpringmvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcDemoApplication.class, args);
        TRANS.write();
    }
}
