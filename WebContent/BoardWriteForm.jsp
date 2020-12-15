<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style>
        #wrap{
            width:1000px;
            margin-left:auto; 
            margin-right:auto;
            text-align:center;
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
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
			<div class="row no-gutters" id="wrap">
			<h1 style="font-size: 35px; font-weight: bold; text-align:center; margin-left:auto; margin-right:auto;" >글쓰기</h1>
			<form action="write" method="POST" enctype="multipart/form-data">
				<table style="width:80%;">
					<input type ="hidden" name="level" value="${Level }">	
					<tr>
						<td align="center">제목</td>
						<td colspan="2"><input type="text" name="title" size="48"></td>
   
     				</tr>	
					<tr>
						<td align="center">주제선택</td>
						<td><select name="subject">
								<c:if test = "${Level eq 'M' }">
								<option value="Notice">공지사항</option>
								</c:if>
								<option value="PartBoard">조별게시판</option>
								<option value="FreeBoard">자유게시판</option>
								<option value="QNABoard">건의게시판</option>
						</select></td>
 						<td align="center">닉네임 &nbsp; : &nbsp; ${nick}</td>
						<input type="hidden" name="nick" value="${nick}">
						<input type="hidden" name="id" value="${id}">
     				</tr>
   
					<tr>
						<td align="center">내용</td>
						<td colspan="2"><textarea rows="10" cols="50" name="content" required></textarea></td>
      	
     				</tr>

					<tr>
						<td align="center">첨부파일</td>
						<td colspan="2"><input type="file" name="file"></td>
      			
     				</tr>
     
				</table>
						<br/><br/>
						<button type="submit">글작성</button>
						 
						<input type="button" onclick="boardList()" value="목록보기">
			</form>
			</div>
		</div>
	</section>
	<!-- End Hero -->

</body>
<script>

	function boardList() {
		location.href = "writeList";
	}
</script>
</html>