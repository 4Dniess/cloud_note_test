package com.tedu.cloudnote.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao dao;
	
	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		//���ݱʼ�ID��ѯ�ʼ���Ϣ
		List<Map> notes = dao.findNotesByBookId(bookId);
		//�������ؽ��
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		result.setData(notes);
		result.setMsg("��ѯ�ɹ�");
		result.setStatus(0);
		return result;
	}

	public NoteResult<Note> loadNote(String noteId) {
		
		Note note = dao.findById(noteId);
		NoteResult<Note> result = new NoteResult<Note>();
		result.setData(note);
		result.setStatus(0);
		result.setMsg("���رʼǳɹ�");
		return result;
	}


	public NoteResult<Object> updateNote(String noteId, String noteTitle, String noteBody) {

		Note note = new Note();
		note.setCn_note_title(noteTitle);
		note.setCn_note_id(noteId);
		note.setCn_note_body(noteBody);
		int status = dao.update(note);
		
		NoteResult<Object> result = new NoteResult<Object>();
		if(status >0){
			result.setMsg("���³ɹ�");
			result.setStatus(0);
		}else{
			result.setStatus(1);
			result.setMsg("����ʧ��");
		}
		return result;
	}

	public NoteResult<Note> addNote(String userId, String bookId, String noteTitle) {
		
		//����note����
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_note_title(noteTitle);
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_type_id("1");//����Ϊnormal���͵ıʼ�
		note.setCn_note_status_id("1");//����Ϊnormal���͵ıʼ�
		note.setCn_note_body("");//�ʼ���������Ϊ�գ���ֹjs�ڲ����ֳ�ͻ 
		
		//����noteResult
		NoteResult<Note> result = new NoteResult<Note>();
		int status = dao.insert(note);
		if(status >0){
			result.setData(note);
			result.setMsg("�ʼǴ����ɹ�");
			result.setStatus(0);
		}else{
			result.setMsg("�ʼǴ���ʧ��");
			result.setStatus(1);
		}
		
		return result;
	}

	public NoteResult<Object> deleteNote(String noteId) {
		
		int status = dao.updateStatus(noteId);
		NoteResult<Object> result = new NoteResult<Object>();
		if(status>0){
			result.setMsg("��ӵ�����վ�ɹ�");
			result.setStatus(0);
		}else{
			result.setMsg("��ӵ�����վʧ��");
			result.setStatus(1);
		}
		return result;
	}

	public NoteResult<Object> updateBookId(String noteId, String bookId) {
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		NoteResult<Object> result = new NoteResult<Object>();
		int status = dao.updateBookId(note);
		if(status >0){
			result.setMsg("�޸ĳɹ�");
			result.setStatus(0);
		}else{
			result.setMsg("�޸�ʧ��");
			result.setStatus(1);
			System.out.println("aaaaaaaaaa");
		}
		return result;
	}

	public NoteResult<List<Note>> loadManager(String userId,String title, String status, String begin, String end) {
		
		System.out.println(userId+":"+title+":"+status+":"+begin+":"+end);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		//���жϱ������������ǿ�ʱ��Ч
		if(title!=null&&!"".equals(title)){
			params.put("title", "%"+title+"%");
		}
		//�ж�״̬���������ǡ�ȫ����ʱ��Ч
		if(status!=null&&!"0".equals(status)){
			params.put("status", status);
		}
		//�ж��������������ǿ�ʱ��Ч
		if(begin!=null&&!"".equals(begin)){
			Date date = Date.valueOf(begin);
			params.put("begin", date.getTime());
		}
		//�ж���ֹ�������������ǿ�ʱ��Ч
		if(end!=null &&!"".equals(end)){
			Date date = Date.valueOf(end);
			params.put("end", date.getTime());
		}
		Set<String> key = params.keySet();
		for (String str: key) {
			System.out.println(str+":"+params.get(str));
		}
		//ִ�в�ѯ
		List<Note> list = dao.findNotes(params);
//		for (Note note : list) {
//			System.out.println(note.getCn_note_title());
//		}
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		result.setData(list);
		result.setMsg("�������");
		result.setStatus(0);
		return result;
	}
}
