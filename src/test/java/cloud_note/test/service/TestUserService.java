package cloud_note.test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;import com.sun.org.apache.regexp.internal.recompile;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

public class TestUserService {
	
	private UserService service;
	@Before
	public void init(){
		String[] conf = {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		service = ac.getBean("userService",UserService.class);
	}
	
	@Test//����-1
	public void test1(){
		NoteResult<User> result = service.checkLogin("��С��", "123");
		System.out.println(result.getStatus()+"||"+result.getMsg()+"||"+result.getData());
	}
	@Test//����-2
	public void test2(){
		NoteResult<User> result = service.checkLogin("demo", "123");
		System.out.println(result.getStatus()+"||"+result.getMsg()+"||"+result.getData());
	}
	@Test//����-3
	public void test3(){
		NoteResult<User> result = service.checkLogin("demo", "123456");
		System.out.println(result.getStatus()+"||"+result.getMsg()+"||"+result.getData());
	}
	
	@Test
	//ע������-1=�û�����demo,�ǳ�DEMO������123abc
	//Ԥ�ڷ���status=1��msg�ѱ�ռ��
	public void test4(){
		NoteResult<Object> result = service.addUser("demo", "aaa", "123abc");
		System.out.println(result.getStatus()+"||"+result.getMsg());
	}
	@Test
	//ע������-3=�û�����tedu,�ǳ�TEDU������123123
	//Ԥ�ڷ���status=0��msg�ѱ�ռ��
	public void test5(){
		NoteResult<Object> result = service.addUser("tedu", "TEDU", "123123");
		System.out.println(result.getStatus()+"||"+result.getMsg());
	}
}
