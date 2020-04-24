<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>在线作业管理系统</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">



</head>
<body>
	<div id="leaveMSGBody" class="panel panel-default">
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
									<nav id="lmpagination" style="text-align: center"></nav>

								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>


		<div class="panel-heading">留言信息管理</div>
		<table class="table table-bordered">

			<tbody id="leaveMSGInfo">
			</tbody>
		</table>
		<div class="panel-footer">
			<nav id="lmmpagination" style="text-align: center">
			</nav>
		</div>
	</div>

</body>
</html>
