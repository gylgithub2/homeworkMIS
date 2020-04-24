<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>在线作业管理系统</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="utf-8">
</head>
<body>
	<div id="materialBody2" class="panel panel-default" style="width:860px;margin:00px 80px;">
		<div class="panel-heading">教学资料管理</div>
			<table class="table table-bordered">
				<tr>
					<td>名称</td>
					<td>内容</td>
					<td>附件下载</td>
					<td>发布时间</td>
				</tr>
				<tbody id="materialInfo">
				</tbody>
			</table>
			<div class="panel-footer">
				<nav id="mmpagination2" style="text-align: center">
				</nav>
			</div>
					<div class="modal fade" id="materialContent2" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 500px; height: 330px;">
					<div class="modal-header">
						<h2>教学材料内容:</h2>
					</div>
					<div class="modal-body">
						<br>
						<div id="materialContentdiv2"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="materialBody" class="panel panel-default">
		<div class="panel-heading">教学资料管理</div>
			<table class="table table-bordered">
				<tr>
					<td>名称</td>
					<td>内容</td>
					<td>附件下载</td>
					<td>发布时间</td>
					<td>操作</td>
				</tr>
				<tbody id="materialInfo">

				</tbody>

				<tr>
					<td colspan="5">
						<button class="btn btn-success " data-toggle="modal"
							data-target="#addMaterialModal">添加</button>
					</td>
				</tr>
			</table>
			<div class="panel-footer">
				<nav id="mmpagination" style="text-align: center">
				</nav>
			</div>
		<div class="modal fade" id="materialContent" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 500px; height: 330px;">
					<div class="modal-header">
						<h2>教学材料内容:</h2>
					</div>
					<div class="modal-body">
						<br>
						<div id="materialContentdiv"></div>
					</div>
				</div>
			</div>
		</div>
		<div id="addMaterialModal" class="modal fade bs-example-modal-lg"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="col-md-10">
						<div class="panel panel-default">
							<div class="panel-heading">教学资料管理</div>
							<form enctype="multipart/form-data">
								<table class="table table-bordered">

									<tbody>

										<tr>
											<td>名称:</td>
											<td><input id="materialHeadline" type="text"
												class="form-control" placeholder=""></td>
										</tr>
										<tr>
											<td>附件:</td>
											<td><div class="row">
													<div class="col-lg-6">
														<div class="input-group">
															<input type="file" id="materialFile" class="form-control"
																placeholder=""> <span class="input-group-btn">
																<button class="btn btn-default" type="button">上传</button>
															</span>
														</div>
														<!-- /input-group -->
													</div>
													<!-- /.col-lg-6 -->
												</div> <!-- /.row --></td>
										</tr>
										<tr>
											<td>介绍:</td>
											<td><textarea id="materialContent" cols="25" rows="5"
													style="width: 365px;">请输入内容</textarea></td>
										</tr>

										<tr>
											<td></td>
											<td><input type="button" onclick="addMaterial()"
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

	</div>
</body>
</html>