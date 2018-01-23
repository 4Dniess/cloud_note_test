package cloud_note.test.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;

public class TestUserDao {
	
	//≤‚ ‘dbcp
	@Test
	public void test1() throws SQLException{
		String[] conf = {
				"conf/spring-mybatis.xml",
				"conf/spring-mvc.xml"
		};
				
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
//		BasicDataSource dbcp = ac.getBean("dbcp",BasicDataSource.class);
//		System.out.println(dbcp.getConnection());
//		
//		SqlSessionFactory ssf = ac.getBean("ssf",SqlSessionFactory.class);
//		System.out.println(ssf.openSession());
		
		UserDao dao = ac.getBean("userDao",UserDao.class);
		User user = dao.findByName("demo");
		System.out.println(user);
		
	}
}
