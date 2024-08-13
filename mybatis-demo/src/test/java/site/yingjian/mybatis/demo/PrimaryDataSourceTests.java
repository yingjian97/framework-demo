package site.yingjian.mybatis.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.yingjian.mybatis.demo.handler.UserHandler;
import site.yingjian.mybatis.demo.mapper.primary.UserMapper;
import site.yingjian.mybatis.demo.pojo.primary.User;
import site.yingjian.mybatis.demo.service.UserService;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class PrimaryDataSourceTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Transactional
    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Transactional
    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@qq.com");
        int result = userMapper.insert(user);
        System.out.println("result: " + result);
        System.out.println("id: " + user.getId());
    }

    @Transactional
    @Test
    void testSelectMapById() {
//        Map<String, Object> map = userMapper.selectMapById(1L);
        Map<String, Object> map = userMapper.selectMapById(2L);
        System.out.println(map);
    }

    @Transactional
    @Test
    void testGetCount() {
        long count = userService.count();
        System.out.println(count);
    }

    @Transactional
    @Test
    void testPage() {
        Page<User> page = new Page<>(1, 3);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "Jack");
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        System.out.println(userPage.getRecords());
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getPages());
        System.out.println(userPage.getTotal());
    }

    @Transactional
    @Test
    void testPageVo() {
        Page<User> page = new Page<>(2, 3);
        Page<User> userPage = userMapper.selectPageVo(page, 20);
        System.out.println(userPage.getRecords());
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getPages());
        System.out.println(userPage.getTotal());
    }

    @Transactional
    @Test
    void testFetchSize() {
        UserHandler userHandler = new UserHandler();
        userMapper.selectFetchSize(userHandler, 20);
    }
}
