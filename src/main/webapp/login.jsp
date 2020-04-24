<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="./js/jquery-3.2.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/login.js"></script>
<style type="text/css">
body {
	background: url(images/a.jpg) repeat;
}

#login-box {
	/*border:1px solid #F00;*/
	padding: 35px;
	border-radius: 15px;
	background: #56666B;
	color: #fff;
}
</style>
</head>
<body>
	<input id="userRole" type="hidden" value="3">
	<div class="container" id="top">
		<div class="row" style="margin-top: 280px;">
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
				<form class="form-horizontal" role="form" 
					id="from1" method="post">
					<div class="form-group" style="margin-top: 10px;">
						<label for="firstname" class="col-sm-3 control-label">用户id</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="uname"
								placeholder="请输入名字" name="uname">
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label for="lastname" class="col-sm-3 control-label">密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="upass"
								placeholder="请输入密码" name="upass">
						</div>
					</div>
					<div class="form-group pull-right"
						style="margin-right: 15px; margin-top: 10px;">
						<div>
							<a  onclick="login()"
								class="btn btn-default btn-info">登录</a>
							<button type="reset"
								class="btn btn-default btn-info">重置</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</body>
</html>

<script>

</script>
