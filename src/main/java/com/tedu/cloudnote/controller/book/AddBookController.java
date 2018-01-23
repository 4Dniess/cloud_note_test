package com.tedu.cloudnote.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.service.BookService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddBookController {
	
	@Resource(name="bookService")
	BookService bookService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public  NoteResult<Book> execute(String userId,String bookName){
		return bookService.addBook(userId, bookName);
	}
	
}
