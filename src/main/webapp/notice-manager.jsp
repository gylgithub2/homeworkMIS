<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>在线作业管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">

</head>
<body>
	<div id="noticeBody" class="panel panel-default">
		<!-- 显示公告内容模态框 -->
		<div class="modal fade" id="noticeContent" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 500px; height: 330px;">
					<div class="modal-header">
						<h2>公告内容:</h2>
					</div>
					<div class="modal-body">
						<br>
						<div id="noticeContentdiv"></div>
					</div>
				</div>
			</div>
		</div>
		<!--添加公告模态框-->
		<form>
			<div id="addNoticeModal" class="modal fade bs-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="col-md-10">
							<div class="panel panel-default">
								<div class="panel-heading">公告信息管理</div>
								<form>
									<table class="table table-bordered">

										<tbody>

											<tr>
												<td>标题:</td>
												<td><div class="row">
														<div class="col-lg-6">
															<input id="addNoticeHeadline" type="text"
																class="form-control" placeholder="">
														</div>
														<!-- /.col-lg-6 -->
													</div> <!-- /.row --></td>
											</tr>
											<tr>
												<td>内容:</td>
												<td><textarea id="addNoticeContent" cols="25" rows="5"
														style="width: 365px;"></textarea></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="button" onclick="addNotice()"
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
		</form>
		<div class="panel-heading">公告信息管理</div>
			<table class="table table-bordered">
				<tr>
					<td>标题</td>
					<td>发布时间</td>
					<td>内容</td>
					<td>操作</td>
				</tr>
				<tbody id="noticeInfo">
				</tbody>
				<tr>
					<td>
						<button data-toggle="modal" data-target="#addNoticeModal"
							class="btn btn-success ">添加公告</button>
					</td>
				</tr>
			</table>
			<div class="panel-footer">
			<nav id="nmpagination" style="text-align: center">
			</nav>
			</div>
	</div>

</body>
</html>
