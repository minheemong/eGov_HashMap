<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link  rel="stylesheet" href="<c:url value='/css/shopping.css' />" />  
<script type="text/javascript" src="<c:url value='/script/member.js' />" ></script>
<script type="text/javascript" src="<c:url value='/script/mypage.js' />" ></script>
</head>
<body>
<div id="wrap">
	<header>
		<div id="logo"><!-- 최상단 "/" 리퀘스트 요청 링크 -->
			<a href="main.do">
				<img src="<c:url value='/images/logo.png' />"  width="180" height="100" />
			</a>
		</div>
		<nav id="top_menu"> <!-- top menu -->
			<ul>
				<c:choose>
					<c:when test="${empty loginUser}">
						<li><a href="loginForm.do">LOGIN</a></li>
						<li><a href="contract.do">JOIN</a></li>
					</c:when>
		    		<c:otherwise>
		       			<li style="color:blue;font-weight:bold;font-size:110%;">
		       				${loginUser.NAME}(${loginUser.ID})</li>
		       			<li><a href="memberEditForm.do">정보수정</a></li>
		       			<li><a href="logout.do">LOGOUT</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="cartList.do">CART</a></li><li><a href="myPage.do">MY PAGE</a></li>
		    	<li ><a href="qnaList.do" style="border:0px;">Q&amp;A(1:1)</a></li>
			</ul>
		</nav>
		<nav id="catagory_menu"> <!-- catagory menu -->
			<ul>
				<li><a href="catagory.do?kind=1">Heels</a></li>
				<li><a href="catagory.do?kind=2">Boots</a></li>
				<li><a href="catagory.do?kind=3">Sandals</a></li>
				<li><a href="catagory.do?kind=4">Sneakers</a></li>
				<li><a href="catagory.do?kind=5">Sleeper</a></li>
				<li><a href="catagory.do?kind=6">On Sale</a></li>
			</ul>
		</nav>
		<div class="clear"></div><hr>
	</header>