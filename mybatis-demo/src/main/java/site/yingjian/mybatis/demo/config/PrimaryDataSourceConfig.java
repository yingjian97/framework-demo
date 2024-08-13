package site.yingjian.mybatis.demo.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Slf4j
@MapperScan(value = "site.yingjian.mybatis.demo.mapper.primary", sqlSessionFactoryRef = "primarySqlSessionFactory", sqlSessionTemplateRef = "primarySqlSessionTemplate")
@Configuration
public class PrimaryDataSourceConfig {
    // 自定义数据库厂商标识
    @Resource
    private MyDatabaseIdProvider myDatabaseIdProvider;

    @Value("${spring.datasource.primary.jdbc-url}")
    private String jdbcUrl;

    /**
     * 数据源
     */
    @Primary
    @Bean("primaryDataSource")
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource dataSource() {
        log.info("primary jdbc-url: {}", jdbcUrl);
        return DataSourceBuilder.create().build();
    }

    /**
     * 插件
     */
    @Primary
    @Bean("primaryMybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * 配置Spring事务管理
     */
    @Primary
    @Bean("primaryDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionFactory
     */
    @Primary
    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource,
                                               @Qualifier("primaryMybatisPlusInterceptor") MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        // SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        // 配置数据源
        factoryBean.setDataSource(dataSource);
        // 配置插件
        factoryBean.setPlugins(mybatisPlusInterceptor);
        // 配置MyBatis事务管理
        // factoryBean.setTransactionFactory(new ManagedTransactionFactory());
        // 配置XML映射器配置文件路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/primary/*.xml"));
        // 配置实体类路径
        factoryBean.setTypeAliasesPackage("site.yingjian.mybatis.demo.pojo.primary");
        // 配置数据库厂商标识
        /*
        Properties properties = new Properties();
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("PostgreSQL", "pgsql");
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        databaseIdProvider.setProperties(properties);
        factoryBean.setDatabaseIdProvider(databaseIdProvider);
         */
        factoryBean.setDatabaseIdProvider(myDatabaseIdProvider);
        // 配置日志
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);

        return factoryBean.getObject();
    }

    /**
     * SqlSessionTemplate
     */
    @Primary
    @Bean("primarySqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
