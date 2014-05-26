<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mrb.bean.Brand2ShowBean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>品牌管理</title>
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
	function delbrand() {
		var exp = $('#bid').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("品牌ID不能为空!");
			return false;
		}

		$('#act').val("del");
		$('#msg').val("{bid:" + $('#bid').val() + "}");
		$('#updateform').submit();

	}

	function updatebrand() {

		var exp = $('#bid').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("品牌ID不能为空!");
			return false;
		}

		exp = $('#btitle').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("品牌标题不能为空!");
			$('#btitle').focus();
			return false;
		}

		exp = $('#pid').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("所属项目不能为空!");
			$('#pid').focus();
			return false;
		}

		exp = $('#binfo').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("产品简介不能为空!");
			$('#binfo').focus();
			return false;
		}

		exp = $('#iuri').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("品牌图标不能为空!");
			$('#iuri').focus();
			return false;
		}

		exp = $('#name').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("名称不能为空!");
			$('#name').focus();
			return false;
		}

		exp = $('#price').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("价格不能为空!");
			$('#price').focus();
			return false;
		}

		exp = $('#function').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("产品功效不能为空!");
			$('#function').focus();
			return false;
		}

		exp = $('#summary').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("其他说明不能为空!");
			$('#summary').focus();
			return false;
		}

		var iuri = $('#iuri').val();
		if (iuri.indexOf("\\") != -1) {
			iuri = iuri.substring(iuri.lastIndexOf("\\") + 1, iuri.length);
		}
		if (iuri.indexOf("/") != -1) {
			iuri = iuri.substring(iuri.lastIndexOf("/") + 1, iuri.length);
		}

		$('#act').val("update");
		$('#msg').val(
				"{pid:" + $('#pid').val() + ",bid:" + $('#bid').val()
						+ ",btitle:\"" + $('#btitle').val() + "\",binfo:\""
						+ $('#binfo').val() + "\",iuri:\"" + iuri
						+ "\",name:\"" + $('#name').val() + "\",price:\""
						+ $('#price').val() + "\",function:\""
						+ $('#function').val() + "\",summary:\""
						+ $('#summary').val() + "\"}");
		$('#updateform').submit();
	}

	function upload() {
		var exp = $('#uploadfile').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("请选择图片");
			$('#uploadfile').focus();
			return false;
		}

		$('#uploadform').submit();

		var iuri = $('#uploadfile').val();
		if (iuri.indexOf("\\") != -1) {
			iuri = iuri.substring(iuri.lastIndexOf("\\") + 1, iuri.length);
		}
		if (iuri.indexOf("/") != -1) {
			iuri = iuri.substring(iuri.lastIndexOf("/") + 1, iuri.length);
		}

		var filePath = "http://localhost:8080/mrb/files/";
		$('#iuri').val(iuri);
		$('#imguri').attr("src", filePath + iuri);
		$('#uploadModal').modal('hide');
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
						<li class="active"><a href="brand.jsp">品牌列表</a></li>
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
				<h1 class="page-title">编辑品牌</h1>
				<div class="btn-toolbar">
					<button class="btn btn-primary" onclick="updatebrand();">
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
					<form id="updateform" name="updateform" action="brand.do"
						method="post">
						<input type="hidden" id="act" name="act" value=""> <input
							type="hidden" id="msg" name="msg" value="">
					</form>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab">
								<%
									String filePath = "http://localhost:8080/mrb/files/";
									Object obj = request.getAttribute("brand");
									if (obj != null) {
										Brand2ShowBean bean = (Brand2ShowBean) obj;
								%>
								<label> 品牌标题 </label> <input type="text" name="btitle"
									id="btitle" value="<%=bean.getBtitle()%>" class="input-xlarge">

								<label> 所属项目 </label> <input type="text" name="pid" id="pid"
									value="<%=bean.getPid()%>" class="input-xlarge"> <label>
									产品简介 </label>
								<textarea name="binfo" id="binfo" class="form-control" rows="3"><%=bean.getBinfo()%></textarea>

								<label> 品牌图标 </label><img id="imguri" name="imguri" alt=""
									width="100" height="60" src="<%=filePath + bean.getIuri()%>"></img><input
									type="text" name="iuri" id="iuri" value="<%=bean.getIuri()%>"
									class="input-xlarge" style="display: none;">
								<button class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#uploadModal">上传图片</button>
								<label> 名称 </label> <input type="text" name="name" id="name"
									value="<%=bean.getName()%>" class="input-xlarge"> <label>
									价格 </label> <input type="text" name="price" id="price"
									value="<%=bean.getPrice()%>" class="input-xlarge"> <label>
									产品功效 </label> <input type="text" name="function" id="function"
									value="<%=bean.getFunction()%>" class="input-xlarge"> <label>
									其他说明 </label>
								<textarea name="summary" id="summary" class="form-control"
									rows="3"><%=bean.getSummary()%></textarea>
								<input type="hidden" id="bid" name="bid"
									value="<%=bean.getBid()%>">
								<%
									} else {
								%>
								<label> 未找到对应品牌 </label>
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
							<i class="icon-warning-sign modal-icon"></i>您确定删除该品牌?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">
							取消</button>
						<button class="btn btn-danger" data-dismiss="modal"
							onclick="delbrand();">删除</button>
					</div>
				</div>

			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">上传图片</h4>
					</div>
					<div class="modal-body">
						<form action="UploadServlet" method="post"
							enctype="multipart/form-data" target="post_frame" id="uploadform"
							name="uploadform">
							<input type="file" class="btn btn-primary" name="uploadfile"
								id="uploadfile" size="100" /> <input type="submit"
								class="btn btn-primary" value="upload" style="display: none;" />
						</form>
						<iframe name='post_frame' id="post_frame" style="display: none;"></iframe>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" onclick="upload()">上传</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

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


