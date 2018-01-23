function sureMoveNote(){
	//请求参数
	var bookId = $("#can option:selected").val();
	//当前选中的noteId
	var currentId = $("#book_ul .checked").parent().data("bookId");
	var noteId = $("#note_ul .checked").parent().data("noteId");
	console.log(noteId+"   "+bookId);
	if(bookId != currentId){
		$.ajax({
			url:"note/move.do",
			type:"post",
			data:{"bookId":bookId,"noteId":noteId},
			dataType:"json",
			success:function(result){
				//console.log(result);
				if(result.data == 0){
					$("note_ul checked").parent().remove();//将当前li删除
					alert("转移成功");
				}
			},
			error:function(){alert("转移笔记失败");}
		});
		closeAlertWindow();
	}else{
		return ;
	}
}

/**确认删除笔记**/
function sureDeleteNote(){
		
		closeAlertWindow();
		//请求参数
		var noteId = $("#note_ul .checked").parent().data("noteId");
		//发送ajax请求
		$.ajax({
			url:"note/delete.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
				//console.log(result);
				alert(result.msg);
				$("#note_ul .checked").parent().remove();
				}
			},
			error:function(){alert("删除笔记失败");}
		});
		return false;//阻止事件冒泡
}

//显示笔记菜单
function showNoteMenu(){
	$("#note_ul").on("click",".btn_slide_down",function(){
		//隐藏所有笔记菜单
		$("#note_ul li div").hide();
		//显示菜单 
		var $note_menu = $(this).parents("li").find("div");
		$note_menu.slideDown(1000);
		//设置li选中效果 
		$("#not_ul a").removeClass("checked");
		$(this).parent().addClass("checked");
		//组织li和body的click事件冒泡
		return false;//返回false就可以组织冒泡
	});
	//点击body范围，将笔记菜单隐藏
	$("body").click(function(){
		$("#note_ul li div").hide();
	});
}
/**添加笔记**/
function sureAddNote(){
	//请求参数
	var bookId = $("#book_ul a.checked").parent().data("bookId");
	var noteTitle = $("#input_note").val();
	var userId = cookie("userId");
	//格式检查 
	var ok = true;
	if(noteTitle == ""){
		$("#title_span").html("请输入笔记名");
		ok = false;
	}
	if(userId == null){
		window.location.href = "log_in.html";
		ok = false;
	}
	if(ok){
		//ajax请求
		$.ajax({
			url:"note/add.do",
			type:"post",
			data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
			dataType:"json",
			success:function(result){
				if(result.status == 0){//成功
					var note =result.data;
					//关闭窗口
					closeAlertWindow();
					//为note_ul 添加li
					console.log(note);
					var noteTitle = note.cn_note_title;
					var noteId = note.cn_note_id;
					console.log(noteId);
					addNoteLi(noteId,noteTitle);
					//修改编辑框标题
					//$("#input_note_title").val(noteTitle);
					//编辑框内容清空
					//um.setContent(note.cn_note_body);
				}
			},
			error:function(){
				alert("创建note失败");
			}
		});
	}
}

function updateNote(){
	//获取请求参数
	var noteBody = UM.getEditor('myEditor').getContent();
	var noteTitle = $("#input_note_title").val();
	var noteId = $("#note_ul .checked").parent().data("noteId");
	console.log(noteId);
	console.log(noteTitle);
	console.log(noteBody);
	
	//ajax异步加载
	$.ajax({
		url:"note/update.do",
		type:"post",
		data:{
			"noteBody":noteBody,
			"noteId":noteId,
			"noteTitle":noteTitle},
		dataType:"json",
		success:function(result){
			if(result.status ==0){
				$("#note_ul .checked").html('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>');
				alert("更新成功");
			}
		},
		error:function(){alert("保存笔记失败");}
	});
}

function loadNoteBooks(){
	
	//切换预览和编辑显示区
	$("#pc_part_5").hide();
	$("#pc_part_3").show();
	//设置选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	
	//获取请求参数
	var bookId = $(this).data("bookId");
	// alert(bookId);
	
	//发送ajax请求
	$.ajax({
		url:"note/loadnotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			
			if(result.status==0){//回馈成功
				var notes = result.data;
				$("#note_ul li").remove();//将父元素的子节点为li的节点移除
				//$(#note_ul).empty();//将父元素的子节点清空
				for(var i=0;i<notes.length;i++){
					//获取笔记标题
					var noteTitle = notes[i].cn_note_title;
					//获取笔记标题
					var noteId = notes[i].cn_note_id;
					addNoteLi(noteId,noteTitle);
				}
				
			}
		},
		error:function(){alert("加载笔记列表失败");}
	});
}

function addNoteLi(noteId,noteTitle){
	var sli ="";
	sli+= '<li class="online">';
	sli+= 	'<a >';
	sli+= 		'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+= 	'</a>';
	sli+= 	'<div class="note_menu" tabindex="-1">';
	sli+= 		'<dl>';
	sli+= 		'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+= 		'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+= 		'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+= 		'</dl>';
	sli+= 	'</div>';
	sli+= '</li>';

	var $sli = $(sli);
	$sli.data("noteId",noteId);
	$("#note_ul").append($sli);
	//设置选中效果
	//$("#note_ul a").removeClass("checked");
	//$(0.)
}
function loadNote(){
	//设置选中效果
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	var noteId = $(this).data("noteId");
	
	$.ajax({
		url:"note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status ==0){
				var note = result.data;
				var noteTitle = note.cn_note_title;
				var noteBody = note.cn_note_body;
				$("#input_note_title").val(noteTitle);
				var um = UM.getEditor('myEditor');
				um.setContent(noteBody);
			}
		},
		error:function(){
			alert("加载笔记失败");
		}
		
	});
	
}
