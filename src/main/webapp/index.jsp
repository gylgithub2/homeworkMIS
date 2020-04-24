<%@page import="service.NoticeService"%>
<%@page import="service.NoticeServiceImpl"%>
<%@page import="model.Notice"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>在线作业管理系统</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="./js/jquery-3.2.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/login.js"></script>

	

</head>
<body>
	<!-- 顶栏 -->
	


<!-- 顶栏 -->
<div class="container" id="top">
    <div class="row">
        <div class="col-md-12">
            <!--加入导航条标--->
			<div id="" class="ass">
				<h1 style="color:red">在线作业管理系统</h1>
			</div>
            <div class="navbar navbar-default" role="navigation">
                　<div class="navbar-header">
                　    <a href="##" class="navbar-brand" >首页</a>
                　</div>
                
            </div>

        </div>
    </div>
</div>

	<!-- 中间主体 -->
	<div class="container" id="content" >
			<div class="row" >
			

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav" style="  border:1px solid #999999;height: 200px; width: 270px; line-height: 2em; padding-left: 10px; ">
       
		<li><h4 style="color:blue ">用户登录</h4></li>
	 
        <li><p>教师用教师号登录（学生用学生号登录）</p></li>
        <li> 用户名:<input type="text" id="uname"  placeholder="请输入用户名" name="uname"></li>
		<li>密码:&nbsp;&nbsp;&nbsp;<input id="upass" type="password" placeholder="请输入密码" name="upass"></li>
		<li>身份:
		<select id="userRole" >
			<option value="1">老师</option>
			<option value="2">学生</option>
		</select>
		<button  class="btn btn-default btn-info" onclick="login()">登录</button>
		</li>
    </ul>
    <%              NoticeService noticeService = new NoticeServiceImpl();
					List<Notice> lists =  noticeService.queryAll();
				%>
				<ul class="nav nav-pills nav-stacked" id="nav"
					style="margin-top: 5px; border: 1px solid #999999; width: 270px; padding-left: 10px; line-height: 2em;">
					<li><h4 style="color: blue">网站公告</h4></li>
						<%if(lists!=null){
						for (int i = 0; i < lists.size(); i++) {
							Notice notice = lists.get(i);
					%>
					<li><%=notice.getHeadline()%></li>
					<%
						}}
					%>
				</ul>
	<ul class="nav nav-pills nav-stacked" id="nav" style=" margin-top: 5px; border:1px solid #999999; width: 270px; padding-left: 10px; line-height: 2em; ">
		<li><h4 style="color:blue">日历表</h4></li>
		<li><!-- On rows -->
			<tr >日</tr>
			<tr >一</tr>
			<tr >二</tr>
			<tr >三</tr>
			<tr >四</tr>
			<tr >五</tr>
			<tr >六</tr>
			</li>
		<li><!-- On rows -->
			<tr >4</tr>
			<tr >5</tr>
			<tr >6</tr>
			<tr >7</tr>
			<tr >8</tr>
			<tr >9</tr>
			<tr >10</tr>
			</li>	
		
	</ul>
</div>
			<div class="col-md-10" >
				<div class="panel panel-default" style=" border:1px solid #999999; height: 470px; width: 863px;margin-left: 80px;  ">
				    <div class="panel-heading">
						<h4 style="color:blue">教学基本资料</h4>
					
					</div>
				    <table  class="table table-bordered">
					        
					        <tbody id="materialBody">
					        </tbody>
					        
				    </table>
				    <nav id="mtpagination2" style="text-align: center">
					</nav>
				</div>

			</div>
		</div>
	</div>
			<div class="modal fade" id="materialContent" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 500px; height: 330px;">
					<div class="modal-header">
						<h2>教学材料内容:</h2>
					</div>
					<div class="modal-body">
						<br>
						<div id="materialContentdiv"></div>
					</div>
				</div>
			</div>
		</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<style type="text/css">
		.ass{
			background-color: #888888;
			text-align: center;
			height:55px;
		}
	</style>
	<script type="text/javascript">
	$(function(){
		changeMaterialsPage(1,0);
	})
	function watchContent(materialId) {

	var content;
	$.ajax({
		url : "/homeworkMIS/getMaterialContent.tm",
		async : false,
		method : "get",
		data : {
			"id" : materialId
		},
		success : function(data) {
			content = data;
		}
	});
	$("#materialContentdiv").html(content);
	$('#materialContent').modal();
}
	function changeMaterialsPage(page, pageChange) {
		$.ajax({
			aysnc : false,
			url : "/homeworkMIS/getPagerMaterials.tm",
			cache : false,
			data : {
				"pageChange" : pageChange,
				"page" : page
			},
			method : "post",
			dataType : "json",
			success : function(data) {
				if (data == null || data == '') {
				} else {
					var str = ""
					for (var i = 0; i < data.length; i++) {
						str += "<tr id='mt" + data[i].id + "'><td>"
								+ data[i].headline
								+ "</td><td><a href='#' onclick='watchContent("
								+ data[i].id + ")'>查看内容</a>"
								+ "</td><td>"
								+ data[i].uploadDate
								+ "</td></tr>";
					}
					var str2="" ;
					$. ajax({ 
						cache: false,
						async:false ,
						url:"/homeworkMIS/getMaterialsNum.tm",
						method:"get",
						success : function(data){
							str2 += "<ul class='pagination'>" +
									"<li> <a href='#' onclick='changeMaterialsPage(-1,1)'>.上一页</a></li>";
							for(var i = 0;i<=data/7.00000000001; i++){
								str2 += "<li> <a href='#' onclick='changeMaterialsPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
							}
							str2 += "<li> <a href='#' onclick='changeMaterialsPage(1,1)'>下一页</a></1i></ul>";
						}
				});
					$("#mtpagination2").html(str2);
					$("#materialBody").html(str);
				}
			}
		});
	}

	</script>
</html>