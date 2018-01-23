package cloud_note.test.service;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.regexp.internal.recompile;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

import test.TestBase;

public class TestNoteService extends TestBase {
	
	private NoteService service;
	
	@Before
	public void init(){
		service = super.getContext().getBean("noteService" ,NoteService.class);
	}
	
	@Test
	public void test1(){
		NoteResult<Object> result = service.updateNote("2a4ca5bb-3073-4194-9d4e-5db0ec5c0998", "dangpang", "123");
		System.out.println(result.getStatus());
	}
}
