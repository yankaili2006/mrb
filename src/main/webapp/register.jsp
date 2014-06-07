<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>注册新用户</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

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

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<script type="text/javascript">
	$(function() {
		$('#regbutton').click(
				function() {
					if ($('#uname').val() == '') {
						alert("用户名不能为空");
						return false;
					} else if ($('#phone').val() == '') {
						alert("手机号不能为空");
						return false;
					} else if ($('#pwd').val() == '') {
						alert("密码不能为空");
						return false;
					} else if ($('#pwd').val() != $('#repwd').val()) {
						alert("两次输入的密码不一致");
						return false;
					} else if (!document.getElementById('protocol').checked) {
						alert("请先阅读并接受协议");
						return false;
					}

					$.post("user.do", {
						act : "register",
						msg : "{uname:" + $('#uname').val() + ",phone:"
								+ $('#phone').val() + ",pwd:" + $('#pwd').val()
								+ "}",
					}, function(data) {
						var obj = eval('(' + data + ')');
						alert(obj.msg);
						if (obj.code == '0000') {
							$('#msg').val(
									"{uname:" + $('#uname').val() + ",phone:"
											+ $('#phone').val() + ",pwd:"
											+ $('#pwd').val() + "}");
							$('#loginform').submit();
						}
					});

				});
	});
</script>
<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body>
	<!--<![endif]-->

	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">

				</ul>
				<a class="brand" href="home.do"><span class="first">欢迎登陆</span>
					<span class="second">美人邦管理端</span> </a>
			</div>
		</div>
	</div>

	<form name="loginform" id="loginform" action="user.do" method="post">
		<input type="hidden" id="act" name="act" value="loginweb"> <input
			type="hidden" id="msg" name="msg" value="">
	</form>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span4 offset4 dialog">
				<div class="block">
					<div class="block-heading">注册新用户</div>
					<div class="block-body">
						<form>
							<label> 用户名 </label> <input id="uname" name="uname" type="text"
								class="span12"> <label> 手机号 </label> <input id="phone"
								name="phone" type="text" class="span12"> <label>
								密码 </label> <input id="pwd" name="pwd" type="password" class="span12">
							<label> 重复密码 </label> <input id="repwd" name="repwd"
								type="password" class="span12"> <label
								class="remember-me"> <input id="protocol"
								name="protocol" type="checkbox"> 我已经阅读并同意了 <a
								href="terms.jsp">《美人邦使用协议》</a>
							</label>
							<button type="button" id="regbutton" name="regbutton"
								class="btn btn-primary pull-center">注册</button>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
				<p>
					<a href="privacy.jsp">版权说明</a>
				</p>
			</div>
		</div>








		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>


