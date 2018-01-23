package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;

public interface NoteDao {
	public List<Map> findNotesByBookId(String bookId);
	public Note findById(String noteId);
	public int update(Note note);
	public int insert(Note note);
	public int updateStatus(String noteId);
	public int updateBookId(Note note);
	public List<Note> findNotes(Map params);
	public int dynamicUpdate(Note note);
	public int deleteNotes(String[] ids);
}
