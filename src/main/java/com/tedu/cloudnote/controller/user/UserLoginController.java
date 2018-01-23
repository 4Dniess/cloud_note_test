package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller//ɨ�赽spring
@RequestMapping("/user")
public class UserLoginController {
	@Resource//ע��service����
	private UserService service;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult<User> execute(String name ,String password){
		//����Service�����¼��
		NoteResult<User> result = service.checkLogin(name, password);
//		System.out.println(result.getMsg());
		return result;
	}

}
