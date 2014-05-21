<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mrb.pbs.PwdBS"%>

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

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script type="text/javascript">
	$(function() {
		// 发送手机校验码
		$('#snd').click(
				function() {
					if ($('#snd_phone').val() == '') {
						alert("请输入手机号");
						return false;
					}
					$.post("code.do?act=snd&msg={phone:"
							+ $('#snd_phone').val() + "}", function(data) {
						alert(data);
						var obj = eval('(' + data + ')');
						$('#chk_chkcode').val(obj.chkcode);
						$('#chk_phone').val(obj.phone);
					});
				});

		// 校验
		$('#chk').click(
				function() {
					if ($('#chk_phone').val() == '') {
						alert("请输入手机号");
						return false;
					}
					if ($('#chk_chkcode').val() == '') {
						alert("请输入校验码");
						return false;
					}

					$.post("code.do?act=chk&msg={phone:"
							+ $('#chk_phone').val() + ",chkcode:"
							+ $('#chk_chkcode').val() + "}", function(data) {
						alert(data);
					});
				});
		// 修改密码
		$('#chg').click(
				function() {
					$.post("pwd.do?act=chg&msg={uid:" + $('#chg_uid').val()
							+ ",oldpwd:" + $('#chg_oldpwd').val() + ",newpwd:"
							+ $('#chg_newpwd').val() + "}", function(data) {
						alert(data);
					});
				});
		//重置密码
		$('#set').click(
				function() {
					$.post("pwd.do?act=set&msg={uid:" + $('#set_uid').val()
							+ ",newpwd:" + $('#set_newpwd').val() + "}",
							function(data) {
								alert(data);
							});
				});
		// 注册
		$('#reg').click(
				function() {
					$.post("user.do?act=register&msg={uname:"
							+ $('#reg_uname').val() + ",phone:"
							+ $('#reg_phone').val() + ",pwd:"
							+ $('#reg_pwd').val() + "}", function(data) {
						alert(data);
					});
				});

		// 登录
		$('#login').click(
				function() {
					$.post("user.do?act=login&msg={phone:"
							+ $('#login_phone').val() + ",pwd:"
							+ $('#login_pwd').val() + "}", function(data) {
						alert(data);
					});
				});

		// 获取视频分类
		$('#vcate').click(
				function() {
					$.post("v.do?act=category&msg={num:"
							+ $('#vcate_num').val() + "}", function(data) {
						alert(data);
					});
				});
		//获取视频列表
		$('#vlist').click(
				function() {
					$.post("v.do?act=list&msg={num:" + $('#vlist_num').val()
							+ ",vcid:" + $('#vlist_vcid').val() + ",start:"
							+ $('#vlist_start').val() + "}", function(data) {
						alert(data);
					});
				});
		//获取视频详情
		$('#vdetail').click(
				function() {
					$.post("v.do?act=detail&msg={uid:"
							+ $('#vdetail_uid').val() + ",vid:"
							+ $('#vdetail_vid').val() + "}", function(data) {
						alert(data);
					});
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
<body>
	<div class="row-fluid">
		<div class="row">
			<h2>手机校验码</h2>
			<hr>
			<div class="span4">
				<h4>发送</h4>
				<label>phone</label> <input type="text" id="snd_phone"
					name="snd_phone" value=""> <br>
				<button id="snd" name="snd" class="btn" data-dismiss="modal"
					aria-hidden="true">发送</button>
			</div>

			<div class="span4">
				<h4>校验</h4>
				<label>chkcode</label> <input type="text" id="chk_chkcode"
					name="chk_chkcode" value=""> <br> <label>phone</label>
				<input type="text" id="chk_phone" name="chk_phone" value="">
				<br>
				<button id="chk" name="chk" class="btn btn-danger"
					data-dismiss="modal">校验</button>
			</div>
		</div>

		<div class="row">
			<h2>密码</h2>
			<hr>
			<%
				PwdBS pwdBs = new PwdBS();
				Long uid = 562869L;
			%>
			<div class="span4">
				<h4>修改</h4>
				<label>uid</label> <input type="text" id="chg_uid" name="chg_uid"
					value="<%=uid%>"> <br> <label>oldpwd</label> <input
					type="text" id="chg_oldpwd" name="chg_oldpwd"
					value="<%=pwdBs.getPwdByUid(uid)%>"> <br> <label>newpwd</label>
				<input type="text" id="chg_newpwd" name="chg_newpwd" value="">
				<br>
				<button id="chg" name="chg" class="btn" data-dismiss="modal"
					aria-hidden="true">修改</button>
			</div>
			<div class="span4">
				<h4>重置</h4>
				<label>uid</label> <input type="text" id="set_uid" name="set_uid"
					value="<%=uid%>"> <br> <label>newpwd</label> <input
					type="text" id="set_newpwd" name="set_newpwd"
					value="<%=pwdBs.getPwdByUid(uid)%>"> <br>
				<button id="set" name="set" class="btn btn-danger"
					data-dismiss="modal">重置</button>
			</div>
		</div>


		<div class="row">
			<h2>用户</h2>
			<hr>
			<div class="span4">
				<h4>注册</h4>
				<label>uname</label> <input type="text" id="reg_uname"
					name="reg_uname"> <br> <label>phone</label> <input
					type="text" id="reg_phone" name="reg_phone"> <br> <label>pwd</label>
				<input type="text" id="reg_pwd" name="reg_pwd" value=""> <br>
				<button id="reg" name="reg" class="btn" data-dismiss="modal"
					aria-hidden="true">注册</button>
			</div>
			<div class="span4">
				<h4>登录</h4>
				<label>phone</label> <input type="text" id="login_phone"
					name="login_phone"> <br> <label>pwd</label> <input
					type="text" id="login_pwd" name="login_pwd" value=""> <br>
				<button id="login" name="login" class="btn" data-dismiss="modal"
					aria-hidden="true">登录</button>
			</div>
		</div>

		<%
			String vid = "593727852328384587";
			String vcid = "648194";
		%>
		<div class="row">
			<h2>视频</h2>
			<hr>
			<div class="span4">
				<h4>视频分类</h4>
				<label>num</label> <input type="text" id="vcate_num"
					name="vcate_num" value=""> <br>
				<button id="vcate" name="vcate" class="btn" data-dismiss="modal"
					aria-hidden="true">获取视频分类</button>
			</div>
			<div class="span4">
				<h4>获取视频列表</h4>
				<label>vcid</label> <input type="text" id="vlist_vcid"
					name="vlist_vcid" value="<%=vcid%>"> <br> <label>start</label>
				<input type="text" id="vlist_start" name="vlist_start" value="">
				<br>
				<label>num</label> <input type="text" id="vlist_num"
					name="vlist_num" value=""> <br>
				<button id="vlist" name="vlist" class="btn" data-dismiss="modal"
					aria-hidden="true">获取视频</button>
			</div>

			<div class="span4">
				<h4>获取视频详细信息</h4>
				<label>uid</label> <input type="text" id="vdetail_uid"
					name="vdetail_uid" value="<%=uid%>"> <br> <label>vid</label>
				<input type="text" id="vdetail_vid" name="vdetail_vid"
					value="<%=vid%>"> <br>
				<button id="vdetail" name="vdetail" class="btn" data-dismiss="modal"
					aria-hidden="true">视频详情</button>
			</div>
		</div>
</body>
</html>