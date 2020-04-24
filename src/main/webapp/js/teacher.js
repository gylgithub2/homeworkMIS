$(function() {
	changeHomeworkPage(1, 0);
})

function changeHomeworkPage(page, pageChange) {
	$
			.ajax({
				aysnc : false,
				url : "/homeworkMIS/getPagerHomework.hws",
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
						var str = "";
						var teacherName;
						for (var i = 0; i < data.length; i++) {
							$.ajax({
								async : false,
								url : "/homeworkMIS/getTeacherName.tea",
								cache : false,
								data : {
									"id" : data[i].teacher
								},
								success : function(data) {

									teacherName = data;
								}
							});
							var time = new Date(data[i].setDate);
							str += "<tr><td>"
									+ data[i].headline
									+ "</td><td>"
									+ data[i].homeworkName
									+ "</td><td>"
									+ time.getFullYear()
									+ "年"
									+ (time.getMonth() + 1)
									+ "月"
									+ time.getDate()
									+ "日    "
									+ time.getHours()
									+ ":"
									+ time.getMinutes()
									+ ":"
									+ time.getSeconds()
									+ "</td><td>"
									+ teacherName
									+ "</td><td><a href='downloadFile.hws?id="
									+ data[i].id
									+ "'>down"
									+ "</a>"
									+ "</td><td><a href='#' onclick='deleteHomework("
									+ data[i].id + ")'>删除</a></td></tr>";
						}
						var str2 = "";
						$
								.ajax({
									cache : false,
									async : false,
									url : "/homeworkMIS/getHomeworkNum.hws",
									method : "get",
									success : function(data) {
										str2 += "<ul class='pagination'>"
												+ "<li> <a href='#' onclick='changeHomeworkPage(-1,1)'>.上一页</a></li>";
										for (var i = 0; i <= data / 7.00000000001; i++) {
											str2 += "<li> <a href='#' onclick='changeHomeworkPage("
													+ (i + 1)
													+ " ,0)'>"
													+ (i + 1) + "</a></li>";
										}
										str2 += "<li> <a href='#' onclick='changeHomeworkPage(1,1)'>下一页</a></1i></ul>";
									}
								});
						$("#hwspagination2").html(str2);
						$("#homeworkInfo").html(str);
					}
				}
			});
}

function deleteHomework(homeworkId) {

	$.ajax({
		async : false,
		url : "/homeworkMIS/deleteHomework.hws",
		data : {
			"id" : homeworkId
		},
		method : "POST",
		success : function(data) {
			if (data == "ok") {
				alert("删除成功!");
				changeHomeworkPage(0, 1);
			} else {
				alert("系统异常!");
			}
		}
	})

}
function addHomework() {

	var headline = $("#addhomeworkHeadline").val();
	var homeworkfile = $("#addHomeworkfile")[0].files[0];
	var formData = new FormData();
	formData.append("headline", headline);
	formData.append("homeworkfile", homeworkfile);
	$.ajax({
		url : "/homeworkMIS/addHomework.hws",
		method : "post",
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			if (data == "ok") {
				alert("ok!");
				$('#addHomeworkModal').modal('hide');
				changeHomeworkPage(0, 1);
			} else {
				alert("请重试!");
			}
		}
	});

}

function loadMaterialsInfo() {
	$("#bodyInfo").load("materials-manager.jsp #materialBody2");
	changeMaterialsPage(1, 0);
}
function loadstuHomeworkInfo() {
	$("#bodyInfo").load("stuHomework-manager.jsp #stuHomeworkBody");
	changeStuHomeworkPage(1, 0);
}
function loadHomeworkInfo() {
	$("#bodyInfo").load("teacher.jsp #homeworkBody");
	changeHomeworkPage(1, 0);
}
function changeMaterialsPage(page, pageChange) {
	$
			.ajax({
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
							str += "<tr id='mt"
									+ data[i].id
									+ "'><td>"
									+ data[i].headline
									+ "</td><td><a href='#' onclick='watchContent("
									+ data[i].id + ")'>查看内容</a>"
									+ "</td><td><a href='downloadFile.tm?id="
									+ data[i].id + "'>down" + "</a></td><td>"
									+ time.getFullYear() + "年"
									+ (time.getMonth() + 1) + "月"
									+ time.getDate() + "日    "
									+ time.getHours() + ":" + time.getMinutes()
									+ ":" + time.getSeconds() + "</td></tr>";
						}
						var str2 = "";
						$
								.ajax({
									cache : false,
									async : false,
									url : "/homeworkMIS/getMaterialsNum.tm",
									method : "get",
									success : function(data) {
										str2 += "<ul class='pagination'>"
												+ "<li> <a href='#' onclick='changeMaterialsPage(-1,1)'>.上一页</a></li>";
										for (var i = 0; i <= data / 7.00000000001; i++) {
											str2 += "<li> <a href='#' onclick='changeMaterialsPage("
													+ (i + 1)
													+ " ,0)'>"
													+ (i + 1) + "</a></li>";
										}
										str2 += "<li> <a href='#' onclick='changeMaterialsPage(1,1)'>下一页</a></1i></ul>";
									}
								});
						$("#mmpagination2").html(str2);
						$("#materialInfo").html(str);
					}
				}
			});
}
function changeStuHomeworkPage(page, pageChange) {
	$
			.ajax({
				aysnc : false,
				url : "/homeworkMIS/getPagerStuHomework.hwc",
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
						var studentInfo;
						for (var i = 0; i < data.length; i++) {
							$.ajax({
								async : false,
								url : "/homeworkMIS/getStudentInfo.stu",
								cache : false,
								data : {
									"id" : data[i].student
								},
								success : function(data) {

									studentInfo = data;
								}
							});
							var time = new Date(data[i].uploadDate);
							var time2 = new Date(data[i].readDate);
							var timeInfo = time2.getFullYear() + "年"
									+ (time2.getMonth() + 1) + "月"
									+ time2.getDate() + "日    "
									+ time2.getHours() + ":" + time2.getMinutes()
									+ ":" + time2.getSeconds();
							if (typeof (data[i].readDate) == "undefined") {
								timeInfo = "未评阅";
							}
							str += "<tr><td>"
									+ data[i].homeworkName
									+ "</td><td><a href='downloadFile.hwc?id="
									+ data[i].id
									+ "'>"
									+ data[i].fileName
									+ "</a>"
									+ "</td><td>"
									+ time.getFullYear()
									+ "年"
									+ (time.getMonth() + 1)
									+ "月"
									+ time.getDate()
									+ "日    "
									+ time.getHours()
									+ ":"
									+ time.getMinutes()
									+ ":"
									+ time.getSeconds()
									+ "</td><td>"
									+ studentInfo
									+ "</td><td>"
									+ ((typeof (data[i].readAdvice)) == "undefined"?'未评阅':data[i].readAdvice) 
									+ "</td><td>"
									+ timeInfo
									+ "</td><td><button class='btn btn-success' onclick='openReadHomework("
									+ data[i].id + ")'>评阅</button></td></tr>";
						}
						var str2 = "";
						$
								.ajax({
									cache : false,
									async : false,
									url : "/homeworkMIS/getMyHomeworkNum.hwc",
									method : "get",
									success : function(data) {
										str2 += "<ul class='pagination'>"
												+ "<li> <a href='#' onclick='changeStuHomeworkPage(-1,1)'>.上一页</a></li>";
										for (var i = 0; i <= data / 7.00000000001; i++) {
											str2 += "<li> <a href='#' onclick='changeStuHomeworkPage("
													+ (i + 1)
													+ " ,0)'>"
													+ (i + 1) + "</a></li>";
										}
										str2 += "<li> <a href='#' onclick='changeStuHomeworkPage(1,1)'>下一页</a></1i></ul>";
									}
								});
						$("#shwpagination").html(str2);
						$("#stuHomeworkInfo").html(str);
					}
				}
			});
}
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
	$("#materialContentdiv2").html(content);
	$('#materialContent2').modal();
}
function openReadHomework(stuHomeworkId) {
	$("#stuHomeworkId").val(stuHomeworkId);
	$('#readStuHomeworkModal').modal();
}
function openLeaveMSGModal() {
	$('#leaveMSGModal').modal();
}
function addHomeworkRead() {
	var readMSG = $("#read-msg").val();
	var stuHomeworkId = $("#stuHomeworkId").val();
	var data = {
		"readMSG" : readMSG,
		"stuHomeworkId" : stuHomeworkId
	};
	$.ajax({
		url : "/homeworkMIS/addHomeworkRead.hwc",
		async : false,
		method : "get",
		data : data,
		success : function(data) {
			content = data;
		}
	});
	$('#readStuHomeworkModal').modal('hide');
	changeStuHomeworkPage(0, 1);
}
function addLeaveMSG() {
	var lmsgHeadline = $("#lmsgHeadline").val();
	var lmsgContent = $("#lmsgContent").val();
	var lmsgdata = {
		"lmsgHeadline" : lmsgHeadline,
		"lmsgContent" : lmsgContent
	};
	$.ajax({
		async : false,
		url : "/homeworkMIS/addLeaveMSG.lmsg",
		method : "post",
		data : lmsgdata,
		success : function(data) {
			if (data == "ok") {
				alert("ok!");
				$('#leaveMSGModal').modal('hide');
			} else {
				alert("请重试!");
			}
		}
	});
}