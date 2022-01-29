<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function selectedimage(){
	document.frm.submit();
}
</script>
</head>
<body>
<div id="wrap" align="center">
<form name="frm" action="fileupload" method="post" enctype="multipart/form-data">
	<h1>파일선택</h1>
	<input type="file" name="image" onchange="selectedimage();">
	<!-- <input type="submit" value="파일 적용"> -->
</form>
</div>
</body>
</html>