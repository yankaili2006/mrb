<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mrb.bean.VideoReqBean"%>
<%@ page import="com.mrb.bean.VCateBean"%>
<%@ page import="com.mrb.bs.VCateBS"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>视频管理</title>
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
	function addvideo() {
		var exp = $('#entryid').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("视频ID不能为空");
		} else {

			exp = $('#vcid').val();
			if (!exp || typeof (exp) == "undefined" || exp == 0) {
				alert("视频分类不能为空!");
				$('#vcid').focus();
				return false;
			}

			$('#act').val("add");
			$('#msg').val(
					"{entryid:" + $('#entryid').val() + ",vcid:"
							+ $('#vcid').val() + "}");
			$('#addform').submit();
		}
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
							<li><a tabindex="-1" href="#">设置</a> <br></li>
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
						<li><a href="home.jsp">主页</a></li>
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
						<li class="active"><a href="video.jsp">视频列表</a></li>
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
			<div class="span9">
				<h1 class="page-title">添加视频</h1>
				<div class="btn-toolbar">
					<button class="btn btn-primary" onclick="addvideo();">
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
					<form id="addform" name="addform" action="video.do" method="post">
						<input type="hidden" id="act" name="act" value=""> <input
							type="hidden" id="msg" name="msg" value="">
					</form>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab">
								<%
									VCateBS vcateBS = new VCateBS();
									Integer cnt = vcateBS.getVCateCnt();
									ArrayList<VCateBean> vclist = vcateBS.getVCateList(0, cnt);

									Object obj = request.getAttribute("video");
									if (obj != null) {
										VideoReqBean bean = (VideoReqBean) obj;
								%>
								<label> 石山视频ID </label> <input type="text" name="entryid"
									id="entryid" value="<%=bean.getEntryid()%>"
									class="input-xlarge"> <label> 视频分类 </label><select
									name="vcid" id="vcid" class="input-xlarge">
									<%
										if (vclist != null && vclist.size() > 0) {
												for (int i = 0; i < vclist.size(); i++) {
													VCateBean vcbean = vclist.get(i);
									%>
									<option value="<%=vcbean.getVcid()%>"
										<%if (vcbean != null
								&& bean.getVcid() == vcbean.getVcid()) {
							out.print("selected=\"selected\"");
						}%>>
										<%=vcbean.getName()%></option>
									<%
										}
											} else {
									%>
									<option value="-1">无</option>
									<%
										}
									%>
									<%
										} else {
									%>
									<label> 视频ID </label>
									<input type="text" name="entryid" id="entryid" value=""
									class="input-xlarge">
									<label> 视频分类 </label>
									<select name="vcid" id="vcid" class="input-xlarge">
										<%
											if (vclist != null && vclist.size() > 0) {
													for (int i = 0; i < vclist.size(); i++) {
														VCateBean vcbean = vclist.get(i);
										%>
										<option value="<%=vcbean.getVcid()%>">
											<%=vcbean.getName()%></option>
										<%
											}
												} else {
										%>
										<option value="-1">无</option>
										<%
											}
										%>
										<%
											}
										%>
								
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
			&copy; 2014 <a href="#">美人邦</a>
		</p>
		</footer>




		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>


