package com.ivoronline.springboot_db_transaction_proxy_aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Connection;

@Aspect
@Component
public class ProxyConfig {

  //PROPERTIES
  @Autowired Connection connection;

  @Pointcut("within(com..service.*)")
  public void logMethodPointCut() { }
  
  //=========================================================================================================
  // @AROUND
  //=========================================================================================================
  @Around("logMethodPointCut()")
  public void logAroundMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
  
    //TRANSACTION
    try {

      //START TRANSACTION
      connection.setAutoCommit(false);

      //CALL SERVICE
      proceedingJoinPoint.proceed();

      //COMMIT TRANSACTION
      connection.commit();

    }
    catch (Exception e) {
      //ROLLBACK TRANSACTION
      connection.rollback();
    }
    finally {
      connection.close();
    }

  }
  
}

