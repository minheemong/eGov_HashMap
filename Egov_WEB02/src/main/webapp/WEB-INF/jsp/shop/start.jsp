<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
if( (navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i))
	|| (navigator.userAgent.match(/iPad/i)) || (navigator.userAgent.match(/Windows CE/i))
	|| (navigator.userAgent.match(/Symbian/i)) || (navigator.userAgent.match(/BlackBerry/i))
	|| (navigator.userAgent.match(/Android/i)) ) {
		window.location.href='mobilemain.do';
} else {
	window.location.href='main.do';
}
</script>
</body>
</html>