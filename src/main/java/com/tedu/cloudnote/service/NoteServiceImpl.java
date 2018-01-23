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
		//根据笔记ID查询笔记信息
		List<Map> notes = dao.findNotesByBookId(bookId);
		//创建返回结果
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		result.setData(notes);
		result.setMsg("查询成功");
		result.setStatus(0);
		return result;
	}

	public NoteResult<Note> loadNote(String noteId) {
		
		Note note = dao.findById(noteId);
		NoteResult<Note> result = new NoteResult<Note>();
		result.setData(note);
		result.setStatus(0);
		result.setMsg("加载笔记成功");
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
			result.setMsg("更新成功");
			result.setStatus(0);
		}else{
			result.setStatus(1);
			result.setMsg("更新失败");
		}
		return result;
	}

	public NoteResult<Note> addNote(String userId, String bookId, String noteTitle) {
		
		//创建note对象
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_note_title(noteTitle);
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_type_id("1");//设置为normal类型的笔记
		note.setCn_note_status_id("1");//设置为normal类型的笔记
		note.setCn_note_body("");//笔记内容设置为空，防止js内部出现冲突 
		
		//构建noteResult
		NoteResult<Note> result = new NoteResult<Note>();
		int status = dao.insert(note);
		if(status >0){
			result.setData(note);
			result.setMsg("笔记创建成功");
			result.setStatus(0);
		}else{
			result.setMsg("笔记创建失败");
			result.setStatus(1);
		}
		
		return result;
	}

	public NoteResult<Object> deleteNote(String noteId) {
		
		int status = dao.updateStatus(noteId);
		NoteResult<Object> result = new NoteResult<Object>();
		if(status>0){
			result.setMsg("添加到回收站成功");
			result.setStatus(0);
		}else{
			result.setMsg("添加到回收站失败");
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
			result.setMsg("修改成功");
			result.setStatus(0);
		}else{
			result.setMsg("修改失败");
			result.setStatus(1);
			System.out.println("aaaaaaaaaa");
		}
		return result;
	}

	public NoteResult<List<Note>> loadManager(String userId,String title, String status, String begin, String end) {
		
		System.out.println(userId+":"+title+":"+status+":"+begin+":"+end);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		//批判断标题条件，不是空时有效
		if(title!=null&&!"".equals(title)){
			params.put("title", "%"+title+"%");
		}
		//判断状态条件，不是“全部”时有效
		if(status!=null&&!"0".equals(status)){
			params.put("status", status);
		}
		//判断日期条件，不是空时有效
		if(begin!=null&&!"".equals(begin)){
			Date date = Date.valueOf(begin);
			params.put("begin", date.getTime());
		}
		//判断终止日期条件，不是空时有效
		if(end!=null &&!"".equals(end)){
			Date date = Date.valueOf(end);
			params.put("end", date.getTime());
		}
		Set<String> key = params.keySet();
		for (String str: key) {
			System.out.println(str+":"+params.get(str));
		}
		//执行查询
		List<Note> list = dao.findNotes(params);
//		for (Note note : list) {
//			System.out.println(note.getCn_note_title());
//		}
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		result.setData(list);
		result.setMsg("搜索完毕");
		result.setStatus(0);
		return result;
	}
}
