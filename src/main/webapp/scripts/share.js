/**share.js**/
function searchMore(){
	page= page+1;
	var likeTitle = $("#search_note").val().trim();
	loadPageShare(likeTitle,page);			
}
function sureSearchShare(event){
	var keyCode = event.keyCode;
	if(keyCode == 13){
		//请求参数 
		page = 1;
		$("#search_ul li").remove();
		var likeTitle = $("#search_note").val().trim();
		loadPageShare(likeTitle,page);					
	}
}

//分页加载搜索结果
function loadPageShare(likeTitle,page){
	$.ajax({
		url:"share/search.do",
		type:"post",
		data:{"likeTitle":likeTitle,"page":page},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				//将其他div均隐藏
				$("#pc_part_2").hide();
				$("#pc_part_3").hide();
				$("#pc_part_7").hide();
				$("#pc_part_8").hide();
				$("#pc_part_6").show();
				var $share_ul = $("#pc_part_6 ul");
				var shareNotes =result.data;
				for(var i = 0;i<shareNotes.length ;i++){
					var shareTitle = shareNotes[i].cn_share_title;
					var li = "";
					li+= '<li class="online">';
					li+= 	'<a >';
					li+= 		'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> ';
					li+= shareTitle
					li+= '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
					li+= '</a>';
					li+= '</li>';
					var shareli = $(li);
					var shareId = shareNotes[i].cn_share_id;
					console.log(shareId);
					shareli.data("shareId",shareId);
					$share_ul.append(shareli);
					
				}
			}
		},
		error:function(){alert("查询失败");}
	});	
}