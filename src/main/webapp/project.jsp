<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>项目管理</title>
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
			function delproject(){
				$('#act').val("del");
				$('#msg').val("{pid:" +$('#pid').val()+"}");
				$('#editpid').submit();
			}
			
			function gotoedit(obj){
				var tds=$(obj).parent().parent().find('td');
				$('#pid').val(tds.eq(0).text());
				$('#act').val("edit");
				$('#msg').val("{pid:" + tds.eq(0).text()+"}");
				$('#editpid').submit();
			}
			
			function gotoadd(){
				window.location.href="addproject.jsp";
			}
			
			function setpid(obj){
				var tds=$(obj).parent().parent().find('td');
				$('#pid').val(tds.eq(0).text());
				$('#update').modal('show');
			}
			
			$(document).ready(function(){
  				$.post("project.do?act=list", function(data) {
  					$("#projectgrid").html(data);
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

						<li id="fat-menu" class="dropdown">
							<a href="#" id="drop3" role="button" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="icon-user"></i>管理员admin<i
								class="icon-caret-down"></i> </a>

							<ul class="dropdown-menu">
								<li>
									<a tabindex="-1" href="#">设置</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a tabindex="-1" href="admin.jsp">退出</a>
								</li>
							</ul>
						</li>

					</ul>
					<a class="brand" href="index.html"><span class="first">欢迎登陆</span>
						<span class="second">美人帮管理端</span> </a>
				</div>
			</div>
		</div>


		<div class="container-fluid">

			<div class="row-fluid">
				<div class="span3">
					<div class="sidebar-nav">
						<div class="nav-header" data-toggle="collapse"
							data-target="#dashboard-menu">
							<i class="icon-dashboard"></i>用户管理
						</div>
						<ul id="dashboard-menu" class="nav nav-list collapse in">
							<li>
								<a href="user.jsp">用户列表</a>
							</li>
						</ul>

						<div class="nav-header" data-toggle="collapse"
							data-target="#dashboard-menu">
							<i class="icon-dashboard"></i>视频管理
						</div>
						<ul id="dashboard-menu" class="nav nav-list collapse in">
							<li>
								<a href="vcate.jsp">视频分类</a>
							</li>
							<li>
								<a href="video.jsp">视频列表</a>
							</li>
						</ul>

						<div class="nav-header" data-toggle="collapse"
							data-target="#dashboard-menu">
							<i class="icon-dashboard"></i>项目管理
						</div>
						<ul id="dashboard-menu" class="nav nav-list collapse in">
							<li>
								<a href="pcate.jsp">项目分类</a>
							</li>
							<li class="active">
								<a href="project.jsp">项目列表</a>
							</li>
							<li>
								<a href="brand.jsp">品牌列表</a>
							</li>
						</ul>

						<div class="nav-header" data-toggle="collapse"
							data-target="#dashboard-menu">
							<i class="icon-dashboard"></i>其他
						</div>
						<ul id="dashboard-menu" class="nav nav-list collapse in">
							<li>
								<a href="user.jsp">城市列表</a>
							</li>
						</ul>

						<div class="nav-header" data-toggle="collapse"
							data-target="#dashboard-menu">
							<i class="icon-dashboard"></i>Dashboard
						</div>
						<ul id="dashboard-menu" class="nav nav-list collapse in">
							<li>
								<a href="index.html">Home</a>
							</li>
							<li>
								<a href="user.html">Sample List</a>
							</li>
							<li>
								<a href="user.html">Sample Item</a>
							</li>
							<li>
								<a href="gallery.html">Gallery</a>
							</li>
							<li>
								<a href="calendar.html">Calendar</a>
							</li>
							<li>
								<a href="faq.html">Faq</a>
							</li>
							<li>
								<a href="help.html">Help</a>
							</li>

						</ul>
						<div class="nav-header" data-toggle="collapse"
							data-target="#accounts-menu">
							<i class="icon-briefcase"></i>Account
							<span class="label label-info">+10</span>
						</div>
						<ul id="accounts-menu" class="nav nav-list collapse in">
							<li>
								<a href="sign-in.html">Sign In</a>
							</li>
							<li>
								<a href="sign-up.html">Sign Up</a>
							</li>
							<li>
								<a href="reset-password.html">Reset Password</a>
							</li>
						</ul>

						<div class="nav-header" data-toggle="collapse"
							data-target="#settings-menu">
							<i class="icon-exclamation-sign"></i>Error Pages
						</div>
						<ul id="settings-menu" class="nav nav-list collapse in">
							<li>
								<a href="403.html">403 page</a>
							</li>
							<li>
								<a href="404.html">404 page</a>
							</li>
							<li>
								<a href="500.html">500 page</a>
							</li>
							<li>
								<a href="503.html">503 page</a>
							</li>
						</ul>

						<div class="nav-header" data-toggle="collapse"
							data-target="#legal-menu">
							<i class="icon-legal"></i>Legal
						</div>
						<ul id="legal-menu" class="nav nav-list collapse in">
							<li>
								<a href="privacy-policy.html">Privacy Policy</a>
							</li>
							<li>
								<a href="terms-and-conditions.html">Terms and Conditions</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="span9">
					<h1 class="page-title">
						所有项目
					</h1>
					<div class="btn-toolbar">
						<button class="btn btn-primary" onclick="gotoadd();">
							<i class="icon-plus"></i>添加项目
						</button>
						<div class="btn-group">
						</div>
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
					<!-- 位置重要 -->
					<form name="editpid" id="editpid" action="project.do" method="post">
						<input type="hidden" id="act" name="act" value="edit">
						<input type="hidden" id="msg" name="msg" value="">
					</form>
					<input type="hidden" id="pid" value="">
					<div class="well" id="projectgrid">
					</div>
					<div class="pagination">
						<ul>
							<li>
								<a href="#">上一页</a>
							</li>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">下一页</a>
							</li>
						</ul>
					</div>

					<div class="modal small hide fade" id="myModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								×
							</button>
							<h3 id="myModalLabel">
								删除确认
							</h3>
						</div>
						<div class="modal-body">
							<p class="error-text">
								<i class="icon-warning-sign modal-icon"></i>您确定删除该项目?
							</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">
								取消
							</button>
							<button class="btn btn-danger" data-dismiss="modal"
								onclick="delproject();">
								删除
							</button>
						</div>
					</div>

				</div>
			</div>

			<footer>
			<hr>
			<!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
			<p class="pull-right">
				A
				<a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free
					Bootstrap Theme</a> by
				<a href="http://www.portnine.com" target="_blank">Portnine</a>
			</p>

			<p>
				&copy; 2012
				<a href="http://www.portnine.com">Portnine</a>
			</p>
			</footer>

			<!-- Le javascript
    ================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->
			<script src="lib/bootstrap/js/bootstrap.js"></script>
	</body>
</html>


