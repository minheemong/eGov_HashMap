<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/admin/admin.css' /> ">
<script src="<c:url value='/admin/product.js' />" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
   
   $("#myButton").click(function(){
       var formselect=$("#fileupForm")[0]; //바디에잇는 대상 폼을 변수에저장
       var formdata= new FormData(formselect);   //폼안의 데이터를 객체형으로 변환 
       //ajax :웹페이지의 새로고침이 필요없는요청
       $.ajax({
          url:"<%=request.getContextPath() %>/fileup.do",
          type:"POST",
          enctype:"Multipart/form-data",
          async:false,
          data:formdata,
          contentType:false,
          processData:false,
          
          success:function(data){
             if(data.STATUS==1){
                
        	    $("#filename").empty();
          		//$("#filename").append("<div>"+data.IMG+"</div>");
        	    $("#filename").append(
              		 "<img src=\"<c:url value='/product_images/" + data.IMG +"' />\" width='100' height='100'/>"   
            	);
            	//$("#image").val(data.IMG); 
            	//document.getElementById("image").value=data.IMG;
            	document.frm.image.value=data.IMG;
             }    
          },
          error:function(){
             alert("실패");
          }
       });
   });
});
</script>

</head>
<body>
<div id="wrap">
<header>			
	<div id="logo">
		<img style="width:800px" src="<c:url value='/admin/bar_01.gif' />" >
		<img src="<c:url value='/admin/text.gif' /> ">
	</div>	
	<input class="btn" type="button" value="logout" style="float: right;"
		onClick="location.href='adminLogout.do'">			
</header>
<div class="clear"></div>
