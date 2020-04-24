<%@page import="service.AdminServiceImpl"%>
<%@page import="service.AdminService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
</head>
<body>
	<div id="adminbody" class="panel panel-default">
		<div class="panel-heading">系统信息管理</div>
		<table class="table table-bordered">
			<tr>
				<td>账号</td>
				<td>密码</td>
				<td>操作</td>
			</tr>
			<tbody id="adminInfo"></tbody>
			<tr>
				<td>
					<button data-toggle="modal" data-target="#addAdminModal"
						class="btn btn-default btn-lg active">添加</button>
				</td>
			</tr>
		</table>
		<div class="panel-footer">
			<nav id="ampagination" style="text-align: center">
			</nav>
		</div>
		<form>
			<div id="addAdminModal" class="modal fade bs-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="panel panel-default">
							<div class="panel-heading">增加管理员</div>
							<table class="table table-bordered">

								<tbody>

									<tr>
										<td>姓名:</td>
										<td><input type="text" id="addAdminName" /></td>
									</tr>
									<tr>
										<td>登录密码:</td>
										<td><input type="text" id="addAdminPasssword" /></td>
									</tr>
									<tr>
										<td></td>
										<td><input type="button" onclick="addAdmin()"
											value="提交" /> <input type="reset" name="" id="" value="重置" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>