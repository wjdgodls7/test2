<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>ICIA D CALSS BOARD</title>
<meta content="" name="descriptison">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/table.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: WeBuild - v2.1.0
  * Template URL: https://bootstrapmade.com/free-bootstrap-coming-soon-template-counthwon/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<style type="text/css">
select {
	-webkit-appearance: none; /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
/* 	background: url(이미지 경로) no-repeat 95% 50%; */ /* 화살표 모양의 이미지 */
} /* IE 10, 11의 네이티브 화살표 숨기기 */
select::-ms-expand {
	display: none;
}

select {
	width: 150px; /* 원하는 너비설정 */
	padding: .3em .3em; /* 여백으로 높이 설정 */
	font-family: inherit; /* 폰트 상속 */

	background: -webkit-linear-gradient(left, #25c481, #25b7c4);
  	background: linear-gradient(to right, #25c481, #25b7c4);
/*  	background:		
		url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg)
		no-repeat 90% 40%;  */ /* 네이티브 화살표 대체 */
	background-color: -webkit-linear-gradient(left, #25c481, #25b7c4);
 	background-color: linear-gradient(to right, #25c481, #25b7c4);
	border: 1px solid #999;
	border-radius: 0px; /* iOS 둥근모서리 제거 */
	-webkit-appearance: none; /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
	padding: 7px 15px;
  	text-align: center;
  	font-weight: 1000;
  	font-size: 24px;
  	color: #000000;
  	text-transform: uppercase;

}

th{
	color: #FFFFFF;
	font-weight: 500;
}
td {
	color :#000000;
	font-weight: 400;
	
}
#hero {
	height : 110vh;
}
#hero .hero-container {
	margin-top : 110px;
}
</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header" style="padding: auto;">
		<div class="container-fluid">

			<div class="logo float-left">
				<h1 class="text-light">
					<a href="javascript:history.back()"><span
						style="font-size: 35px; font-weight: bold;">BACK</span></a>
				</h1>
				<!-- Uncomment below if you prefer to use an image logo -->
				<!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
			</div>

			<div class="contact-link float-right">
				<a href="memberLogout?id=${id}" class="scrollto"
					style="font-size: 35px; font-weight: bold;">Logout</a>
			</div>

		</div>
	</header>
	<!-- End #header -->

	<!-- ======= Hero Section ======= -->
	<section id="hero">
		<div class="hero-container" style="padding: 0 50px; font-color: snow;">
			<h1>USER INFO</h1>
			<br/><br/>
			<section >
			<form action="MemberModifyForm.jsp?id=${id}" method="POST">
					<table >
						<tr>
							<th></th>
							<th>아이디</th>
							<td>${memberView.userId }</td>
							<th></th>
						</tr>
						<tr>
							<th></th>
							<th>닉네임</th>
							<td>${memberView. userNick}</td>
							<th></th>
						</tr>							
						<tr>
							<th></th>
							<th>이름</th>
							<td>${memberView. userName }</td>
							<th></th>
						</tr>
						<tr>
							<th></th>
							<th>조</th>
							<td>${memberView. userTeam}</td>
							<th></th>
						</tr>
						<tr>
							<th></th>
							<th>생년월일</th>
							<td>${memberView. userBirth}</td>
							<th></th>
						</tr>
						<tr>
							<th></th>
							<th>성별</th>
							<td>${memberView. userGender}</td>
							<th></th>
						</tr>
						<tr>
							<th></th>	
							<th>휴대전화</th>	
							<td>${memberView. userPhone}</td>	
							<th></th>	
						</tr>
						<tr>
							<th></th>
							<th>주소</th>
							<td>${memberView. userAdd}</td>
							<th></th>
						</tr>
						<tr>
							<th></th>
							<th>이메일</th>
							<td>${memberView. userEmail}</td>
							<th></th>
						</tr>
						<tr>
						<th colspan="4" style="text-align: center;">
						<button type="submit">정보수정</button></th>
					</tr>
				</table>
				</form>
				</section>
			</div>
	</section>	
</body>

	<!-- End Hero -->
<script src="https://ajax.googleapis.com/ajax/libs/d3js/6.2.0/d3.min.js"></script>
<script type="text/javascript">


</script>
</html>