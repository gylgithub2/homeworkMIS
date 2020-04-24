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

						var str = ""
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
									+ "</td><td><a href='#' onclick='uploadHomeworkModal("
									+ data[i].id + ")'>上传作业</a></td></tr>";
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
						$("#hwspagination").html(str2);
						$("#homeworkInfo").html(str);
					}
				}
			});
}
function changeMyHomeworkPage(page, pageChange) {
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
						for (var i = 0; i < data.length; i++) {
							var time = new Date(data[i].readDate);
							var time2 = new Date(data[i].uploadDate);
							var timeInfo = time.getFullYear() + "年"
									+ (time.getMonth() + 1) + "月"
									+ time.getDate() + "日    "
									+ time.getHours() + ":" + time.getMinutes()
									+ ":" + time.getSeconds();
							if (typeof (data[i].readDate) == "undefined") {
								timeInfo = "未评阅";
							}
							str += "<tr><td>" + data[i].homeworkName
									+ "</td><td><a href='downloadFile.hwc?id="
									+ data[i].id + "'>" + data[i].fileName
									+ "</a>" + "</td><td>"
									+ time2.getFullYear() + "年"
									+ (time2.getMonth() + 1) + "月"
									+ time2.getDate() + "日    "
									+ time2.getHours() + ":"
									+ time2.getMinutes() + ":"
									+ time2.getSeconds() + "</td><td>"
									+ ((typeof (data[i].readAdvice)) == "undefined"?'未评阅':data[i].readAdvice) 
									+ "</td><td>"
									+ timeInfo + "</td></tr>";
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
												+ "<li> <a href='#' onclick='changeMyHomeworkPage(-1,1)'>.上一页</a></li>";
										for (var i = 0; i <= data / 7.00000000001; i++) {
											str2 += "<li> <a href='#' onclick='changeMyHomeworkPage("
													+ (i + 1)
													+ " ,0)'>"
													+ (i + 1) + "</a></li>";
										}
										str2 += "<li> <a href='#' onclick='changeMyHomeworkPage(1,1)'>下一页</a></1i></ul>";
									}
								});
						$("#hwcpagination").html(str2);
						$("#myhomeworkInfox").html(str);
					}
				}
			});
}
// 打开提交作业模态框,以及数据填入
function uploadHomeworkModal(id) {
	var homeworkName;
	$.ajax({
		url : "/homeworkMIS/getHomeworkName.hws",
		data : {
			"id" : id
		},
		async : false,
		method : "get",
		success : function(data) {
			if (data == "fail") {
				alert("系统异常！");
				return;
			} else {

				homeworkName = data;
			}
		}
	});
	$("#homeworkHeadline").val(homeworkName);
	$('#uploadHomeworkModal').modal({
		keyboard : false
	})
}
// 作业上传
function uploadHomework() {

	var homeworkHeadline = $("#homeworkHeadline").val();
	var homeworkFile = $("#homeworkFile")[0].files[0];
	var formData = new FormData();
	formData.append("homeworkHeadline", homeworkHeadline);
	formData.append("homeworkFile", homeworkFile);
	$.ajax({
		url : "/homeworkMIS/uploadHomework.hwc",
		method : "post",
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			if (data == "ok") {
				alert("ok!");
				$('#uploadHomeworkModal').modal('hide');
				changeHomeworkPage(0, 1);
			} else {
				alert("请重试!");
			}
		}
	});

}
// 消息发送模态框
function openLeaveMSGModal() {
	$('#leaveMSGModal').modal();
}
// 消息新增
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
function loadHomeworkInfo() {
	$("#bodyInfo").load("student.jsp #homeworkInfodiv");
	changeHomeworkPage(1, 0);
}
function loadMyHomeworkInfo() {
	$("#bodyInfo").load("myhomework-manager.jsp #myhomeworkInfo");
	changeMyHomeworkPage(1, 0);
}
