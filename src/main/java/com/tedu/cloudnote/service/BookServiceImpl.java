package com.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource
	private BookDao bookDao;
	
	public NoteResult<List<Book>> loadBooks(String userId) {
		
		//���û�id��ѯ�ʼǱ���Ϣ
		List<Book> books = bookDao.findByUserId(userId);
		//�������ؽ��
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		result.setStatus(0);
		result.setMsg("��ѯ�ʼǱ��ɹ�");
		result.setData(books);
		return result;
	}

	public NoteResult<Book> addBook(String userId, String bookName) {
		
		//����Note
		Book book = new Book();
		book.setCn_user_id(userId);
		book.setCn_notebook_name(bookName);
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		Timestamp cn_notebook_createtime = new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(cn_notebook_createtime );
		book.setCn_notebook_type_id("5");//����normal���͵ı�
		
		//���book
		int status =  bookDao.insert(book);
		NoteResult<Book> result = new NoteResult<Book>();
		if(status>0){
			result.setMsg("�����ɹ�");
			result.setStatus(0);
			result.setData(book);
		}else{
			result.setMsg("����ʧ��");
			result.setStatus(1);
		}
		return result;
	}

}
