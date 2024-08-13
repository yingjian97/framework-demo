package site.yingjian.mybatis.demo.pojo.secondary;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "phone", schema = "mybatis_demo")
public class Phone {
    private Long id;

    private String brand;

    private String model;

    private String color;

    private BigDecimal price;
}
