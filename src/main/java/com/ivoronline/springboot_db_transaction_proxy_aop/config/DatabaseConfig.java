package com.ivoronline.springboot_db_transaction_proxy_aop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@ComponentScan("com")
@EnableAspectJAutoProxy

public class DatabaseConfig {

  //PROPERTIES
  @Autowired private DataSource dataSource;

  //=========================================================================================================
  // CONNECTION
  //=========================================================================================================
  @Bean
  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

}
