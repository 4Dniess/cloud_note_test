package cloud_note.test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteUtil;

import test.TestBase;

public class TestBookDao extends TestBase{
	
	private UserDao userDao;
	private BookDao noteDao;

	@Before
	public void init(){
		userDao = super.getContext().getBean("userDao",UserDao.class);
		noteDao = super.getContext().getBean("bookDao",BookDao.class);
	}
	
	
	@Test
	//”√¿¥≤‚ ‘NoteDaoµƒfindByUserId;
	public void test1(){
		 User user = userDao.findByName("demo");
		 String id = user.getCn_user_id();
		 System.out.println(id);
		 List<Book> notes = noteDao.findByUserId(id);
		 System.out.println(notes.size());
		 for (Book noteBook : notes) {
			System.out.println(noteBook);
		}
	}
	
	@Test
	public void test2(){
		Book book = new Book();
		book.setCn_user_id("48595f52-b22c-4485-9244-f4004255b972");
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name("bbb");
		int status = noteDao.insert(book);
		System.out.println(status);
	}
	
}
