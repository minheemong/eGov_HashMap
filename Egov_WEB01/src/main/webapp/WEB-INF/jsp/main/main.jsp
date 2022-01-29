<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>안녕하세요<br>반갑습니다<br>
주문하신 ${food} 나왔습니다<br>
또오세요</h2>
<br>
<form method="get" action="main2.do">
	보낼 메뉴 : <input type="text" name="menu">
	<input type="submit" value="전송">
</form>
</body>
</html>