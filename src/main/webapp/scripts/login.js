
//body记载完毕后自动调用的方法。 
//主处理
$(function(){
	//给登录按钮绑定单击处理
	$("#login").click(userLogin);
	//给注册按钮绑定单击处理
	$("#regist_button").click(userRegister);
	
});

//用户登录
function userLogin(){
		//清除原消息
		$("#count_span").html("");
		$("#pwd_span").html("");
		
		//获取请求参数
		var name = $("#count").val().trim();
		var pwd = $("#password").val().trim();
		//检测格式
		var ok = true;//标识是否通过校验  
		if(name == ""){
			$("#count_span").html("用户名为空");
			ok = false;
		}
		if(pwd ==""){
			$("#pwd_span").html("密码为空");
			ok = false;
		}
		//发送Ajax请求
		if(ok){//通过检测。
			$.ajax({
				url:"user/login.do",
				type:"post",
				data:{"name":name,"password":pwd},
				datatype:"json",
				success:function(result){
					if(result.status==0){
						//将用户信息写入Cookie
						var userId = result.data.cn_user_id;
						addCookie("userId",userId,2);
						console.log(userId);
						//成功->跳转到edit界面 
						window.location.href="edit.html";
					}else if(result.status==1){
						//账号不存在
						$("#count_span").html(result.msg);
					}else if(result.status==2){
						//密码错误 
						$("#pwd_span").html(result.msg);
					}
				},
				error:function(){
					alert("登录失败");
				}
			});
				
		}			
	}

//用户注册函数
function userRegister(){
	//清空提示信息
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	
	//获取请求参数
	var name =$("#regist_username").val().trim();
	var nick =$("#nickname").val().trim();
	var pwd =$("#regist_password").val().trim();
	var final_pwd = $("#final_password").val().trim();
	var ok = true;
	//检查格式
	//检测用户名
	if(name ==""){
		ok = false;
		$("#warning_1 span").html("用户名为空");
		$("#warning_1").show();
	}
	//检测密码
	if(pwd==""){
		ok = false;
		$("#warning_2 span").html("密码为空");
		$("#warning_2").show();
	}else if(pwd.length>0&&pwd.length<6){
		ok = false;
		$("#warning_2 span").html("密码长度小于6位");
		$("#warning_2").show();
	}
	//检测确认密码
	if(final_pwd == ""){
		ok = false;
		$("#warning_3 span").html("确认密码为空");
		$("#warning_3").show();
	}else if(final_pwd != pwd){
		ok = false;
		$("#warning_3 span").html("与密码不一致");
		$("#warning_3").show();
		
	}
	//通过减压发送ajax请求 
	if(ok){
		$.ajax({
			url:"user/add.do",
			type:"post",
			data:{"name":name,"nick":nick,"password":pwd},
			dataType:"json",
			success:function(result){
				if(result.status==1){//用户名已存在
					$("#warning_1 span").html("用户名已存在");
					$("#warning_1").show();
				}else if(result.status==0){//登录成功
					alert("注册成功");//提示登录成功 
					$("#back").click();//返回到登录界面
				}
			},
			error:function(){
				alert("注册失败");
			}
		});
	}
}
