<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<link  rel="stylesheet" href="<c:url value='/css/mobile.css'/>">
<script type="text/javascript" src="<c:url value='/script/mmember.js'/>"></script>
<script type="text/javascript" src="<c:url value='/script/mmypage.js' /> "></script>
	  
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
<header>
	<c:choose>
		<c:when test="${empty loginUser}">
			<nav id="top_menu" > <!-- top menu -->
				<ul>
					<li><a href="mloginForm.do">Login</a></li>
					<li><a href="mcontract.do">Join</a></li>
					<li><a href="mcartList.do">Cart</a></li>
					<li><a href="mmyPage.do">My Page</a></li>
					<li ><a href="mqnaList.do">Q&amp;A</a></li>
				</ul>
			</nav>
		</c:when>
		<c:otherwise>
			<nav id="top_menu" > <!-- top menu -->
				<ul>
					<li><span style="color:yellow;">
						${loginUser.id}</span>
						<a href="mmemberEditForm.do"> · 정보수정</a>
						<a href="mlogout.do"> · Logout</a></li>
					<li><a href="mcartList.do">Cart</a></li>
					<li><a href="mmyPage.do">My Page</a></li>
			    	<li ><a href="mqnaList.do">Q&amp;A</a></li>
			    </ul>
			</nav>
		</c:otherwise>
	</c:choose>
	
	<div id="logo"><a href="/mobilemain.do">Shoes Shop</a></div>
	
</header>






