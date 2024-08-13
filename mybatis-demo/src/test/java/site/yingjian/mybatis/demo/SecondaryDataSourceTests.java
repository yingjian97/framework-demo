package site.yingjian.mybatis.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.yingjian.mybatis.demo.mapper.secondary.PhoneMapper;
import site.yingjian.mybatis.demo.pojo.secondary.Phone;
import site.yingjian.mybatis.demo.service.PhoneService;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class SecondaryDataSourceTests {

    @Resource
    private PhoneMapper phoneMapper;

    @Resource
    private PhoneService phoneService;

    @Transactional(value = "secondaryDataSourceTransactionManager")
    @Test
    void testSelectList() {
        List<Phone> phones = phoneMapper.selectList(null);
        phones.forEach(System.out::println);
    }


    @Transactional(value = "secondaryDataSourceTransactionManager")
    @Rollback(value = false)
    @Test
    void testInsert() {
        Phone phone = new Phone();
        phone.setBrand("三星");
        phone.setModel("Galaxy");
        phone.setColor("Blue");
        phone.setPrice(BigDecimal.valueOf(2150.00));
        int result = phoneMapper.insert(phone);
        System.out.println("result: " + result);
        System.out.println("id: " + phone.getId());
    }

    @Transactional
    @Test
    void testPage() {
        Page<Phone> page = new Page<>(1, 3);
        QueryWrapper<Phone> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("brand", "三星");
        Page<Phone> phonePage = phoneMapper.selectPage(page, queryWrapper);
        System.out.println(phonePage.getRecords());
        System.out.println(phonePage.getCurrent());
        System.out.println(phonePage.getPages());
        System.out.println(phonePage.getTotal());
    }
}
