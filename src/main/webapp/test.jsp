<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

<script src="lib/jquery-1.8.1.min.js" type="text/javascript"></script>

<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script type="text/javascript">
	$(function() {
		$('#snd').click(
				function() {
					$.post("code.do?act=snd&msg={phone:" + $('#phone').val()
							+ "}", function(data) {
						alert(data);
						$('#chkcode').val(data);
					});
				});
		$('#chk').click(
				function() {
					$.post("code.do?act=chk&msg={phone:" + $('#phone').val()
							+ ",chkcode:" + $('#chkcode').val() + "}",
							function(data) {
								alert(data);
							});
				});

		$('#chg').click(
				function() {
					$.post("pwd.do?act=chg&msg={uid:" + $('#uid').val()
							+ ",oldpwd:" + $('#oldpwd').val() + ",newpwd:"
							+ $('#newpwd').val() + "}", function(data) {
						alert(data);
						$('#chkcode').val(data);
					});
				});
		$('#set').click(
				function() {
					$.post("pwd.do?act=set&msg={uid:" + $('#uid').val()
							+ ",oldpwd:" + $('#oldpwd').val() + ",newpwd:"
							+ $('#newpwd').val() + "}", function(data) {
						alert(data);
					});
				});
	});
</script>
</head>
<body>
	<h2>手机校验码</h2>
	<label>chkcode</label>
	<input type="text" id="chkcode" name="chkcode" value="">
	<br>
	<label>phone</label>
	<input type="text" id="phone" name="phone" value="">
	<br>
	<button id="snd" name="snd" class="btn" data-dismiss="modal"
		aria-hidden="true">发送</button>
	<button id="chk" name="chk" class="btn btn-danger" data-dismiss="modal">校验</button>

	<h2>密码</h2>
	<label>uid</label>
	<input type="text" id="uid" name="uid" value="">
	<br>
	<label>oldpwd</label>
	<input type="text" id="oldpwd" name="oldpwd" value="">
	<br>
	<label>newpwd</label>
	<input type="text" id="newpwd" name="newpwd" value="">
	<br>
	<button id="chg" name="chg" class="btn" data-dismiss="modal"
		aria-hidden="true">修改</button>
	<button id="set" name="set" class="btn btn-danger" data-dismiss="modal">重置</button>
</body>
</html>