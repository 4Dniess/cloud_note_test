package com.tedu.cloudnote.controller.book;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.service.BookService;
import com.tedu.cloudnote.util.NoteResult;

@Controller//É¨Ãè
@RequestMapping("/book")
public class LoadBookController {
	
	@Resource//×¢Èë
	private BookService bookService;
	
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult<List<Book>> execute(String userId){
		 NoteResult<List<Book>> result = bookService.loadBooks(userId);
		 return result;
	}
	
}
