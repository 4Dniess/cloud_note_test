package com.tedu.cloudnote.service;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;

public interface UserService {
	// ��׼-->public �������� (NoteResult<����>) ����������������������壩;
	public NoteResult<User> checkLogin(String name , String pwd);
	public NoteResult<Object> addUser(String name,String nick,String password);
}
