package com.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;

@Controller//扫描到spring
@RequestMapping("/user")
public class UserLoginController {
	@Resource//注入service对象
	private UserService service;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult<User> execute(String name ,String password){
		//调用Service处理登录。
		NoteResult<User> result = service.checkLogin(name, password);
//		System.out.println(result.getMsg());
		return result;
	}

}
