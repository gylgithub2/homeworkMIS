<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>在线作业管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
</head>
<body>
	<div id="classbody" class="panel panel-default">
		<div class="panel-heading">
			<div class="row">
				<h3 class="col-md-5">班级信息管理</h3>
			</div>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="classInfo">
			</tbody>
			<tr>
				<td>
					<button  data-toggle="modal" data-target="#addClass"
						class="btn btn-default btn-lg active">添加</button>
				</td>
			</tr>
		</table>
		<div class="panel-footer">
			<nav id="cmpagination" style="text-align: center">
			</nav>
		</div>
		<div class="modal fade" id="addClass" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="height: 330px;">
					<div class="modal-header">
						<h2>添加</h2>
					</div>
					<div class="modal-body">
						<br>
						<form action="">
							<label class="col-sm-2 control-label">班级名:</label><input
								type="text" id="clasName"><br> <br>
							<div class="col-sm-offset-2 col-sm-1">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
							<div class="col-sm-offset-3 col-sm-3">
								<button onclick="addClas()" class="btn  btn-default">提交</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>