//登录方法
function login() {
	var userRole = $("#userRole").val();
	$.ajax({
		async : false,
		method : "post",
		url : "/homeworkMIS/login.lg",
		data : {
			"userRole" : userRole,
			"userName" : $("#uname").val(),
			"userPassword" : $("#upass").val()
		},
		success : function(data) {
			if (data == "ok") {
				if (userRole == 1) {
					window.location.href = "/homeworkMIS/teacher.jsp";
				} else if (userRole == 2) {
					window.location.href = "/homeworkMIS/student.jsp";
				} else if (userRole == 3) {
					window.location.href = "/homeworkMIS/admin.jsp";
				} else {
					alert("系统异常,请联系管理员");
				}
			} else {
				alert("登陆失败");
			}
		}
	});

}