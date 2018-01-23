package com.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteService {
	public  NoteResult<List<Map>> loadBookNotes(String bookId);
	public  NoteResult<Note> loadNote(String noteId);
	public  NoteResult<Object> updateNote(String noteId ,String noteTitle ,String noteBody);
	public  NoteResult<Note> addNote(String userId,String bookId,String noteTitle );
	public  NoteResult<Object> deleteNote(String noteId);
	public  NoteResult<Object> updateBookId(String noteId ,String bookId);
	public 	NoteResult<List<Note>> loadManager(String userId,String title,String status,String begin ,String end);
}
