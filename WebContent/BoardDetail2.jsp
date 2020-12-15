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
  * Template URL: https://bootstrapmade.com/free-bootstrap-coming-soon-template-countdwon/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<style type="text/css">
select {
	-webkit-appearance: none; /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
	background: url(이미지 경로) no-repeat 95% 50%; /* 화살표 모양의 이미지 */
} /* IE 10, 11의 네이티브 화살표 숨기기 */
select::-ms-expand {
	display: none;
}

select {
	width: 170px; /* 원하는 너비설정 */
	padding: .7em .4em; /* 여백으로 높이 설정 */
	font-family: inherit; /* 폰트 상속 */
	background:
		url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg)
		no-repeat 95% 50%; /* 네이티브 화살표 대체 */
	background-color: white;
	border: 1px solid #999;
	border-radius: 0px; /* iOS 둥근모서리 제거 */
	-webkit-appearance: none; /* 네이티브 외형 감추기 */
	-moz-appearance: none;
	appearance: none;
}

#wrap {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
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
		<div class="hero-container" id="wrap">
			<h1>${detail.title}</h1>
			<table>
				<tr>
					<th>카테고리</th>
					<td>${detail.subject }</td>
				</tr>
				<tr>
					<th>번호</th>
					<td>${detail.num }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${detail.title }</td>
				</tr>
				<tr>
					<th>파일</th>
					<td><img src="FileUpload/${detail.file}" width="200px"><br>${detail.file}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${detail.nick}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${detail.date}</td>
				</tr>
				<tr>
					<th>조회 수</th>
					<td>${detail.hit}</td>
				</tr>

			</table>
			<br /> <br />
			<form action="forms/notify.php" method="post" role="form"
				class="php-email-form">
				<div class="text-center" style="text-align: center;">
					<input type="button" onclick="location.href='writeList'"
						value="목록가기" style="width: 140px; height: 42px;">

					<!-- <input type="button" onclick="location.href='writeModify?num=${detail.num}'" value="글 수정" style="width: 140px; height: 42px;"> -->
					<input type="button" onclick="zmodify()" value="글 수정"
						style="width: 140px; height: 42px;"> <input type="button"
						onclick="zdelete()" value="글 삭제"
						style="width: 140px; height: 42px;">
				</div>
			</form>


			<!-- 댓글 리스트 시작 -->
			<div style = overflow:auto>
				<form action="comment?nick=${detail.nick}&id=${id}" method="POST">
					<table style="text-align: center; border: 1px solid #dddddd">
						<c:forEach items="${commentlist}" var="commentlist">
						<tr>
							<td style="border-bottom: none;" valign="middle"><br> <br></td>
							<td>${commentlist.getComments()}</td>
						</tr>
						</c:forEach>
					</table>
				</form>
			</div>

			<!-- 댓글 리스트 끝 -->
			<!-- 댓글 시작 -->
			<div>
				<div>
					<form action="comment?nick=${detail.nick}&id=${id}" method="POST">
						<table style="text-align: center; border: 1px solid #dddddd">
							<tr>
								<td style="border-bottom: none;" valign="middle"><br>
									<br>${detail.nick}</td>
								<td><input type="text" style="height: 100px;"
									placeholder="댓글창" name="commentText"></td>
								<td><br> <br> <input type="submit" value="댓글 작성"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 댓글 끝-->

		</div>
	</section>
	<!-- End Hero -->
</body>
<script src="https://ajax.googleapis.com/ajax/libs/d3js/6.2.0/d3.min.js"></script>
<script>
	function zdelete() {
		var loginnick = '${nick}';
		var nick = '${detail.nick}';
		var level = '${Level}';

		console.log(loginnick);
		console.log(nick);

		if (loginnick == nick || level === 'M') {
			location.href = "writeDelete?num=${detail.getNum()}&level=${Level}";
		} else {
			alert('권한이 없습니다.');
		}
	}

	function zmodify() {
		var loginnick = '${nick}';
		var nick = '${detail.nick}';
		var level = '${Level}';

		console.log(loginnick);
		console.log(nick);

		if (loginnick == nick || level === 'M') {
			location.href = "writeModify?num=${detail.getNum()}&level=${Level}";
		} else {
			alert('권한이 없습니다.');
		}
	}
</script>
</html>