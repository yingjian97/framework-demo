package site.yingjian.datasource.demo.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolTest {

    /**
     * 数据源：ComboPooledDataSource
     * 数据库连接线程池：BasicResourcePool
     * 每个数据源都是独立的数据库连接线程池
     * ComboPooledDataSource.getConnection() -> C3P0PooledDataSource.getConnection() -> BasicResourcePool.getResource() ->
     * NewPooledConnection (create or reuse) -> NewPooledConnection.getConnection() -> NewProxyConnection (return)
     */

    public static void main(String[] args) throws PropertyVetoException, SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setUser("root");
        dataSource.setPassword("19970918lkdys_A");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test_db");
        dataSource.setInitialPoolSize(1);
        dataSource.setMaxPoolSize(1);
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName()); // com.mchange.v2.c3p0.impl.NewProxyConnection
        Object inner = getInner(connection);
        System.out.println(inner.getClass().getName()); // com.mysql.cj.jdbc.ConnectionImpl

//        Connection connection1 = dataSource.getConnection();
//        Object inner1 = getInner(connection1);
//        connection1.close();
//        Connection connection2 = dataSource.getConnection();
//        Object inner2 = getInner(connection2);
//        System.out.println(connection1.getClass().getName());
//        System.out.println(connection2.getClass().getName());
//        System.out.println(connection1 == connection2);
//        System.out.println(inner1.getClass().getName());
//        System.out.println(inner2.getClass().getName());
//        System.out.println(inner1 == inner2);


    }

    public static Object getInner(Object connection) {
        Object result = null;
        Field field = null;
        try {
            field = connection.getClass().getDeclaredField("inner");
            field.setAccessible(true);
            result = field.get(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
