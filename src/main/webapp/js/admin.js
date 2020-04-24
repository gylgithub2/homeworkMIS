//密码修改
function changepsword() {
	var newPsword = $("#newPsword").val();
	var repeatPsword = $("#repeatPsword").val();
	if (repeatPsword != newPsword) {
		alert("两次密码不同!");
		return;
	}
	$.ajax({
		url : "/homeworkMIS/updatePassword.admin",
		async : false,
		method : "post",
		data : {
			"oldPassword" : $("#oldPsword").val(),
			"newPassword" : newPsword
		},
		success : function(data) {
			if (data == "ok") {
				alert("修改成功!");
			} else {
				alert("系统异常.请重试");
			}
		}
	});
	$("#oldPsword").val();
}
// 将admin页面的显示部分主体内容,替换为其他页面的div内容,         注:同名方法类似
function loadClassInfo() {
	$("#bodyInfo").load("class-manager.jsp #classbody");
	changeClassPage(1, 0);
}
function loadTeacherInfo() {
	$("#bodyInfo").load("teacher-manager.jsp #teacherbody");
	changeTeacherPage(1, 0);
}
function loadAdminInfo() {
	$("#bodyInfo").load("admin-manager.jsp #adminbody");
	changeAdminPage(1, 0);
}
function loadStudentInfo() {
	$("#bodyInfo").load("student-manager.jsp #studentbody");
	changeStudentPage(1, 0);
}
function loadNoticeInfo() {
	$("#bodyInfo").load("notice-manager.jsp #noticeBody");
	changeNoticePage(1, 0);
}
function loadLeaveMSGInfo() {
	$("#bodyInfo").load("leaveMSG-manager.jsp #leaveMSGBody");
	changeLeaveMSGPage(1, 0);
}
function loadMaterialsInfo() {

	$("#bodyInfo").load("materials-manager.jsp #materialBody");
	changeMaterialsPage(1, 0);
}
// 用于数据显示,ajax的回传json格式的list对象,根据用户各种操作请求动态刷新页面列表,参数1为页数,当参数2为1时,参数1变为页数加减       注:同名方法类似
function changeMaterialsPage(page, pageChange) {
	$.ajax({
		aysnc : false,
		url : "/homeworkMIS/getPagerMaterials.tm",
		cache : false,
		data : {
			"pageChange" : pageChange,
			"page" : page
		},
		method : "post",
		dataType : "json",
		success : function(data) {
			if (data == null || data == '') {
			} else {
				var str = ""
				for (var i = 0; i < data.length; i++) {
					var time = new Date(data[i].uploadDate);
					str += "<tr id='mt" + data[i].id + "'><td>"
							+ data[i].headline
							+ "</td><td><a href='#' onclick='watchContent("
							+ data[i].id + ")'>查看内容</a>"
							+ "</td><td><a href='downloadFile.tm?id="
							+ data[i].id + "'>down" + "</a></td><td>"
							+ time.getFullYear() + "年"
							+ (time.getMonth() + 1) + "月"
							+ time.getDate() + "日    "
							+ time.getHours() + ":" + time.getMinutes()
							+ ":" + time.getSeconds()
							+ "</td><td><a href='#' onclick='deleteMaterial("
							+ data[i].id + ")'>删除</a></td></tr>";
				}
				var str2="" ;
				$. ajax({ 
					cache: false,
					async:false ,
					url:"/homeworkMIS/getMaterialsNum.tm",
					method:"get",
					success : function(data){
						str2 += "<ul class='pagination'>" +
								"<li> <a href='#' onclick='changeMaterialsPage(-1,1)'>.上一页</a></li>";
						for(var i = 0;i<=data/7.00000000001; i++){
							str2 += "<li> <a href='#' onclick='changeMaterialsPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
						}
						str2 += "<li> <a href='#' onclick='changeMaterialsPage(1,1)'>下一页</a></1i></ul>";
					}
			});
				$("#mmpagination").html(str2);
				$("#materialInfo").html(str);
			}
		}
	});
}

function changeNoticePage(page, pageChange) {
	$.ajax({
				aysnc : false,
				url : "/homeworkMIS/getPagerNotice.ntc",
				cache : false,
				data : {
					"pageChange" : pageChange,
					"page" : page
				},
				method : "post",
				dataType : "json",
				success : function(data) {
					if (data == null || data == '') {
					} else {
						var str = ""
						for (var i = 0; i < data.length; i++) {
							var time = new Date(data[i].uploadDate);
							str += "<tr id='ntc"
									+ data[i].id
									+ "'><td>"
									+ data[i].headline
									+ "</td><td>"
									+ time.getFullYear() + "年"
									+ (time.getMonth() + 1) + "月"
									+ time.getDate() + "日    "
									+ time.getHours() + ":" + time.getMinutes()
									+ ":" + time.getSeconds()
									+ "</td><td><a href='#' onclick='watchNoticeContent("
									+ data[i].id
									+ ")'>查看内容</a>"
									+ "</td><td><a href='#' onclick='deleteNotice("
									+ data[i].id + ")'>删除</a></td></tr>";
						}	var str2="" ;
						$. ajax({ 
							cache: false,
							async:false ,
							url:"/homeworkMIS/getNoticeNum.ntc",
							method:"get",
							success : function(data){
								str2 += "<ul class='pagination'>" +
										"<li> <a href='#' onclick='changeNoticePage(-1,1)'>.上一页</a></li>";
								for(var i = 0;i<=data/7.00000000001; i++){
									str2 += "<li> <a href='#' onclick='changeNoticePage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
								}
								str2 += "<li> <a href='#' onclick='changeNoticePage(1,1)'>下一页</a></1i></ul>";
							}
					});
						$("#nmpagination").html(str2);
						$("#noticeInfo").html(str);
					}
				}
			});
}
function changeLeaveMSGPage(page, pageChange) {
	$
			.ajax({
				aysnc : false,
				url : "/homeworkMIS/getPagerLeaveMSG.lmsg",
				cache : false,
				data : {
					"pageChange" : pageChange,
					"page" : page
				},
				method : "post",
				dataType : "json",
				success : function(data) {
					if (data == null || data == '') {
					} else {
						var str = ""
						for (var i = 0; i < data.length; i++) {
							var time = new Date(data[i].uploadDate);
							str += "<tr><td>留言人:</td><td style='width:360px;'>"+data[i].user+"</td><td>留言时间</td><td>"
							+ time.getFullYear() + "年"
							+ (time.getMonth() + 1) + "月"
							+ time.getDate() + "日    "
							+ time.getHours() + ":" + time.getMinutes()
							+ ":" + time.getSeconds()
									+ "</td><td><button class='btn btn-default btn-xs btn-danger btn-primary' onclick='deleteLeaveMSG("
									+ data[i].id
									+ ")'>删除</button></td></tr><tr><td>标题:</td><td colspan='4'><h4>"
									+ data[i].headline
									+ "</h4></td></tr><tr><td>内容:</td><td colspan='4' >"
									+ data[i].msgContent + "</td></tr>"
						}
						var str2="" ;
						$. ajax({ 
							cache: false,
							async:false ,
							url:"/homeworkMIS/getLeaveMSGNum.lmsg",
							method:"get",
							success : function(data){
								str2 += "<ul class='pagination'>" +
										"<li> <a href='#' onclick='changeLeaveMSGPage(-1,1)'>.上一页</a></li>";
								for(var i = 0;i<=data/3.00000000001; i++){
									str2 += "<li> <a href='#' onclick='changeLeaveMSGPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
								}
								str2 += "<li> <a href='#' onclick='changeLeaveMSGPage(1,1)'>下一页</a></1i></ul>";
							}
					});
						$("#lmmpagination").html(str2);
						$("#leaveMSGInfo").html(str);
					}
				}
			});
}
function changeStudentPage(page, pageChange) {
	$.ajax({
		aysnc : false,
		url : "/homeworkMIS/getPagerStudent.stu",
		cache : false,
		data : {
			"pageChange" : pageChange,
			"page" : page
		},
		method : "post",
		dataType : "json",
		success : function(data) {
			if (data == null || data == '') {
			} else {
				var str = ""
				for (var i = 0; i < data.length; i++) {
					str += "<tr id='stu" + data[i].id + "'><td>" + data[i].id
							+ "</td><td>" + data[i].studentName + "</td><td>"
							+ data[i].sex + "</td><td>" + data[i].age
							+ "</td><td>" + data[i].studentClass + "</td><td>"
							+ data[i].studentPassword
							+ "</td><td><a href='#' onclick='deleteStudent("
							+ data[i].id + ")'>删除</a></td></tr>";
				}	
				var str2="" ;
				$. ajax({ 
					cache: false,
					async:false ,
					url:"/homeworkMIS/getStudentNum.stu",
					method:"get",
					success : function(data){
						str2 += "<ul class='pagination'>" +
								"<li> <a href='#' onclick='changeStudentPage(-1,1)'>.上一页</a></li>";
						for(var i = 0;i<=data/7.00000000001; i++){
							str2 += "<li> <a href='#' onclick='changeStudentPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
						}
						str2 += "<li> <a href='#' onclick='changeStudentPage(1,1)'>下一页</a></1i></ul>";
					}
			});
				$("#smpagination").html(str2);
				$("#studentInfo").html(str);
			}
		}
	});
}

function changeTeacherPage(page, pageChange) {

	$.ajax({
		aysnc : false,
		url : "/homeworkMIS/getPagerTeacher.tea",
		cache : false,
		data : {
			"pageChange" : pageChange,
			"page" : page
		},
		method : "post",
		dataType : "json",
		success : function(data) {
			if (data == null || data == '') {
			} else {
				var str = ""
				for (var i = 0; i < data.length; i++) {
					str += "<tr id='tea" + data[i].id + "'><td>" + data[i].id
							+ "</td><td>" + data[i].teacherName + "</td><td>"
							+ data[i].sex + "</td><td>" + data[i].age
							+ "</td><td>" + data[i].teacherPassword
							+ "</td><td><a href='#' onclick='deleteTeacher("
							+ data[i].id + ")'>删除</a></td></tr>";
				}   
				var str2="" ;
				$. ajax({ 
					cache: false,
					async:false ,
					url:"/homeworkMIS/getTeacherNum.tea",
					method:"get",
					success : function(data){
						str2 += "<ul class='pagination'>" +
								"<li> <a href='#' onclick=' changeTeacherPage(-1,1)'>.上一页</a></li>";
						for(var i = 0;i<=data/7.00000000001; i++){
							str2 += "<li> <a href='#' onclick=' changeTeacherPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
						}
						str2 += "<li> <a href='#' onclick='changeTeacherPage(1,1)'>下一页</a></1i></ul>";
					}
			});
				$("#teacherInfo").html(str);
				$("#tmpagination"). html(str2);
		}
		}
	});
}

function changeAdminPage(page, pageChange) {
	$.ajax({
		aysnc : false,
		url : "/homeworkMIS/getPagerAdmin.admin",
		cache : false,
		data : {
			"pageChange" : pageChange,
			"page" : page
		},
		method : "post",
		dataType : "json",
		success : function(data) {
			if (data == null || data == '') {
			} else {
				var str = ""
				for (var i = 0; i < data.length; i++) {
					str += "<tr><td>" + data[i].adminName + "</td><td>"
							+ data[i].adminPassword
							+ "</td><td><a href='#' onclick='deleteAdmin("
							+ data[i].id + ")'>删除</a></td></tr>";
				}
				var str2="" ;
				$. ajax({ 
					cache: false,
					async:false ,
					url:"/homeworkMIS/getAdminNum.admin",
					method:"get",
					success : function(data){
						str2 += "<ul class='pagination'>" +
								"<li> <a href='#' onclick='changeAdminPage(-1,1)'>.上一页</a></li>";
						for(var i = 0;i<=data/7.00000000001; i++){
							str2 += "<li> <a href='#' onclick='changeAdminPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
						}
						str2 += "<li> <a href='#' onclick='changeAdminPage(1,1)'>下一页</a></1i></ul>";
					}
			});
				$("#ampagination").html(str2);
				$("#adminInfo").html(str);
			}
		}
	});
}
function changeClassPage(page, pageChange) {

	$.ajax({
		aysnc : false,
		url : "/homeworkMIS/getPagerClass.cls",
		cache : false,
		data : {
			"pageChange" : pageChange,
			"page" : page
		},
		method : "post",
		dataType : "json",
		success : function(data) {
			if (data == null || data == '') {
			} else {
				var str = ""
				for (var i = 0; i < data.length; i++) {
					str += "<tr id='clas" + data[i].id + "'><td>" + data[i].id
							+ "</td><td>" + data[i].className
							+ "</td><td><a href='#' onclick='deleteClass("
							+ data[i].id + ")'>删除</a></td></tr>";
				}
				var str2="" ;
				$. ajax({ 
					cache: false,
					async:false ,
					url:"/homeworkMIS/getClassNum.cls",
					method:"get",
					success : function(data){
						str2 += "<ul class='pagination'>" +
								"<li> <a href='#' onclick='changeClassPage(-1,1)'>.上一页</a></li>";
						for(var i = 0;i<=data/7.00000000001; i++){
							str2 += "<li> <a href='#' onclick='changeClassPage("+(i+1)+" ,0)'>"+(i+1)+"</a></li>";
						}
						str2 += "<li> <a href='#' onclick='changeClassPage(1,1)'>下一页</a></1i></ul>";
					}
			});
				$("#cmpagination").html(str2);
				$("#classInfo").html(str);
			}
		}
	});
}
// 增加班级新记录      注:同名方法作用类似   addXXXX
function addClas() {


	var clasName = $("#clasName").val();
	$.ajax({
		async : false,
		url : "/homeworkMIS/addClass.cls",
		data : {
			"className" : clasName
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("添加成功!");
				$('#addClass').modal('hide');
				changeClassPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})
}

//删除班级一条记录     注:同名方法作用类似   deleteXXXX
function deleteClass(classId) {

	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteClass.cls",
		data : {
			"id" : classId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeClassPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})
}

function addTeacher() {

	var gender = $("input[name='gender']:checked").val();// 1&2
	var id = $("#teacherId").val();
	var teacherName = $("#teacherName").val();
	var age = $("#age").val();
	var psword = $("#psword").val();
	$.ajax({
		async : false,
		method : "POST",
		url : "/homeworkMIS/addTeacher.tea",
		data : {
			"id" : id,
			"name" : teacherName,
			"psword" : psword,
			"sex" : gender,
			"age" : age
		},
		success : function(data) {
			if (data == "ok") {
				alert("添加成功");
				$('#addTeacherModal').modal('hide');
				changeTeacherPage(0, 1);
			} else {
				alert("系统异常");
			}
		}
	})
}
function addAdmin() {
	
	var addAdminName = $("#addAdminName").val();
	var addAdminPasssword = $("#addAdminPasssword").val();
	$.ajax({
		async : false,
		method : "POST",
		url : "/homeworkMIS/addAdmin.admin",
		data : {
			"name" : addAdminName,
			"psword" : addAdminPasssword,
		},
		success : function(data) {
			if (data == "ok") {
				alert("添加成功");
				$('#addAdminModal').modal('hide');
				changeAdminPage(0, 1);
			} else {
				alert("系统异常");
			}
		}
	})
}

function deleteTeacher(teacherId) {

	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteTeacher.tea",
		data : {
			"id" : teacherId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeTeacherPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})

}
function deleteAdmin(adminId) {
	
	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteAdmin.admin",
		data : {
			"id" : adminId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeAdminPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})
	
}

function deleteStudent(studentId) {
	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteStudent.stu",
		data : {
			"id" : studentId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeStudentPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})
}
function deleteNotice(noticeId) {
	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteNotice.ntc",
		data : {
			"id" : noticeId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeNoticePage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})
}
// 除了删除教学资料记录,也会删除实际存储文件
function deleteMaterial(materialId) {

	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteMaterial.tm",
		data : {
			"id" : materialId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeMaterialsPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})

}
function deleteLeaveMSG(msgId) {

	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteLeaveMSG.lmsg",
		data : {
			"id" : msgId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeLeaveMSGPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})

}

function addStudent() {

	var stuGender = $("input[name='stuGender']:checked").val();// 1&2
	var id = $("#studentId").val();
	var studentName = $("#studentName").val();
	var age = $("#stu-age").val();
	var studentPassword = $("#stu-psword").val();
	var studentClass = $("#studentClass").val();
	$.ajax({
		async : false,
		method : "POST",
		url : "/homeworkMIS/addStudent.stu",
		data : {
			"id" : id,
			"studentName" : studentName,
			"studentPassword" : studentPassword,
			"sex" : stuGender,
			"studentClass" : studentClass,
			"age" : age
		},
		success : function(data) {
			if (data == "ok") {
				alert("添加成功");
				$('#addStudentModal').modal('hide');
				changeStudentPage(0, 1);
			} else {
				alert("系统异常");
			}
		}
	})
}

// 添加教学资料,包括文件存储
function addMaterial() {
	var materialHeadline = $("#materialHeadline").val();
	var materialContent = $("#materialContent").val();
	var materialFile = $("#materialFile")[0].files[0];
	var formData = new FormData();
	formData.append("materialHeadline", materialHeadline);
	formData.append("materialContent", materialContent);
	formData.append("materialFile", materialFile);
	$.ajax({
		url : "/homeworkMIS/addMaterial.tm",
		method : "post",
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			if (data == "ok") {
				alert("ok!");
				$('#addMaterialModal').modal('hide');
				changeMaterialsPage(0, 1);
			} else {
				alert("请重试!");
			}
		}
	});
}
function addNotice() {
	var noticeHeadline = $("#addNoticeHeadline").val();
	var noticeContent = $("#addNoticeContent").val();
	var noticedata = {
		"noticeHeadline" : noticeHeadline,
		"noticeContent" : noticeContent
	};
	$.ajax({
		async : false,
		url : "/homeworkMIS/addNotice.ntc",
		method : "post",
		data : noticedata,
		success : function(data) {
			if (data == "ok") {
				alert("ok!");
				$('#addNoticeModal').modal('hide');
				changeNoticePage(0, 1);
			} else {
				alert("请重试!");
			}
		}
	});
}

// ajax获取教学资料内容,修改模态框内容,并打开模态框
function watchContent(materialId) {

	var content;
	$.ajax({
		url : "/homeworkMIS/getMaterialContent.tm",
		async : false,
		method : "get",
		data : {
			"id" : materialId
		},
		success : function(data) {
			content = data;
		}
	});
	$("#materialContentdiv").html(content);
	$('#materialContent').modal();
}
//ajax获取公告内容,修改模态框内容,并打开模态框
function watchNoticeContent(noticeId) {

	var content;
	$.ajax({
		url : "/homeworkMIS/getNoticeContent.ntc",
		async : false,
		method : "get",
		data : {
			"id" : noticeId
		},
		success : function(data) {
			content = data;
		}
	});
	$("#noticeContentdiv").html(content);
	$('#noticeContent').modal();
}
