<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mrb.bean.ProjectBean"%>

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
	function delproject() {
		$('#act').val("del");
		$('#msg').val("{pid:" + $('#pid').val() + "}");
		$('#updateform').submit();
	}

	function updateproject() {
		$('#act').val("update");
		$('#msg').val(
				"{pid:" + $('#pid').val() + ",name:" + $('#name').val()
						+ ",iid:" + $('#iid').val() + ",level:"
						+ $('#level').val() + ",area:" + $('#area').val()
						+ ",store:" + $('#store').val() + ",build:"
						+ $('#build').val() + ",pack:" + $('#pack').val()
						+ ",sale:" + $('#sale').val() + ",chain:"
						+ $('#chain').val() + ",fee:" + $('#fee').val()
						+ ",date:" + $('#date').val() + "}");
		$('#updateform').submit();
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
							<li class="divider"></li>
							<li><a tabindex="-1" href="loginout.do">退出</a></li>
						</ul></li>

				</ul>
				<a class="brand" href="home.do"><span class="first">pdpda欢迎登陆</span>
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
						<li><a href="user.jsp">用户列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#dashboard-menu">
						<i class="icon-dashboard"></i>视频管理
					</div>
					<ul id="dashboard-menu" class="nav nav-list collapse in">
						<li><a href="vcate.jsp">视频分类</a></li>
						<li><a href="video.jsp">视频列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#dashboard-menu">
						<i class="icon-dashboard"></i>项目管理
					</div>
					<ul id="dashboard-menu" class="nav nav-list collapse in">
						<li><a href="pcate.jsp">项目分类</a></li>
						<li class="active"><a href="project.jsp">项目列表</a></li>
						<li><a href="brand.jsp">品牌列表</a></li>
					</ul>

					<div class="nav-header" data-toggle="collapse"
						data-target="#dashboard-menu">
						<i class="icon-dashboard"></i>管店管理
					</div>
					<ul id="dashboard-menu" class="nav nav-list collapse in">
						<li><a href="user.jsp">城市列表</a></li>
					</ul>

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
				<h1 class="page-title">编辑项目</h1>
				<div class="btn-toolbar">
					<button class="btn btn-primary" onclick="updateproject();">
						<i class="icon-save"></i>保存
					</button>
					<a href="#myModal" data-toggle="modal" class="btn">删除</a>
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
					<form id="updateform" name="updateform" action="project.do"
						method="post">
						<input type="hidden" id="act" name="act" value=""> <input
							type="hidden" id="msg" name="msg" value="">
					</form>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab">
								<%
									Object obj = request.getAttribute("project");
									if (obj != null) {
										ProjectBean bean = (ProjectBean) obj;
								%>
								<label> 项目名 </label> <input type="text" name="name" id="name"
									value="<%=bean.getName()%>" class="input-xlarge"> <label>
									图标 </label> <input type="text" name="iid" id="iid"
									value="<%=bean.getIid()%>" class="input-xlarge"> <label>
									定位 </label> <input type="text" name="level" id="level"
									value="<%=bean.getLevel()%>" class="input-xlarge"> <label>
									生存地址 </label> <input type="text" name="area" id="area"
									value="<%=bean.getArea()%>" class="input-xlarge"> <label>
									店面类型 </label> <input type="text" name="store" id="store"
									value="<%=bean.getStore()%>" class="input-xlarge"> <label>
									成立时间 </label> <input type="text" name="build" id="build"
									value="<%=bean.getBuild()%>" class="input-xlarge"> <label>
									产品包装 </label> <input type="text" name="pack" id="pack"
									value="<%=bean.getPack()%>" class="input-xlarge"> <label>
									销售模式 </label> <input type="text" name="sale" id="sale"
									value="<%=bean.getSale()%>" class="input-xlarge"> <label>
									是否下店扶持 </label> <select name="chain" id="chain" class="input-xlarge">
									<option value="1"
										<%if (1 == bean.getChain()) {
					out.print("selected=\"selected\"");
				}%>>
										是</option>
									<option value="0"
										<%if (0 == bean.getChain()) {
					out.print("selected=\"selected\"");
				}%>>
										否</option>
								</select> <label> 是否有加盟费 </label> <select name="fee" id="fee"
									class="input-xlarge">
									<option value="1"
										<%if (1 == bean.getFee()) {
					out.print("selected=\"selected\"");
				}%>>
										是</option>
									<option value="0"
										<%if (0 == bean.getFee()) {
					out.print("selected=\"selected\"");
				}%>>
										否</option>
								</select> <label> 修改时间 </label> <input type="text" name="date" id="date"
									value="<%=bean.getDate()%>" class="input-xlarge"> <input
									type="hidden" id="pid" name="pid" value="<%=bean.getPid()%>">
								<%
									} else {
								%>
								<label> 项目名 </label> <input type="text" name="name" id="name"
									value="" class="input-xlarge"> <label> 图标 </label> <input
									type="text" name="iid" id="iid" value="" class="input-xlarge">
								<label> 定位 </label> <input type="text" name="level" id="level"
									value="" class="input-xlarge"> <label> 生存地址 </label> <input
									type="text" name="area" id="area" value="" class="input-xlarge">
								<label> 店面类型 </label> <input type="text" name="store" id="store"
									value="" class="input-xlarge"> <label> 成立时间 </label> <input
									type="text" name="build" id="build" value=""
									class="input-xlarge"> <label> 产品包装 </label> <input
									type="text" name="pack" id="pack" value="" class="input-xlarge">
								<label> 销售模式 </label> <input type="text" name="sale" id="sale"
									value="" class="input-xlarge"> <label> 是否下店扶持 </label>
								<select name="chain" id="chain" class="input-xlarge">
									<option value="1">是</option>
									<option value="0">否</option>
								</select> <label> 是否有加盟费 </label> <select name="fee" id="fee"
									class="input-xlarge">
									<option value="1">是</option>
									<option value="0">否</option>
								</select> <label> 修改时间 </label> <input type="text" name="date" id="date"
									value="" class="input-xlarge"> <input type="hidden"
									id="pid" name="pid" value="">
								<%
									}
								%>
							</form>
						</div>
					</div>

				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">删除确认</h3>
					</div>
					<div class="modal-body">

						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>您确定删除该项目?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">
							取消</button>
						<button class="btn btn-danger" data-dismiss="modal"
							onclick="delproject();">删除</button>
					</div>
				</div>

			</div>
		</div>



		<footer>
		<hr>
		<p class="pull-right"><a href="#" target="_blank">技术支持</a> by <a href="#" target="_blank">YKLI</a>
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


