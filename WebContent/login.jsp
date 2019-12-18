<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>Sign-Up/Login Form</title>

<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
<%-- 弹出警告框 --%>
<link rel="stylesheet" type="text/css" href="css/bootoast.css">

</head>

<body>
	<!-- partial:index.partial.html -->
	<div class="form">

		<ul class="tab-group">
			<li class="tab "><a href="#signup">注册</a></li>
			<li class="tab active"><a href="#login">登录</a></li>
		</ul>

		<div class="tab-content">

			<div id="login">

				<h1>欢迎回来！</h1>

				<form action="LoginServlet" method="post">

					<div class="field-wrap">
						<label> 邮箱<span class="req">*</span>
						</label> <input type="email" name="loginemail" required autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> 密码<span class="req">*</span>
						</label> <input type="password" name="loginpassword" required
							autocomplete="off" />
					</div>

					<p class="forgot">
						<a href="#">忘记密码</a>
					</p>

					<button id="loginbtn" class="button button-block">登录</button>

				</form>

			</div>


			<div id="signup">
				<h1>注册账户</h1>

				<form action="RegisterServlet" method="post">


					<div class="field-wrap">
						<label> 用户名<span class="req">*</span>
						</label> <input type="text" name="username" required autocomplete="off" />
					</div>


					<div class="field-wrap">
						<label> 邮箱<span class="req">*</span>
						</label> <input type="email" name="email" id="register_email" onblur="registerTest()" required autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> 密码<span class="req">*</span>
						</label> <input type="password" name="password" required
							autocomplete="off" />
					</div>

					<button type="submit" id="register_btn" class="button button-block" />
					Get Started
					</button>
				</form>
			</div>
		</div>
		<!-- tab-content -->
	</div>
	<!-- /form -->
	<!-- partial -->
	<%-- <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> --%>
	
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
	<script type="text/javascript" src="js/bootoast.js"></script>
	<script src="./js/login.js"></script>

	<script>
	<% 
		Boolean isLoginSuccess=(Boolean)session.getAttribute("isLoginSuccess");
	
		if(isLoginSuccess!=null && !isLoginSuccess){
	%>
		bootoast({
          message: '用户名或密码错误！',
          type: 'danger',
          position: 'top-center',
          timeout: 2
        });
	<% 
		}
	%>
 </script>

</body>

</html>