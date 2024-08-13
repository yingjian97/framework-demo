package site.yingjian.mybatis.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 自定义数据库厂商标识
 */
@Slf4j
@Component
public class MyDatabaseIdProvider implements DatabaseIdProvider {
    private static final String HOLOGRES = "Hologres";

    private static final String GAUSSDB = "GaussDB";

    @Value("${database-id-provider}")
    private String databaseIdProvider;

    @Override
    public void setProperties(Properties p) {
        DatabaseIdProvider.super.setProperties(p);
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        log.info("DatabaseIdProvider: {}", databaseIdProvider);
        String dbAlias = "";
        switch (databaseIdProvider) {
            case HOLOGRES:
                dbAlias = HOLOGRES;
                break;
            case GAUSSDB:
                dbAlias = GAUSSDB;
                break;
            default:
                break;
        }
        return dbAlias;
    }
}
