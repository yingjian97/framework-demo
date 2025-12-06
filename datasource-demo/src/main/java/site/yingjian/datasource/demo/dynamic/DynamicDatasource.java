package site.yingjian.datasource.demo.dynamic;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Component
@Primary
public class DynamicDatasource extends AbstractRoutingDataSource {

    public static final ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    @Resource
    DataSource dataSource1;

    @Resource
    DataSource dataSource2;

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return datasourceContext.get();
    }


    @Override
    public void afterPropertiesSet() {
        // 设置所有数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource1", dataSource1);
        targetDataSources.put("dataSource2", dataSource2);
        super.setTargetDataSources(targetDataSources);

        // 设置默认数据源
        super.setDefaultTargetDataSource(dataSource1);

        super.afterPropertiesSet();
    }
}
