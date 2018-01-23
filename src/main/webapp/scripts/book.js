/**添加笔记本**/
function sureAddBook(){
	//请求参数
	var bookName = $("#input_notebook").val();
	if(bookName ==""){
		$("#name_null").html("笔记本名为空");
		return ;
	}
	var userId = cookie("userId");
	console.log(userId);
	$.ajax({
		url:"book/add.do",
		type:"post",
		data:{"bookName":bookName,"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status == 0){//添加成功
				//获得data
				var book = result.data;
				var bookId = book.cn_notebook_id;
				//关闭对话框 
				closeAlertWindow();
				//创建一个笔记本li
				createBookLi(bookId,bookName);
				alert(result.msg);
				
			}
		},
		error:function(){alert("笔记本创建失败");}
	});
	
	
}

function loadUserBooks(){
	var userId = getCookie("userId");
	if(userId == null){
		window.location.href = "log_in.html";
	}else{//登录成功使用userId做其他操作
		//1.发送ajax请求加载笔记本列表 
		$.ajax({
			url:"book/loadbooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){//请求响应成功
					var books = result.data;
					for( var i=0;i<books.length;i++){
						//获取笔记本id
						var bookId = books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName = books[i].cn_notebook_name;
						//创建bookLi
						createBookLi(bookId,bookName);
						/*
						$("#pc_part_1").show();
						$("#pc_part_2").hide();
						$("#pc_part_3").hide();
						$("#pc_part_4").hide();
						$("#pc_part_5").hide();
						$("#pc_part_6").hide();
						$("#pc_part_8").hide();
						*/
					}
				}
			},
			error:function(){alert("加载笔记本列表失败");}
		});
	}
}

function createBookLi(bookId,bookName){
	//创建一个列表项<li>元素
	var sli = '<li class="online" >'+
					'<a >'+
					'<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
			   '</i> '+bookName+'</a></li>';
	var $li =$(sli);//将sli转换成jQuery对象
	$li.data("bookId",bookId);//将值与jQuery对象元素绑定(存值)
	//$li.data("bookId");从元素中取值
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
}

