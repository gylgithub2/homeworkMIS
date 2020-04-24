<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="./js/admin.js"></script>



</head>
<body>
	<!-- 顶栏 -->



	<!-- 顶栏 -->
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-12">
				<!--加入导航条标--->
				<div class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<a href="##" class="navbar-brand" style="color: red">在线作业管理系统</a>
					</div>
					<form action="##" class="navbar-form navbar-right" rol="search">
						<div class="dropdown">
							<button class="btn btn-default dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown"
								style="margin-right: 20px;">
								<span class="glyphicon glyphicon-user">系统管理员</span>
							</button>
							<button style="margin-right: 20px;">
								<span>注销系统</span>
							</button>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="dropdownMenu1">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#"> <span class="glyphicon glyphicon-cog pull-right"></span>
										修改个人信息
								</a></li>
								<!-- 分割-->
								<li role="presentation" class="divider"></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="/logout"> <span
										class="glyphicon glyphicon-off pull-right"></span> 注销
								</a></li>
							</ul>
						</div>

					</form>
				</div>

			</div>
		</div>
	</div>

	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" id="nav">
					<li><a href="#" data-toggle="modal"
						data-target="#changePsword">修改个人密码<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadAdminInfo()">系统信息管理<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadClassInfo()">班级信息管理<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadTeacherInfo()">老师信息管理<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadStudentInfo()">学生信息管理<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadNoticeInfo()">公告信息管理<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadLeaveMSGInfo()">留言板——管理<span 
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="#" onclick="loadMaterialsInfo()">教学资料管理<span
							class="glyphicon glyphicon-pencil pull-right" /></a></li>
					<li><a href="exit.jsp?flag=1">退出系统<sapn
								class="glyphicon glyphicon-log-out pull-right" /></a></li>

				</ul>
			</div>
			<div id="bodyInfo" class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">系统基本信息</div>
					<table class="table table-bordered">

						<tbody>

							<tr>
								<td>操作系统版本:</td>
								<td>Windows Vista 6.1</td>
							</tr>
							<tr>
								<td>操作系统类型:</td>
								<td>X86</td>
							</tr>
							<tr>
								<td>用户目录，临时目录:</td>
								<td>E:\BaiduNetdiskDownload</td>
							</tr>
							<tr>
								<td>JDK版本:</td>
								<td>1.6.0_05</td>
							</tr>
							<tr>
								<td>JDK安装目录:</td>
								<td>E:\java2\JDK8</td>
							</tr>
							<tr>
								<td>总内存剩余内容:</td>
								<td>400MB/ 1448MB</td>
							</tr>


						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
	<div class="modal fade" id="changePsword" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="height: 330px;">
				<div class="modal-header">
					<h2>修改密码</h2>
				</div>
				<div class="modal-body">
					<br>
					<form action="">
						<label class="col-sm-2 control-label">旧密码:</label><input
							type="text" id="oldPsword"><br> <br> <label
							class="col-sm-2 control-label">新密码:</label><input type="text"
							id="newPsword"><br> <br> <label
							class="col-sm-2 control-label">重复密码:</label><input type="text"
							id="repeatPsword"><br> <br>
						<div class="col-sm-offset-2 col-sm-1">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
						<div class="col-sm-offset-3 col-sm-3">
							<button onclick="changepsword()" class="btn  btn-default">提交</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>