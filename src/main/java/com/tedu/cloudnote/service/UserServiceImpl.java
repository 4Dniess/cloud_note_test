package com.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

@Service("userService")//扫描到spring容器
public class UserServiceImpl implements UserService {

	@Resource//将UserDao注入到userDao属性中。
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name, String pwd) {
		NoteResult<User> result = new NoteResult<User>();
		System.out.println(name+"||"+pwd);
		User user = userDao.findByName(name);
		if(user == null){
			result.setMsg("用户名不存在");
			result.setStatus(1);
			return result;
		}
		//测试密码
		String md5Pwd = NoteUtil.md5(pwd);//将用户输入的密码进行加密。
		if(!user.getCn_user_password().equals(md5Pwd)){
			result.setMsg("密码错误");
			result.setStatus(2);
			return result;
		}
		result.setStatus(0);
		result.setData(user);
		result.setMsg("登录成功");
		System.out.println(user);
		return result;
	}

	public NoteResult<Object> addUser(String name, String nick, String password) {
		
		//构建返回结果
		NoteResult<Object> result = new NoteResult<Object>();
		//执行用户名检测
		User hasuser = userDao.findByName(name);
		if(hasuser !=null){
			result.setMsg("用户名已存在");
			result.setStatus(1);
			return result;
		}
		
		User user = new User();
		user.setCn_user_name(name);//设置用户名
		user.setCn_user_nick(nick);//设置昵称
		//密码加密之后保存
		String md5Pwd = NoteUtil.md5(password);
		user.setCn_user_password(md5Pwd);
		String id = NoteUtil.createId();
		user.setCn_user_id(id);
		userDao.save(user);//保存user
		
		result.setMsg("注册成功");
		result.setStatus(0);
		return result;
	}


	
}
