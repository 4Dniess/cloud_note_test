package com.tedu.cloudnote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//封装共同业务
@Component//扫描
@Aspect//等价于<aop:aspect>
public class LoggerBean {
	
	//等价于<aop:before>定义
	//在Controller方法执行前，限制性logController处理
	@Before("within(com.tedu.cloudnote.controller..*)")
	public void logController(){
		System.out.println("进入Controller处理请求");
	}
	
	@Before("execution(* com.tedu.cloudnote.service.UserServiceImpl.*(..))")
	public void logService(){
		System.out.println("进入Service处理业务");
	}
}
