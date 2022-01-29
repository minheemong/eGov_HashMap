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
window.opener.formm.imgfilename.value='${image}';
window.opener.document.getElementById('imageName').innerHTML='${originalFilename}';
self.close();
</script>
</body>
</html>