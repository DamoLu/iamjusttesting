package com.plcs.web.common.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author User
 *  配置多数据源的时候，将下面的注释放开并修改相应的切入点以及数据源即可
 */
@Aspect
@Component // for auto scan
@Order(0)
public class DataSourceInterceptor {

    // 配置切换数据源的切入点，该方法所处的位置不能启动事务，否则切换失败
    // @Pointcut("execution(* com.plcs.web.wsxd.businessoperation.businessoperation.service.*(..))")
    // public void dataSourcePointCutSLAVE(){};
    //
    // @Before("dataSourcePointCutSLAVE()")
    // public void beforeSLAVE(JoinPoint jp) {
    // 	System.out.println("切换到从数据库实例方法执行前");
    //     DataSourceTypeManager.set(DataSourceEnum.SLAVE);
    // }
    //
    // @AfterReturning("dataSourcePointCutSLAVE()")
    // public void afterSLAVE(JoinPoint jp){
    // 	System.out.println("切换到从数据库实例方法返还后");
    // 	DataSourceTypeManager.set(DataSourceEnum.MASTER);
    // }
    //
    // @AfterThrowing("dataSourcePointCutSLAVE()")
    // public void afterThrowingSLAVE(JoinPoint jp){
    // 	System.out.println("切换到从数据库实例方法异常后");
    // 	DataSourceTypeManager.set(DataSourceEnum.MASTER);
    // }
}
