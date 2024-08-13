package site.yingjian.aop.demo.aop;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner {

    @Resource
    private Cal cal;

    @Override
    public void run(String... args) throws Exception {
        cal.add(1, 2);
        cal.sub(1, 2);
        cal.mul(1, 2);
        cal.div(1, 2);
    }
}
