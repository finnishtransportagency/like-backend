package fi.livi.like.backend.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Database related configurations
 */
@Configuration
@MapperScan("fi.livi.like.backend.data.mapper")
public class DataConfig {

    @Value("${c3p0.max_size}")
    private int maxSize;
 
    @Value("${c3p0.min_size}")
    private int minSize;
 
    @Value("${c3p0.acquire_increment}")
    private int acquireIncrement;
 
    @Value("${c3p0.idle_test_period}")
    private int idleTestPeriod;
 
    @Value("${c3p0.max_statements}")
    private int maxStatements;
 
    @Value("${c3p0.max_idle_time}")
    private int maxIdleTime;
 
    @Value("${c3p0.url}")
    private String url;
 
    @Value("${c3p0.username}")
    private String username;
 
    @Value("${c3p0.password}")
    private String password;
 
    @Value("${c3p0.driverClassName}")
    private String driverClassName;

    /**
     * Data source connection configuration
     * @return Data source connection
     */
    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMaxPoolSize(maxSize);
        dataSource.setMinPoolSize(minSize);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setIdleConnectionTestPeriod(idleTestPeriod);
        dataSource.setMaxStatements(maxStatements);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        dataSource.setUser(username);
        dataSource.setDriverClass(driverClassName);
        return dataSource;
    }
    
    /**
     * Create transaction manager
     * @return Created transaction manager
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Create SQL session factory
     * @return Created SQL session factory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sessionFactory.setTypeAliasesPackage("fi.livi.like.backend.data.dto");
        return sessionFactory;
    }
}
