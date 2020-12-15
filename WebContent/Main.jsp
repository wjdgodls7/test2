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

<!-- =======================================================
  * Template Name: WeBuild - v2.1.0
  * Template URL: https://bootstrapmade.com/free-bootstrap-coming-soon-template-countdwon/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header" style="padding: auto;">
		<div class="container-fluid">

			<div class="logo float-left">
				<h1 class="text-light">
					<a href="Main.jsp"><span
						style="font-size: 35px; font-weight: bold;">ICIA</span></a>
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
	<c:if test="${Level eq 'S' }">
	<section id="hero">
		<div class="hero-container">
			<h1>Welcome to ICIA D Classes</h1>
			<br />
<%-- 			<h1>${nick }</h1>
			<h1>${sessionScope.nick}</h1> --%>
			<h2 style="font-size: 35px;">Select the Menu</h2>
			
			<form action="myWriteList" method="post" role="form"
				class="php-email-form">
				
				<input type="hidden" name="nick" value="${nick}">



				<button type="submit" style="width: 170px; height: 42px;">내가 쓴 글</button>	
				<input type="button" onclick="location.href='writeList'" value="ICIA 게시판" style="width: 170px; height: 42px;">
				<input type="button" onclick="location.href='memberView?id=${id}'" value="마이페이지" style="width: 170px; height: 42px;"><br/><br/>
				
				<input type="button" onclick="location.href='memberLogout?id=${id}'" value="로그아웃" style="width: 170px; height: 42px;">
				<input type="button" onclick="location.href='MemberModifyForm.jsp'" value="회원정보 수정" style="width: 170px; height: 42px;">
				<input type="button" onclick="confirm_test()" value="회원 탈퇴" style="width: 170px; height: 42px;">
				<%-- <input type="button" onclick="location.href='writeList?nick=${nick}'" value="ICIA 게시판" style="width: 170px; height: 42px;">	 --%>		
			

			</form>
		</div>
	</section>
	<!-- End Hero -->
	</c:if>
		<!-- ======= Hero Section ======= -->
	<c:if test="${Level eq 'M' }">
	<section id="hero">
		<div class="hero-container">
			<h1>Welcome to ICIA D Classes</h1>
			<br />
			<h2 style="font-size: 35px;">Select the Menu</h2>

			<form action="myWriteList" method="post" role="form"
				class="php-email-form">
				
				<input type="hidden" name="nick" value="${nick}">
				
				<input type="button" onclick="location.href='memberLogout?id=${id}'" value="로그아웃" style="width: 190px; height: 42px;">
				<input type="button" onclick="location.href='memberList'" value="ICIA 회원관리" style="width: 190px; height: 42px;">
				<input type="button" onclick="location.href='writeList'" value="ICIA 게시판 관리" style="width: 190px; height: 42px;"><br/><br/>
				<button type="submit" style="width: 190px; height: 42px;">내가 쓴 글</button>				


			</form>
		</div>
	</section>
	<!-- End Hero -->
	</c:if>
	

</body>
<script>
function confirm_test() {
	var result = confirm("정말로 탈퇴하시겠습니까?");
    if ( result ) {
    	location.href = "memberDelete?id=${id}&nick=${nick}";
    } else  {
    	location.href = "Main.jsp";
    }
}
</script>
</html>