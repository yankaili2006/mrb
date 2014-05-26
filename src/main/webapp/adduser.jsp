<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mrb.bean.UserBean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>用户管理</title>
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
	function adduser() {

		var exp = $('#uname').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("用户名不能为空!");
			$('#uname').focus();
			return false;
		}

		exp = $('#phone').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("手机号不能为空!");
			$('#phone').focus();
			return false;
		}

		exp = $('#pwd').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("密码不能为空!");
			$('#pwd').focus();
			return false;
		}

		exp = $('#status').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("状态不能为空!");
			$('#status').focus();
			return false;
		}

		$('#act').val("add");
		$('#msg').val(
				"{uname:" + $('#uname').val() + ",phone:" + $('#phone').val()
						+ ",pwd:" + $('#pwd').val() + ",status:"
						+ $('#status').val() + "}");
		$('#addform').submit();
	}
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
	&nbsp;
	<!--<![endif]-->
	<%
		Long uid = (Long) session.getAttribute("uid");
		String uname = (String) session.getAttribute("uname");
	%>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">

					<li id="fat-menu" class="dropdown"><a href="#" id="drop3"
						role="button" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-user"></i><%=uname%><i class="icon-caret-down"></i>
					</a>

						<ul class="dropdown-menu">
							<li><a tabindex="-1" href="#">设置</a> <br></li>
							<li class="divider">u <br>
							</li>
							<li><a tabindex="-1" href="loginout.do">退出</a></li>
						</ul></li>

				</ul>
				<a class="brand" href="home.do"><span class="first">欢迎登陆</span>
					<span class="second">美人帮管理端</span> </a>
			</div>
		</div>
	</div>


	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span3">
				<div class="sidebar-nav">

					<div class="nav-header" data-toggle="collapse"
						data-target="#home-menu">
						<i class="icon-home"></i>主页
					</div>
					<ul id="home-menu" class="nav nav-list collapse in">
						<li><a href="home.jsp">主页</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#user-menu">
						<i class="icon-user"></i>用户管理
					</div>
					<ul id="user-menu" class="nav nav-list collapse in">
						<li class="active"><a href="user.jsp">用户列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#video-menu">
						<i class="icon-facetime-video"></i>视频管理
					</div>
					<ul id="video-menu" class="nav nav-list collapse in">
						<li><a href="vcate.jsp">视频分类</a></li>
						<li><a href="video.jsp">视频列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#project-menu">
						<i class="icon-road"></i>项目管理
					</div>
					<ul id="project-menu" class="nav nav-list collapse in">
						<li><a href="pcate.jsp">项目分类</a></li>
						<li><a href="city.jsp">城市列表</a></li>
						<li><a href="project.jsp">项目列表</a></li>
						<li><a href="brand.jsp">品牌列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#store-menu">
						<i class="icon-globe"></i>管店管理
					</div>

					<div class="nav-header" data-toggle="collapse"
						data-target="#legal-menu">
						<i class="icon-legal"></i>法律协议
					</div>
					<ul id="legal-menu" class="nav nav-list collapse in">
						<li><a href="privacy.jsp">版权说明</a></li>
						<li><a href="terms.jsp">美人帮使用协议</a></li>
					</ul>
				</div>
			</div>
			<div class="span9">
				<h1 class="page-title">添加用户</h1>
				<div class="btn-toolbar">
					<button class="btn btn-primary" onclick="adduser();">
						<i class="icon-save"></i>保存
					</button>
					<div class="btn-group"></div>
				</div>
				<%
					Object robj = request.getAttribute("result");
					if (robj != null) {
						String result = (String) robj;
						if ("ok".equals(result)) {
				%>
				<div class="alert alert-success">
					<a href="#" class="alert-link">操作成功！</a>
				</div>
				<%
					} else {
				%>
				<div class="alert alert-danger">
					<a href="#" class="alert-link"><%=result%></a>
				</div>
				<%
					}
					}
				%>
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">基本信息</a>
						</li>
					</ul>
					<form id="addform" name="addform" action="user.do" method="post">
						<input type="hidden" id="act" name="act" value=""> <input
							type="hidden" id="msg" name="msg" value="">
					</form>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab">
								<%
									Object obj = request.getAttribute("user");
									UserBean bean = null;
									if (obj != null) {
										bean = (UserBean) obj;
									}
								%>
								<label> 用户名 </label> <input type="text" name="uname" id="uname"
									value="<%if (bean != null)
				out.print(bean.getUname());%>"
									class="input-xlarge"> <label> 手机号 </label> <input
									type="text" name="phone" id="phone"
									value="<%if (bean != null)
				out.print(bean.getPhone());%>"
									class="input-xlarge"> <label> 密码 </label> <input
									type="password" id="pwd" name="pwd" class="input-xlarge">

								<label> 重复密码 </label> <input type="password" id="pwd2"
									name="pwd2" class="input-xlarge"> <label> 状态 </label> <select
									name="status" id="status" class="input-xlarge">
									<option value="Z"
										<%if (bean != null && "Z".equals(bean.getStatus())) {
				out.print("selected=\"selected\"");
			}%>>
										正常</option>
									<option value="C"
										<%if (bean != null && "C".equals(bean.getStatus())) {
				out.print("selected=\"selected\"");
			}%>>
										注销</option>
								</select>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<footer>
		<hr>
		<p class="pull-right">
			<a href="#" target="_blank">技术支持</a> by <a href="#" target="_blank">YKLI</a>
		</p>


		<p>
			&copy; 2014 <a href="#">美人帮</a>
		</p>
		</footer>




		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>


