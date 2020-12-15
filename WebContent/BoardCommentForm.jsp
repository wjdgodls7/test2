<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#wrap {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}

#wrap3 {
	width: 800px;
	margin: 0 auto 0 auto;
}

#wrap2 {
	width: 800px;
	margin: 0 auto 0 auto;
}
</style>

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
	<section id="hero">

		<div class="hero-container">
		<h1 style="font-size: 35px; font-weight: bold; text-align: center; margin-left: auto; margin-right: auto;">댓글 수정</h1>
			<div class="row no-gutters" id="wrap">
				<div id="wrap3">
					<hr size="1" width="550">
					<br>
					<div id="beforeForm">
						<b><font size="5" color="white">이전 댓글</font></b>
						<br/><br/>
						<b><font size="5" color="white">댓글 번호 :&nbsp;  ${commentNum}&nbsp; &nbsp; 닉 네임: &nbsp; ${nick}</font></b>
						<br/><br/>
						<textarea rows="7" cols="70" name="beforecontent">${commentText}</textarea>
						<br> <br>
					</div>
				</div>
				<div id="wrap2">
					<hr size="1" width="550">
					<br>
					<div id="afterForm">
						<b><font size="5" color="white">수정할 댓글</font></b>
						<br/><br/>
						<form action="updateComment" method="POST" name="updateModiForm">

							<input type="hidden" value="${commentNum}" name="commentNum">
							<input type="hidden" value="${time}" name="time"> <input
								type="hidden" value="${id}" name="id"> <input
								type="hidden" value="${nick}" name="nick"> <input
								type="hidden" value="${BoardNum}" name="boardNum">
							<textarea rows="7" cols="70" name="aftercomment"></textarea>
							<br> <br>
							<input type="button" onclick="update()" value="댓글 등록">
							<input type="button" onclick="location.href='writeDetail?num=${BoardNum}'" value="수정 취소">
						</form>
					</div>
				</div>


			</div>
		</div>
	</section>
	<!-- End Hero -->
</body>
<script>
	function update() {
		updateModiForm.submit();
	}
</script>
</html>

