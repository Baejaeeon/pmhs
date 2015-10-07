<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지 </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.mmain.min.css" rel="stylesheet" type="text/css">
<link href="font-awesome/css/font-awesome.mmain.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.10.2.mmain.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.mmain.js" type="text/javascript"></script>
<style>
.row {
	padding: 20px 0px;
}


.contcustom {
	text-align: center;
	width: 500px;
	border-radius: 0.5rem;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: 10px auto;
	background-color: white;
	padding: 20px;
}

input {
	width: 80%;
	margin-bottom: 17px;
	padding: 15px;
	background-color: #ECF4F4;
	border-radius: 2px;
	border: none;
}


h2 {
	margin-bottom: 20px;
	font-size: 39px;
	font-weight: bold;
	color: #ABABAB;
}

.btn {
    background-color: #48D1CC;
	border-radius: 2px;
	font-weight: bold;
	padding: 10px;
	color: white;
}

.med {
	font-size: 27px;
	color: white;
}

.wide {
	background-color: #8EB7E4;
	width: 100%;
	-webkit-border-top-right-radius: 0;
	-webkit-border-bottom-right-radius: 0;
	-moz-border-radius-topright: 0;
	-moz-border-radius-bottomright: 0;
	border-top-right-radius: 0;
	border-bottom-right-radius: 0;
}
</style>
<script>
  function regist(){
	window.open("memberRegistForm.mem");
  }
  
</script>
</head>
<body style="background-image: url('img/login-bg.jpg'); ">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
		<section>
			<form action="loginPro.mem" method="post">

				<div class="container">
					<div class="row colored">
						<div class="contcustom">
						<span><img src="img/logo1.jpg" style="height: 70px; width: 70px;" /></span>
							<h2>Log-In</h2>
							<div>
								<input type="text" name="id" id="id" placeholder="Enter your ID">
								<input type="password" name="passwd" id="passwd"
									placeholder="Enter your Password"> <input type="submit"
									class="btn" value="LOG-IN">
									<input type="button"
									class="btn" value="회원가입" onclick = "regist()">
									
							</div>
						</div>
					</div>
			</form>
	
	</section>
</body>
</html>