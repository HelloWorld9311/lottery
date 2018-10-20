package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Prject: cloud-alex
 * @Package: com.bqmart.config
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/10/24 21:37
 */
@Configuration
@MapperScan(basePackages = "cn.bqmart.manager", sqlSessionFactoryRef = "sunSqlSessionFactory")
@SuppressWarnings("all")
@EnableTransactionManagement
public class DruidDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.sun")
    public Properties sunDataSourceProperties() {
        return new Properties();
    }

    @Bean
    @Primary
    public DataSource sunDataSource(@Autowired @Qualifier("sunDataSourceProperties") Properties properties) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setDbType(properties.getProperty("dbType"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setInitialSize((Integer) properties.get("initialSize"));
        dataSource.setMinIdle((Integer) properties.get("minIdle"));
        dataSource.setMaxActive((Integer) properties.get("maxActive"));
        dataSource.setMaxWait(Integer.parseInt(properties.get("maxWait").toString()));
        dataSource.setDriverClassName(properties.getProperty("driver-class-name"));

        dataSource.setTimeBetweenConnectErrorMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);//自动释放链接时间1800秒


        // SQL监控配置
        dataSource.setFilters("stat,wall,log4j");
        dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        return dataSource;
    }

    @Bean
    @Primary
    public SqlSessionFactory sunSqlSessionFactory(@Qualifier("sunDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mybatis的配置  替代mybatis-config.xml文件
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        // 插件配置
        // 分页插件
        // 引入pageHelper spring boot 集成之后就有默认配置了, 不需要做额外插件设置了
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("dialect", "com.github.pagehelper.dialect.helper.MysqlDialect");
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "pageNum=page;pageSize=size;");
//        pageHelper.setProperties(properties);
//        bean.setPlugins(new Interceptor[]{pageHelper});

        return bean.getObject();
    }


    @Bean
    @Primary
    public SqlSessionTemplate sunSqlSessionTemplate(@Qualifier("sunSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }
}
