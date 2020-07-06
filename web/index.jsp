<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>百千万行知人才培养项目“行知双百”定向班学生管理系统</title>
	<script type="text/javascript">
		function resetValue() {
			document.getElementById("userName").value = "";
			document.getElementById("password").value = "";
		}
	</script>
	<style>
		.left{
			float: left;
			height: 100%;
		}

		.right {
			float: right;
		}

		.poster {
			width: 600px;
			height: 500px;
			background-image: url('images/2.jpg');
			background-size: 100% 100%;
			background-repeat: no-repeat;
		}

		.clearfix:after{
			content: "";
			display: block;
			height: 0;
			clear: both;
			visibility: hidden;
		}
	</style>
</head>
<body>
<div align="center" class="clearfix" style="padding: 50px;">
	<div class="left">
		<div class="poster">

		</div>
		<p class="tips">
			123123123
		</p>
	</div>
	<form class="right" action="login" method="post">
		<table width="740" height="500">
			<tr height="180">
				<td colspan="4" style="text-align: center;padding-top: 200px;color: white">百千万行知人才培养项目“行知双百”定向班学生管理系统</td>
			</tr>


			<tr height="20">
				<td width="30%"></td>
				<td width="10%" style="color: white;padding-top: 50px">用户名：</td>
				<td style="margin-top: 50px;padding-top: 50px"><input type="text" value="${userName }" name="userName" id="userName" /></td>
				<td width="20%"></td>
			</tr>
			<tr height="10">
				<td width="30%"></td>
				<td width="10%" style="color: white">密 码：</td>
				<td><input type="password" value="${password }" name="password" id="password" /></td>
				<td width="20%"></td>
			</tr>
			<tr height="10">
				<td width="40%"></td>
				<td width="10%" style="margin-top: 10px"><input type="submit" value="登录" style="margin-top: 10px;margin-left: 20px" /></td>
				<td><input type="button" value="重置" onclick="resetValue()" style="margin-top: 10px" /></td>
				<td width="30%"></td>
			</tr>
			<tr height="10">
				<td width="40%"></td>
				<td colspan="3">
					<font color="red">${error }</font>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
