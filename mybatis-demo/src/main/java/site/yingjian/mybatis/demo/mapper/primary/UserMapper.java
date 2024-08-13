package site.yingjian.mybatis.demo.mapper.primary;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;
import site.yingjian.mybatis.demo.pojo.primary.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    Map<String, Object> selectMapById(Long id);

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);

    void selectFetchSize(ResultHandler<User> handler, @Param("age") Integer age);
}
