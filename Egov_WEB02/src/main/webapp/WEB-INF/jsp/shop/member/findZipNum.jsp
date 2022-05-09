<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{background-color:gray; font-family: Verdana;}
#popup h1 {font-family: "Times New Roman", Times, serif; font-size: 45px; color: #CCC; font-weight: normal;}
table#zipcode{border-collapse:collapse;border-top:3px solid #fff; border-bottom:3px solid #fff; width:100%;}
table#zipcode th, table#zipcode td{text-align:center;color:#FFF;	border-bottom: 1px dotted  #fff;}
table#zipcode  a{display:block;height:20px;text-decoration:none; color:#fff; padding:10px;}
table#zipcode a:hover{color: #F90;font-weight: bold;}
</style>
<script type="text/javascript">
	function result(zip_num, sido, gugun, dong) {
	   opener.document.formm.zip_num.value=zip_num;
	   opener.document.formm.addr1.value = sido+ "  " + gugun + " " + dong;
	   self.close();
	};
</script>
</head>
<body>
<div id="popup">
	<h1>우편번호검색</h1>
	<form method="get" name="formm" action="findZipNum.do">
		동 이름 : <input name="dong"  type="text"><input type="submit" value="찾기"  class="submit">
	</form><br><br>
	<table id="zipcode">
		<tr><th>우편번호</th><th>주소</th></tr>
		<c:forEach items="${addressList}" var="addressVO">
			<tr><td>${addressVO.ZIP_NUM}</td><td><a href="#" onclick="result('${addressVO.ZIP_NUM}',
			'${addressVO.SIDO}', '${addressVO.GUGUN}','${addressVO.DONG}')">
			${addressVO.SIDO} ${addressVO.GUGUN} ${addressVO.DONG} </a></td></tr>
		</c:forEach>
	</table>
</div>
</body>
</html>