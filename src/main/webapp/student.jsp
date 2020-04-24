<%@page import="model.Notice"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>在线作业管理系统</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/student.js"></script>



</head>
<body>
	<!-- 顶栏 -->



	<!-- 顶栏 -->
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-12">
				<!--加入导航条标--->
				<div id="" class="ass">
					<h1 style="color: red">在线作业管理系统</h1>
				</div>
				<div class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<a href="#" onclick="loadHomeworkInfo()" class="navbar-brand">首页</a>
						<a href="#" onclick="loadMyHomeworkInfo()" class="navbar-brand">我的作业</a>
						<a href="#" onclick="openLeaveMSGModal()" class="navbar-brand">网站留言板</a>
					</div>

				</div>

			</div>
		</div>
	</div>

	<!--提交作业模态框-->
	<div id="uploadHomeworkModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading">提交作业</div>
						<form enctype="multipart/form-data">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td>作业名称:</td>
										<td><div class="row">
												<div class="col-lg-6">
													<input id="homeworkHeadline" type="text"
														disabled="disabled" class="form-control" placeholder="">
												</div>
												<!-- /.col-lg-6 -->
											</div> <!-- /.row --></td>
									</tr>
									<tr>
										<td>作业附件:</td>
										<td><div class="row">
												<div class="col-lg-6">
													<div class="input-group">
														<input type="file" id="homeworkFile" class="form-control"
															placeholder=""> <span class="input-group-btn">
														</span>
													</div>
													<!-- /input-group -->
												</div>
												<!-- /.col-lg-6 -->
											</div> <!-- /.row --></td>
									</tr>

									<tr>
										<td></td>
										<td><input type="button" onclick="uploadHomework()"
											value="提交" /> <input type="reset" name="" id="" value="重置" /></td>
									</tr>
								</tbody>
							</table>
						</form>

					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">


			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" id="nav"
					style="border: 1px solid #999999; height: 200px; width: 270px; line-height: 2em; padding-left: 10px;">

					<li><h4 style="color: blue">用户登录</h4></li>

					<li></li>
					<li><div id="">
							欢迎您:&nbsp;${studentName }&nbsp;&nbsp;&nbsp;<a
								href="/homeworkMIS/exit.jsp?flag=0">安全退出</a>
						</div></li>
					<li></li>
					<li></li>
				</ul>
				<%
					List<Notice> lists = (List<Notice>) session.getAttribute("notices");
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
				<ul class="nav nav-pills nav-stacked" id="nav"
					style="margin-top: 5px; border: 1px solid #999999; width: 270px; padding-left: 10px; line-height: 2em;">
					<li><h4 style="color: blue">日历表</h4></li>
					<li>
						<!-- On rows -->
						<tr>日
						</tr>
						<tr>一
						</tr>
						<tr>二
						</tr>
						<tr>三
						</tr>
						<tr>四
						</tr>
						<tr>五
						</tr>
						<tr>六
						</tr>
					</li>
					<li>
						<!-- On rows -->
						<tr>4
						</tr>
						<tr>5
						</tr>
						<tr>6
						</tr>
						<tr>7
						</tr>
						<tr>8
						</tr>
						<tr>9
						</tr>
						<tr>10
						</tr>
					</li>

				</ul>
			</div>
			<div id="bodyInfo" class="col-md-10">
				<div id="homeworkInfodiv" class="panel panel-default"
					style="border: 1px solid #999999; height: 470px; width: 863px; margin-left: 80px;">
					<div class="panel-heading">
						<h4 style="color: blue">作业查看</h4>

					</div>

					<table class="table table-bordered">
						<tr>
							<td>作业名称</td>
							<td>作业附件</td>
							<td>发布时间</td>
							<td>发布老师</td>
							<td>操作</td>
						</tr>
						<tbody id="homeworkInfo">
						</tbody>
					</table>
					<div class="panel-footer">
						<nav id="hwspagination" style="text-align: center">
						</nav>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
	<!--添加留言模态框-->
	<div id="leaveMSGModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading">留言板</div>
						<form>
							<table class="table table-bordered">

								<tbody>

									<tr>
										<td>标题:</td>
										<td><input id="lmsgHeadline" type="text"
											class="form-control" placeholder=""></td>
									</tr>
									<tr>
										<td>内容:</td>
										<td><textarea id="lmsgContent" cols="25" rows="5"
												style="width: 365px;">请输入内容</textarea></td>
									</tr>

									<tr>
										<td><input type="button" onclick="addLeaveMSG()"
											value="提交" /></td>
									</tr>


								</tbody>
							</table>
							<div class="panel-footer">
								<nav style="text-align: center">
									<ul class="pagination">
										<li><a href="#" onclick="changeLeaveMSGPage(-1,1)">&laquo;上一页</a></li>
										<li><a href="#" onclick="changeLeaveMSGPage(1,0)">1</a></li>
										<li><a href="#" onclick="changeLeaveMSGPage(2,0)">2</a></li>
										<li><a href="#" onclick="changeLeaveMSGPage(1,1)">下一页</a></li>
									</ul>
								</nav>

							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
<style type="text/css">
.ass {
	background-color: #888888;
	text-align: center;
	height: 55px;
}
</style>

</html>

