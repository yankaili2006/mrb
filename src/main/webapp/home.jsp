<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java"
	import="com.mrb.bs.UserBS,com.mrb.bs.VideoBS,com.mrb.bs.ProjectBS,com.mrb.bs.OperateBS"%>
<%@ page language="java"
	import="com.mrb.bean.UserBean,com.mrb.bean.OperateBean"%>
<%@ page language="java" import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>管理端</title>
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

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body>
	<!--<![endif]-->
	<%
		Long uid = (Long) session.getAttribute("uid");
		if (uid == null || uid <= 0) {
			response.sendRedirect("admin.jsp");
		}

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
							<li><a tabindex="-1" href="#">设置</a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href="loginout.do">退出</a></li>
						</ul></li>

				</ul>
				<a class="brand" href="home.do"><span class="first">欢迎登陆</span>
					<span class="second">美人邦管理端</span> </a>
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
						<li class="active"><a href="home.jsp">主页</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#user-menu">
						<i class="icon-user"></i>用户管理
					</div>
					<ul id="user-menu" class="nav nav-list collapse in">
						<li><a href="user.jsp">用户列表</a></li>
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
						<li><a href="bimg.jsp">品牌图片列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#param-menu">
						<i class="icon-globe"></i>系统配置
					</div>
					<ul id="param-menu" class="nav nav-list collapse in">
						<li><a href="param.jsp">参数</a></li>
					</ul>
					
					<div class="nav-header" data-toggle="collapse"
						data-target="#legal-menu">
						<i class="icon-legal"></i>法律协议
					</div>
					<ul id="legal-menu" class="nav nav-list collapse in">
						<li><a href="privacy.jsp">版权说明</a></li>
						<li><a href="terms.jsp">美人邦使用协议</a></li>
					</ul>
				</div>
			</div>
			<%
				UserBS userBS = new UserBS();
				VideoBS videoBS = new VideoBS();
				ProjectBS proBS = new ProjectBS();
			%>
			<div class="span9">
				<script type="text/javascript" src="lib/jqplot/jquery.jqplot.min.js"></script>
				<script type="text/javascript" charset="utf-8"
					src="javascripts/graphDemo.js"></script>

				<div class="stats">
					<p class="stat">
						<span class="number"><%=userBS.getUserCnt()%></span>用户
					</p>
					<p class="stat">
						<span class="number"><%=proBS.getProjectCnt()%></span>项目
					</p>
					<p class="stat">
						<span class="number"><%=videoBS.getVideoCnt()%></span>视频
					</p>
				</div>
				<h1 class="page-title">主页</h1>

				<div class="row-fluid">
					<div class="block">
						<p class="block-heading" data-toggle="collapse"
							data-target="#chart-container">动态趋势</p>
						<div id="chart-container" class="block-body collapse in">
							<div id="line-chart"></div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse"
							data-target="#tablewidget">用户</div>
						<div id="tablewidget" class="block-body collapse in">
							<table class="table">
								<thead>
									<tr>
										<th>用户名</th>
										<th>手机号</th>
										<th>注册时间</th>
									</tr>
								</thead>
								<tbody>
									<%
										ArrayList<UserBean> ulist = userBS.getUserList(0, 6);
										if (ulist != null && ulist.size() > 0) {
											for (int i = 0; i < ulist.size(); i++) {
												UserBean ubean = ulist.get(i);
									%>
									<tr>
										<td><%=ubean.getUname()%></td>
										<td><%=ubean.getPhone()%></td>
										<td><%=ubean.getDate()%></td>
									</tr>
									<%
										}
										} else {
									%>
									<tr>
										<td></td>
										<td>没有注册用户</td>
										<td></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
							<p>
								<a href="user.jsp">更多</a>
							</p>
						</div>
					</div>
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse"
							data-target="#widget1container">通知</div>
						<div id="widget1container" class="block-body collapse in">
							<h2>通知标题</h2>
							<p>恭喜美人邦正式上线！</p>
						</div>
					</div>
				</div>
				<%
					OperateBS operBS = new OperateBS();
					ArrayList<OperateBean> operList = operBS.getOperateList(0, 6);
				%>
				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse"
							data-target="#widget2container">
							操作历史<span class="label label-warning">+<%=operBS.getOperateCnt()%></span>
						</div>
						<div id="widget2container" class="block-body collapse in">
							<table class="table">
								<thead>
									<tr>
										<th>用户名</th>
										<th>操作</th>
										<th>时间</th>
									</tr>
								</thead>
								<tbody>
									<%
										if (operList != null && operList.size() > 0) {
											for (int i = 0; i < operList.size(); i++) {
												OperateBean operBean = operList.get(i);
									%>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i><%=operBean.getUname()%>
											</p>
										</td>
										<td>
											<p><%=operBean.getOper()%></p>
										</td>
										<td>
											<p><%=operBean.getDate()%></p>
										</td>
									</tr>
									<%
										}
										} else {
									%>
									<tr>
										<td></td>
										<td>
											<p>没有操作记录</p>
										</td>
										<td></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
					<div class="block span6">
						<p class="block-heading">使用说明</p>
						<div class="block-body">
							<h2>如何添加视频？</h2>
							<p>请先将视频上传至石山视频(www.smvp.cn),生成一个唯一ID号，点击菜单中的[视频管理]->[视频列表]->[添加视频]。录入在石山视频的唯一的ID号，系统自动获取该视频的播放地址，简介等详细信息。添加成功后，可以修改该视频的部分信息。










							
							<p>
								<a class="btn btn-primary btn-large">了解更多 &raquo;</a>
							</p>
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
			&copy; 2014 <a href="#">美人邦</a>
		</p>
		</footer>




		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>