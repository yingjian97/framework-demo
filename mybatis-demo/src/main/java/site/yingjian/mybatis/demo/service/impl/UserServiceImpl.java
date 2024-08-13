package site.yingjian.mybatis.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.yingjian.mybatis.demo.mapper.primary.UserMapper;
import site.yingjian.mybatis.demo.pojo.primary.User;
import site.yingjian.mybatis.demo.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
