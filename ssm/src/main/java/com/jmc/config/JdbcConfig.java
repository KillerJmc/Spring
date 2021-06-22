package com.jmc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.*;
import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
@MapperScan("com.jmc.mapper")
public class JdbcConfig {
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        var dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
         需要导入spring-jdbc的包，mybatis底层要用到这个事务管理器，然后这个可写可不写

         public TransactionManager getTransactionManager() {
            return new DataSourceTransactionManager(dataSource);
         }
     */

    /**
     * 整合mybatis<br>
     * 把mapper放进容器中
     */
    @Bean
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        var sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        /*
            手动添加mapper的xml多个文件（stuMapper.xml位于resources目录下）

            sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("stuMapper.xml"),
                    new ClassPathResource("xxx.xml"));

        */
        sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("stuMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}