package com.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class MoveNoteController {
	
	@Resource(name="noteService")
	private NoteService service;
	
	@RequestMapping("/move.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId, String bookId){
		
		return service.updateBookId(noteId, bookId);
	}

}
