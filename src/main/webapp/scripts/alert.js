/**弹出转移笔记对话框**/
function alertMoveNoteWindow(){
	

}
/**弹出转移笔记对话框**/
function alertMoveNoteWindow(){
	$("#can").load("alert/alert_move.html");
	$(".opacity_bg").show();
}
/**弹出删除笔记对话框**/
function alertDeleteNoteWindow(){
	//点击“+”弹出对话框
	$("#can").load("alert/alert_delete_note.html");
	//显示灰色对话框背景(使用class="opacity_bg")选择器
	$(".opacity_bg").show();
}

//关闭对话框
function closeAlertWindow(){
	$("#can").html("");//清空加载的对话框
	$(".opacity_bg").hide();//清空背景色
}
//弹出创建笔记对话框
function alertAddBookWindow(){
	//弹出添加笔记本对话框
	$("#can").load("alert/alert_notebook.html");
	//显示灰色对话框背景(使用class="opacity_bg")选择器
	$(".opacity_bg").show();
}
//弹出创建笔记对话框
function alertAddNoteWindow(){
	//判断是否有笔记本选中
	var $li =  $("#book_ul a.checked").parent();
	if($li.length == 0){
		alert("请选择笔记本");
	}else{
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
}