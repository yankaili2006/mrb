<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function uploadImage() {
		$(document).ready(
				function() {
					var options = {
						url : "material.action!ajaxGetImage.do",//跳转到相应的Action  
						type : "POST",//提交方式  
						dataType : "script",//数据类型  
						success : function(msg) {//调用Action后返回过来的数据  
							alert(msg);
							if (msg.indexOf("#") > 0) {
								var data = msg.split("#");
								$("#message").html(
										"<font color='red'>" + data[0]
												+ data[2] + "</font>");
								$("#showImage").html(
										"<img src='" + data[1]+"'/>");
							} else {
								$("#message").html(msg);//在相应的位置显示信息  
							}
						}
					};
					$("#form2").ajaxSubmit(options);//绑定页面中form表单的id  
					return false;
				});
	}
</script>
</head>
<body>
	<form id="form2" method="post" enctype="multipart/form-data">
		<input id="fileupload" name="fileMaterial" type="file" />
		<div id="message"></div>
		<input type="button" class="right-button02" onclick="uploadImage()"
			value="上传" />
	</form>
	图片预览
	<div id="showImage"></div>
</body>
</html>