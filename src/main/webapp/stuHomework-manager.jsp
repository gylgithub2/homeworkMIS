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



</head>
<body>

	<div id="stuHomeworkBody" class="panel panel-default"
		style="border: 1px solid #999999; height: 470px; width: 863px; margin-left: 80px;">
		<!--批阅作业的模态框-->
		<div id="readStuHomeworkModal" class="modal fade bs-example-modal-lg"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="col-md-10">
						<div class="panel panel-default">
							<div class="panel-heading">学生作业评阅</div>
							<form>
								<table class="table table-bordered">

									<tbody>

										<tr>
											<td>评阅信息:</td>
											<td><div class="row">
													<div class="col-lg-6">
														<input id="read-msg" type="text" class="form-control"
															placeholder="">
													</div>
													<!-- /.col-lg-6 -->
												</div> <!-- /.row --></td>
										</tr>

										<tr>
											<td></td>
											<td><input id="stuHomeworkId" type="hidden"><input
												type="button" onclick="addHomeworkRead()" value="提交" /> <input
												type="reset" name="" id="" value="重置" /></td>
										</tr>


									</tbody>
								</table>
							</form>

						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="panel-heading">
			<h4 style="color: blue">作业管理</h4>

		</div>
		<table class="table table-bordered">
			<tr>
				<td>作业名称</td>
				<td>作业附件</td>
				<td>提交时间</td>
				<td>学生信息</td>
				<td>评阅信息</td>
				<td>评阅时间</td>
				<td>操作</td>
			</tr>
			<tbody id='stuHomeworkInfo'>
			</tbody>
		</table>
		<div class="panel-footer">
				<nav id="shwpagination" style="text-align: center">
				</nav>
		<style type="text/css">
.ass {
	background-color: #888888;
	text-align: center;
	height: 55px;
}
</style>
	</div>
</body>

</html>
