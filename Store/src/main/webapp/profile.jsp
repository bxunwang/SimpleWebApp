<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>User Profile</title>
		<link href="register.css" rel="stylesheet" />
		
	</head>
	<body style="font-size:30px;">
		<header id="header">
			<a href="#">首頁</a>
			<a href="#">購物車</a>
			<a href="registerPage.do">註冊</a>
			<a href="loginPage.do">登入</a>
			<a href="logout.do">登出</a>
			<a href="profilePage.do">會員中心</a>
		</header>
		<div id="content">
			<h2>會員中心</h2>
			<form id="registerInformation">
				<table>
					<tr>
						<td class="words">密碼：</td>
						<td>
							<div id=""></div>
							<input id="username" name="username" type="text" placeholder="請輸入原本的密碼" onblur="oldPasswordCheck()">
							<div id="usernameAlert"></div>
						</td>
					</tr>
					<tr>
						<td class="words">新密碼：</td>
						<td>
							<div id=""></div>
							<input id="password" name="password" type="password" placeholder="請輸入新密碼"onblur="passwordCheck()">
							<div id="passwordAlert"></div>
						</td>
					</tr>
					<tr>
						<td class="words">密碼驗證：</td>
						<td>
							<div id=""></div>
							<input id="password2" name="password2" type="password" placeholder="請再次輸入新密碼"onblur="password2Check()">
							<div id="password2Alert"></div>
						</td>
					</tr>
					<tr>
						<td class="words">電子信箱：</td>
						<td>
							<div id="selectEmail"></div>
							<input id="email" name="email" type="text" onblur="emailCheck()">
							<div id="emailAlert"></div>
						</td>
					</tr>
					<tr>
						<td class="words">手機號碼：</td>
						<td>
							<div id="selectPhone"></div>
							<input id="phone" name="phone" type="text" onblur="phoneCheck()">
							<div id="phoneAlert"></div>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td>
							<input id="submitButton" type="button" value="確定" onclick="userRegister()">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<footer id="footer"></footer>
		
		<script src="jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			function oldPasswordCheck() {
				$.ajax({
					url: "usernameCheck.do",
					data: "username=" + $("#username").val(),
					type: "post",
					dataType: "json",
					success: function(obj){
						$("#usernameAlert").html(obj.message);
						if (obj.state == 1) {
							$("#usernameAlert").css("color", "green");
							$("#username").css("border-color", "initial");
							$("#username").css("border-width", "2px");
							$("#username").css("border-style", "inset");
						} else {
							$("#usernameAlert").css("color", "red");
							$("#username").css("border", "red 2px solid");
						}
					}
				});
			}
			
			function passwordCheck() {
				$.ajax({
					url: "passwordCheck.do",
					data: "password=" + $("#password").val(),
					type: "post",
					dataType: "json",
					success: function(obj){
						$("#passwordAlert").html(obj.message);
						if (obj.state == 1) {
							$("#passwordAlert").css("color", "green");
							$("#password").css("border-color", "initial");
							$("#password").css("border-width", "2px");
							$("#password").css("border-style", "inset");
						} else {
							$("#passwordAlert").css("color", "red");
							$("#password").css("border", "red 2px solid");
						}
					}
				});
			}
			
			function password2Check() {
				$.ajax({
					url: "password2Check.do",
					data: "password=" + $("#password").val() + "&password2=" + $("#password2").val(),
					type: "post",
					dataType: "json",
					success: function(obj){
						$("#password2Alert").html(obj.message);
						if (obj.state == 1) {
							$("#password2Alert").css("color", "green");
							$("#password2").css("border-color", "initial");
							$("#password2").css("border-width", "2px");
							$("#password2").css("border-style", "inset");
						} else {
							$("#password2Alert").css("color", "red");
							$("#password2").css("border", "red 2px solid");
						}
					}
				});
			}
			
			function emailCheck() {
				$.ajax({
					url: "emailCheck.do",
					data: "email=" + $("#email").val(),
					type: "post",
					dataType: "json",
					success: function(obj){
						$("#emailAlert").html(obj.message);
						if (obj.state == 1) {
							$("#emailAlert").css("color", "green");
							$("#email").css("border-color", "initial");
							$("#email").css("border-width", "2px");
							$("#email").css("border-style", "inset");
						} else {
							$("#emailAlert").css("color", "red");
							$("#email").css("border", "red 2px solid");
						}
					}
				});
			}
			
			function phoneCheck() {
				$.ajax({
					url: "phoneCheck.do",
					data: "phone=" + $("#phone").val(),
					type: "post",
					dataType: "json",
					success: function(obj){
						$("#phoneAlert").html(obj.message);
						if (obj.state == 1) {
							$("#phoneAlert").css("color", "green");
							$("#phone").css("border-color", "initial");
							$("#phone").css("border-width", "2px");
							$("#phone").css("border-style", "inset");
						} else {
							$("#phoneAlert").css("color", "red");
							$("#phone").css("border", "red 2px solid");
						}
					}
				});
			}
			
			function userRegister() {
				$.ajax({
					url: "userRegister.do",
					data: $("#registerInformation").serialize(),
					type: "post",
					dataType: "json",
					success: function(obj){
						alert(obj.message);
						if (obj.state == 1) {
							location.href = "loginPage.do";
						}
					}
				});
			}
			
		</script>
	</body>
</html>