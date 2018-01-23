package cloud_note.test.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Note;

import test.TestBase;

public class TestNoteDao extends TestBase {
	
	private NoteDao noteDao;
	@Before
	public void init(){
		noteDao = super.getContext().getBean("noteDao",NoteDao.class);	
	}
	
	@Test
	public void test1(){
		  List<Map> notes = noteDao.findNotesByBookId("2a4ca5bb-3073-4194-9d4e-5db0ec5c0998");
		  for (Map map : notes) {
			  System.out.println(map.getClass().getName());
			System.out.println(map.get("cn_note_id")+"||"+map.get("cn_note_title"));
		}
	}
	@Test
	public void test2(){
		Note note = noteDao.findById("2b0aa581-13fb-4e2c-afd7-d5fc476320f4");
		System.out.println(note);
		//更新note
		note.setCn_note_title("dangpang");
	}
	
	@Test
	/**
	 *test for insert 
	 */
	public void test3(){
		Note note = new Note();
		note.setCn_note_id("jlsadkjlkjasdjgl");
		int status = noteDao.insert(note);
		System.out.println(status);
	}
	
	@Test
	public void test4(){
		int status =  noteDao.updateStatus("326f17e8-ff04-4a79-b33b-ae4bb3bb5f1b");
		System.out.println(status);
	}
	@Test
	public void test5(){
		Note note  = new Note();
		note.setCn_note_id("9598cb72-4215-4b58-b7c6-b51ed0b7b3b4");
		note.setCn_notebook_id("12119052-874c-4341-b85d-6529e171ed83");
		int status = noteDao.updateBookId(note);
		System.out.println(status);
	}

	@Test
	public void test6(){
		//创建查询参数
		Map<String,Object> params=new HashMap<String, Object>() ;
		//params.put("status", 1);
		params.put("userId", "48595f52-b22c-4485-9244-f4004255b972");
		params.put("title", "%"+"测试"+"%");
		//Date begin = Date.valueOf("2017-06-01");
		//params.put("begin", begin.getTime());
//		params.put("end", value);
		//执行查询
		List<Note> nontes = noteDao.findNotes(params);
		for (Note note : nontes) {
			System.out.println(note.getCn_note_title());
		}
		System.out.println(nontes.size());
	}
	
	@Test
	public void test7(){
		String[] ids ={
				"334d6521-bebf-4f1a-8239-2128a4e828cc",
				"4949cfde-1766-4d9b-a933-4f1c8365b75b",
				"65731b69-409c-47b9-866b-b283840b2723"	
		};
		int num = noteDao.deleteNotes(ids);
		System.out.println(num);
	}
	
}
