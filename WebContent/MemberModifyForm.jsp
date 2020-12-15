<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
		$('#nickcheck').click(function click() {
			console.log("실행");
			var usernick = $('#usernick').val();
			
			if(usernick==0){
	            alert('닉네임 중복체크를 해주세요');
	            return false;
			}
			
			
			$.ajax({
				async:true,
				data:{nick : usernick},
				url:"membernickCheck",
				type:'POST',
				dataType:"json",

				success : function(data) {
					if (data.cnt > 0) {
						alert("존재하는 닉네임입니다.");
						usernick_overlap_input.focus();
					} else {
						alert("사용가능한 닉네임입니다.");
					}
				},
				error : function(data) {
					alert("데이터를 x")
				}

			});
		});
	});
</script>
<style>
#wrap {
	width: 1000px;
	
	margin-left: auto;
	margin-right: auto;
	text-align: center;
	
}


td {
	text-align: left;
	width:270px;
}
table {
	margin-top:80px;
}
</style>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>회원 정보 수정</title>
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
<link href="assets/css/design.css" rel="stylesheet">

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
					style="font-size: 35px; font-weight: bold;">LOGOUT</a>
			</div>

		</div>
	</header>
	<!-- End #header -->

	<!-- ======= Hero Section ======= -->
	<section id="hero" style="height:125vh;">
		<div class="hero-container">
			<div class="row no-gutters" id="wrap">
			
					
			<form action="memberModify?id=${id}" method="POST">
				<table>
					<tr>
						<th>아이디</th>
						<td>${id}</td>
					</tr>

					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="password" id="userPw"
							onchange="check1()" maxlength="15" required></td>
					</tr>

					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="checkPw" id="checkPw"
							onchange="check1()" maxlength="15" required></td>
						<td><h4 id="msg"></h4></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" maxlength="6" autocomplete="off" required>
									</td>
					</tr>

					<tr>
						<th>닉네임</th>
						<td><input type="text" name="nick" maxlength="20" id="usernick"
							placeholder="닉네임을 입력해주세요." autocomplete="off" required></td>
							<td><input type="button" value="중복확인" id="nickcheck" required="required"></td>
					</tr>

					<tr>
						<th id="title">성별</th>
						<td><input type="radio" name="gender" value="남자" checked required>남자
							<input type="radio" name="gender" value="여자" checked required>여자</td>
					</tr>
					<tr>
						<th>휴대전화</th>
						<td><input type="text" name="phone" maxlength="11" autocomplete="off" placeholder="-제외하고 작성해주세요" required></td>
					</tr>
					<tr>
						<th>조</th>
						<td><select name="team" required>
								<option value="">조</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
						</select></td>
					</tr>
					<tr>
						<th id="title">이메일</th>
						<td><input type="email" name="email" maxlength="30" autocomplete="off"></td>
					</tr>

					<tr>
						<th>주소</th>
						<td><input type="text" name="add" size="30" id="sample6_postcode" placeholder="우편번호" autocomplete="off"> 
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" autocomplete="off"> 
							<input type="text" name="add2" size="30" id="sample6_address" placeholder="주소" autocomplete="off">
							<input type="text" name="add3"size="30" id="sample6_detailAddress" placeholder="상세주소" autocomplete="off">
							<input type="hidden" id="sample6_extraAddress" placeholder="참고항목" autocomplete="off"> 
							<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script></td>
					</tr>

					<tr>
						<td colspan="2" style="text-align: right;">
						<button type="submit">수정하기</button></td>
					</tr>

				</table>
				</form>

			</div>

		</div>
	</section>

</body>

<script>
 	var pw1;
	var pw2;
	 
	function check1() {
/* 		var pw = document.getElementById('userPw').value;
        var SC = ["!","@","#","$","%"];
        var check_SC = 0; */
 		pw1 = document.getElementById("userPw").value;
		pw2 = document.getElementById("checkPw").value;
        
        if(pw1.length < 6 || pw1.length > 16) {
            window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
            document.getElementById('pw1').value='';
        }

		var msg = document.getElementById("msg");
			if(pw1==pw2) {
				msg.innerHTML = "가능한 비밀번호다";
			}else {
				msg.innerHTML = "다시 입력해라"
			} 
	}
/*         for(var i=0;i<SC.length;i++){
            if(pw.indexOf(SC[i]) != -1){
                check_SC = 1;
            }
        }
        if(check_SC == 0){
            window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
            document.getElementById('pw').value='';
        }
        if(document.getElementById('pw').value !='' && document.getElementById('checkPw').value!=''){
            if(document.getElementById('pw').value==document.getElementById('checkPw').value){
                document.getElementById('msg').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('msg').style.color='blue';
            }
            else{
                document.getElementById('msg').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('msg').style.color='red';
            }
        } */ 
</script>
</html>