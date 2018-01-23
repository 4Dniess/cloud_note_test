package com.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component//扫描到Spring容器
@Aspect//将组件指定为切面组件
public class ExceptionBean {
	
	//e就是目标组件方法抛出 的异常
	@AfterThrowing(throwing="e",//throwing 写方法参数名（异常对象名）
			pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e){
		//将异常信息写入到文件中
//		System.out.println("发生了异常:"+e.getMessage());
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeStr= sdf.format(new Date());
			FileWriter fw = new FileWriter("E:\\note_error.log",true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("*****************************");
			pw.println("异常类型:"+e);
			pw.println("发生时间:"+timeStr);
			pw.println("************异常详情************");
			e.printStackTrace(pw);
			fw.close();
			pw.close();
			//利用pw对象写信息
		}catch(Exception ex){
			System.out.println("记录异常失败");
		}
	}
	
}
