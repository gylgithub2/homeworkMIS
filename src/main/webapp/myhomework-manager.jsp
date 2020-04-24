<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</head>
<body>




	<div id="myhomeworkInfo" class="panel panel-default"
		style="border: 1px solid #999999; height: 470px; width: 863px; margin-left: 80px;">
		<div class="panel-heading">
			<h4 style="color: blue">我的作业</h4>

		</div>

		<table class="table table-bordered">
			<tr>
				<td>作业名称</td>
				<td>作业附件</td>
				<td>提交时间</td>
				<td>老师评阅信息</td>
				<td>评阅时间</td>
			</tr>
			<tbody id="myhomeworkInfox">
			</tbody>
		</table>
		<div class="panel-footer">
				<nav id="hwcpagination" style="text-align: center">
				</nav>
	</div>
</body>
</html>