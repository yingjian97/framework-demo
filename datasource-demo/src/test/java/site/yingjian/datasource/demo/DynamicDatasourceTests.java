package site.yingjian.datasource.demo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.yingjian.datasource.demo.dynamic.DynamicDatasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static site.yingjian.datasource.demo.dynamic.DynamicDatasource.datasourceContext;

@SpringBootTest
public class DynamicDatasourceTests {

    @Resource
    private DynamicDatasource dynamicDatasource;

    @Transactional
    @Test
    void testSelectList() throws SQLException {
        datasourceContext.set("dataSource1");
        Connection connection1 = dynamicDatasource.getConnection();
        Statement statement1 = connection1.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM user");
        while (resultSet1.next()) {
            long id = resultSet1.getLong("id");
            String name = resultSet1.getString("name");
            System.out.println(id + ": " + name);
        }

        datasourceContext.set("dataSource2");
        Connection connection2 = dynamicDatasource.getConnection();
        Statement statement2 = connection2.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM mybatis_demo.phone");
        while (resultSet2.next()) {
            long id = resultSet2.getLong("id");
            String name = resultSet2.getString("brand");
            System.out.println(id + ": " + name);
        }
    }

}
