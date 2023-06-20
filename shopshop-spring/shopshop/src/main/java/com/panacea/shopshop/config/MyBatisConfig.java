//package com.panacea.shopshop.config;
//
//
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.TransactionManager;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
//@Configuration
//public class MyBatisConfig implements TransactionManagementConfigurer {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        // 配置其他属性，例如mapper位置等
//        return sessionFactory;
//    }
//
//    @Override
//    public TransactionManager annotationDrivenTransactionManager() {
//        return null;
//    }
//
//    // 其他配置...
//}
