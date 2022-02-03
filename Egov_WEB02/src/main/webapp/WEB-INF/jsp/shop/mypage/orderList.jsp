<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/headerfooter/header.jsp" %>
<%@ include file="../include/sub03/sub_image.html" %> 
<%@ include file="../include/sub03/sub_menu.jsp" %>
<article>
<h2> Order List </h2>
<form>
<table id="cartList">
	<tr><th>상품명</th><th>수 량</th><th>가 격</th><th>주문일</th><th>진행상태</th></tr>
	<c:forEach items="${orderList}" var="orderVO">
		<tr><td><a href="productDetail.do?pseq=${cartVO.PSEQ}">
			<h3>${orderVO.PNAME}</h3></td>	<td> ${orderVO.QUANTITY} </td>
       		<td><fmt:formatNumber value="${orderVO.PRICE2*orderVO.QUANTITY}" type="currency"/></td>      
        	<td><fmt:formatDate value="${orderVO.INDATE}" type="date"/></td><td> 처리 진행 중 </td></tr>
	</c:forEach>
	<tr><th colspan="2"> 총 액 </th><th colspan="2"> 
         <fmt:formatNumber value="${totalPrice}" type="currency"/></th><th>주문 처리가 완료되었습니다. </th></tr> 
</table><div class="clear"></div>
<div id="buttons" style="float: right">
<input type="button" value="쇼핑 계속하기"  class="cancel"  onclick="location.href='main.do'"></div>
</form>
</article>
<%@ include file="../include/headerfooter/footer.jsp" %>