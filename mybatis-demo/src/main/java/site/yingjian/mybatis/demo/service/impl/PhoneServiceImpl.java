package site.yingjian.mybatis.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.yingjian.mybatis.demo.mapper.secondary.PhoneMapper;
import site.yingjian.mybatis.demo.pojo.secondary.Phone;
import site.yingjian.mybatis.demo.service.PhoneService;

@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {
}
