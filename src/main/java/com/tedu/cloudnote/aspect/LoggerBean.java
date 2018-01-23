package com.tedu.cloudnote.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//��װ��ͬҵ��
@Component//ɨ��
@Aspect//�ȼ���<aop:aspect>
public class LoggerBean {
	
	//�ȼ���<aop:before>����
	//��Controller����ִ��ǰ��������logController����
	@Before("within(com.tedu.cloudnote.controller..*)")
	public void logController(){
		System.out.println("����Controller��������");
	}
	
	@Before("execution(* com.tedu.cloudnote.service.UserServiceImpl.*(..))")
	public void logService(){
		System.out.println("����Service����ҵ��");
	}
}
