package com.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component//ɨ�赽Spring����
@Aspect//�����ָ��Ϊ�������
public class ExceptionBean {
	
	//e����Ŀ����������׳� ���쳣
	@AfterThrowing(throwing="e",//throwing д�������������쳣��������
			pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e){
		//���쳣��Ϣд�뵽�ļ���
//		System.out.println("�������쳣:"+e.getMessage());
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeStr= sdf.format(new Date());
			FileWriter fw = new FileWriter("E:\\note_error.log",true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("*****************************");
			pw.println("�쳣����:"+e);
			pw.println("����ʱ��:"+timeStr);
			pw.println("************�쳣����************");
			e.printStackTrace(pw);
			fw.close();
			pw.close();
			//����pw����д��Ϣ
		}catch(Exception ex){
			System.out.println("��¼�쳣ʧ��");
		}
	}
	
}
