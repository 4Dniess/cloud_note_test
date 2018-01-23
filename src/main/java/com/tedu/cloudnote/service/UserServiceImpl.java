package com.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

@Service("userService")//ɨ�赽spring����
public class UserServiceImpl implements UserService {

	@Resource//��UserDaoע�뵽userDao�����С�
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name, String pwd) {
		NoteResult<User> result = new NoteResult<User>();
		System.out.println(name+"||"+pwd);
		User user = userDao.findByName(name);
		if(user == null){
			result.setMsg("�û���������");
			result.setStatus(1);
			return result;
		}
		//��������
		String md5Pwd = NoteUtil.md5(pwd);//���û������������м��ܡ�
		if(!user.getCn_user_password().equals(md5Pwd)){
			result.setMsg("�������");
			result.setStatus(2);
			return result;
		}
		result.setStatus(0);
		result.setData(user);
		result.setMsg("��¼�ɹ�");
		System.out.println(user);
		return result;
	}

	public NoteResult<Object> addUser(String name, String nick, String password) {
		
		//�������ؽ��
		NoteResult<Object> result = new NoteResult<Object>();
		//ִ���û������
		User hasuser = userDao.findByName(name);
		if(hasuser !=null){
			result.setMsg("�û����Ѵ���");
			result.setStatus(1);
			return result;
		}
		
		User user = new User();
		user.setCn_user_name(name);//�����û���
		user.setCn_user_nick(nick);//�����ǳ�
		//�������֮�󱣴�
		String md5Pwd = NoteUtil.md5(password);
		user.setCn_user_password(md5Pwd);
		String id = NoteUtil.createId();
		user.setCn_user_id(id);
		userDao.save(user);//����user
		
		result.setMsg("ע��ɹ�");
		result.setStatus(0);
		return result;
	}


	
}
