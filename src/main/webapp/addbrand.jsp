<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mrb.bean.Brand2ShowBean"%>
<%@ page import="com.mrb.bean.ProjectBean"%>
<%@ page import="com.mrb.bs.ProjectBS"%>
<%
	//项目列表
	ProjectBS pbs = new ProjectBS();
	ArrayList<ProjectBean> plist = pbs.getProjectList(0,5);
	String result = null;
	
	Object robj = request.getAttribute("result");
	if (robj != null) {
		result = (String) robj;
	}
	
	if (plist==null || plist.size() <=0){
		result = "请先添加项目";
	}
%>
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
	function addbrand() {

		var exp = $('#btitle').val();
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

		$('#act').val("add");
		$('#msg').val(
				"{pid:" + $('#pid').val() + ",bid:" + $('#bid').val()
						+ ",btitle:\"" + $('#btitle').val() + "\",binfo:\""
						+ $('#binfo').val() + "\",iuri:\"" + iuri
						+ "\",name:\"" + $('#name').val() + "\",price:\""
						+ $('#price').val() + "\",function:\""
						+ $('#function').val() + "\",summary:\""
						+ $('#summary').val() + "\"}");
		$('#addform').submit();
	}

	function upload() {
		var exp = $('#uploadfile').val();
		if (!exp || typeof (exp) == "undefined" || exp == 0) {
			alert("请选择图片");
			$('#uploadfile').focus();
			return false;
		}

		var iuri = $('#uploadfile').val();
		if (iuri.indexOf("\\") != -1) {
			iuri = iuri.substring(iuri.lastIndexOf("\\") + 1, iuri.length);
		}
		if (iuri.indexOf("/") != -1) {
			iuri = iuri.substring(iuri.lastIndexOf("/") + 1, iuri.length);
		}

		$('#uploadform').submit();
		$('#iuri').val(iuri);
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
							<li class="divider">u <br>
							</li>
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
						<li><a href="bimg.jsp">品牌图片列表</a></li>
					</ul>
					
					<div class="nav-header" data-toggle="collapse"
						data-target="#param-menu">
						<i class="icon-globe"></i>系统配置
					</div>
					<ul id="param-menu" class="nav nav-list collapse in">
						<li><a href="param.jsp">参数</a></li>
						<li><a href="modelimg.jsp">模块图片列表</a></li>
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
				<h1 class="page-title">添加品牌</h1>
				<div class="btn-toolbar">
					<button class="btn btn-primary" onclick="addbrand();">
						<i class="icon-save"></i>保存
					</button>
					<div class="btn-group"></div>
				</div>
				<%
					if (result == null || "".equals(result)) {
						;
					} else if ("ok".equals(result)) {
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
				%>
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">基本信息</a>
						</li>
					</ul>

					<form id="addform" name="addform" action="brand.do" method="post">
						<input type="hidden" id="act" name="act" value=""> <input
							type="hidden" id="msg" name="msg" value="">
					</form>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<form id="tab" name="tab">
								<%
									Object obj = request.getAttribute("brand");
									if (obj != null) {
										Brand2ShowBean bean = (Brand2ShowBean) obj;
								%>
								<label> 品牌标题 </label> <input type="text" name="btitle"
									id="btitle" value="<%=bean.getBtitle()%>" class="input-xlarge">
								<label> 所属项目 </label><select name="pid" id="pid"
									class="input-xlarge">

									<%
										if (plist != null && plist.size() > 0) {
												for (int i = 0; i < plist.size(); i++) {
													ProjectBean pbean = plist.get(i);
									%>
									<option value="<%=pbean.getPid()%>"
										<%if (pbean != null && bean.getPid() == pbean.getPid()) {
							out.print("selected=\"selected\"");
						}%>>
										<%=pbean.getName()%></option>
									<%
										}
											} else {
									%>
									<option value="-1">无</option>
									<%
										}
									%>
								</select> <label> 产品简介 </label>
								<textarea name="binfo" id="binfo" class="form-control" rows="3"><%=bean.getBinfo()%></textarea>

								<label> 品牌图标 </label> <input type="text" name="iuri" id="iuri"
									value="<%=bean.getIuri()%>" class="input-xlarge">
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
									rows="5"><%=bean.getSummary()%></textarea>


								<input type="hidden" id="bid" name="bid"
									value="<%=bean.getBid()%>">
								<%
									} else {
								%>
								<label> 品牌标题 </label> <input type="text" name="btitle"
									id="btitle" value="" class="input-xlarge"> <label>
									所属项目 </label><select name="pid" id="pid" class="input-xlarge">

									<%
										if (plist != null && plist.size() > 0) {
												for (int i = 0; i < plist.size(); i++) {
													ProjectBean pbean = plist.get(i);
									%>
									<option value="<%=pbean.getPid()%>"><%=pbean.getName()%></option>
									<%
										}
											} else {
									%>
									<option value="-1">无</option>
									<%
										}
									%>
								</select> <label> 产品简介 </label>
								<textarea name="binfo" id="binfo" class="form-control" rows="5"></textarea>

								<label> 品牌图标 </label> <input type="text" name="iuri" id="iuri"
									value="" class="input-xlarge">
								<button class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#uploadModal">上传图片</button>
								<label> 名称 </label> <input type="text" name="name" id="name"
									value="" class="input-xlarge"> <label> 价格 </label> <input
									type="text" name="price" id="price" value=""
									class="input-xlarge"> <label> 产品功效 </label> <input
									type="text" name="function" id="function" value=""
									class="input-xlarge"> <label> 其他说明 </label>
								<textarea name="summary" id="summary" class="form-control"
									rows="5"></textarea>

								<input type="hidden" id="bid" name="bid" value="0">
								<%
									}
								%>
							</form>
						</div>
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
			&copy; 2014 <a href="#">美人邦</a>
		</p>
		</footer>

		<!-- Le javascript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>


