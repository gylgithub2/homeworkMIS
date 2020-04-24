<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
</head>
<body>
	<div id="studentbody" class="panel panel-default">
		<div class="panel-heading">学生信息管理</div>
		<table class="table table-bordered">
<tr>
									<td>学生号</td>
									<td>姓名</td>
									<td>性别</td>
									<td>年龄</td>
									<td>班级</td>
									<td>登录密码</td>
									<td>操作</td>
								</tr>
			<tbody id="studentInfo"></tbody>
			<tr>
				<td>
					<button data-toggle="modal" data-target="#addStudentModal"
						class="btn btn-default btn-lg active">添加</button>
				</td>
			</tr>
		</table>
		<div class="panel-footer">
			<nav id="smpagination" style="text-align: center">
			</nav>
		</div>
		<form>
			<div id="addStudentModal" class="modal fade bs-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="panel panel-default">
							<div class="panel-heading">老师信息管理</div>
							<table class="table table-bordered">

								<tbody>

									<tr>
										<td>学生号:</td>
										<td><input type="text" id="studentId" /></td>
									</tr>
									<tr>
										<td>姓名:</td>
										<td><input type="text" id="studentName" /></td>
									</tr>
									<tr>
										<td>学生班级</td>
										<td><input type="text" id="studentClass" /></td>
									</tr>
									<tr>
										<td>性别:</td>
										<td><input type="radio" name="stuGender" value="1" />男 <input
											type="radio" name="stuGender" value="2" />女</td>
									</tr>
									<tr>
										<td>年龄:</td>
										<td><input type="text" id="stu-age" /></td>
									</tr>
									<tr>
										<td>登录密码:</td>
										<td><input type="text" id="stu-psword" /></td>
									</tr>
									<tr>
										<td></td>
										<td><input type="button" onclick="addStudent()"
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