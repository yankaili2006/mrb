<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>
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
<script type="text/javascript">
	$(function() {
		$('#loginbutton').click(
				function() {
					if ($('#uname').val() == '') {
						alert("用户名或手机号不能为空");
						return false;
					} else if ($('#pwd').val() == '') {
						alert("密码不能为空");
						return false;
					}

					$('#msg').val(
							"{uname:" + $('#uname').val() + ",pwd:"
									+ $('#pwd').val() + "}");
					$('#loginform').submit();

				});
	});
</script>
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
					<span class="second">美人帮管理端</span> </a>
			</div>
		</div>
	</div>

	<form name="loginform" id="loginform" action="user.do" method="post">
		<input type="hidden" id="act" name="act" value="loginuserphone">
		<input type="hidden" id="msg" name="msg" value="">
	</form>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="dialog span4">
				<div class="block">
					<div class="block-heading">登&nbsp;&nbsp;陆</div>
					<div class="block-body">
						<form>
							<label> 用户名或手机号 </label> <input type="text" id="uname"
								name="uname" class="span12"> <label>
								密&nbsp;&nbsp;码 </label> <input type="password" id="pwd" name="pwd"
								class="span12">
							<button type="button" id="loginbutton" name="loginbutton"
								class="btn btn-primary pull-right">登陆</button>
							<label class="remember-me"> <input type="checkbox">
								记住我
							</label>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
				<p class="pull-right" style="">
					<a href="register.jsp" target="blank">注册新用户?</a>
				</p>

				<p>
					<a href="resetpwd.jsp">忘记密码?</a>
				</p>
			</div>
		</div>


		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>
