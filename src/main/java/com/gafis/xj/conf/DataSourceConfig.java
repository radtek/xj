package com.gafis.xj.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 数据源
 */
@Configuration
@MapperScan(basePackages = "com.gafis.xj.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
	@Value("${datasource.url}")  
    private String url;
	
	@Value("${datasource.username}")  
    private String username;
	
    @Value("${datasource.password}")  
    private String password;
    
    @Value("${datasource.driverClassName}")  
    private String driverClassName;
    
    @Value("${mybatis.mapper-locations}")
    private String mybatisMapperLocations;
	
	@Bean(name = "dataSource")  
    @Primary  
    public DataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);  
        dataSource.setJdbcUrl(url);  
        dataSource.setUsername(username);  
        dataSource.setPassword(password);  
        return dataSource;  
    }
	
	@Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisMapperLocations));
        return bean.getObject();
    }
	
	@Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	 
}
