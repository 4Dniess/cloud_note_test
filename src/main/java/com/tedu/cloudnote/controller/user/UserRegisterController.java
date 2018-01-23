package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
	
	@Resource
	private UserService service;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String name ,String nick ,String password){
		NoteResult<Object> result = service.addUser(name, nick, password);
		System.out.println(result.getMsg()+"||"+result.getStatus());
		return result;
	}
	
}
