<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../include/headerfooter/header.jsp" %>
<%@ include file="../include/sub01/sub_image.html" %> 
<%@ include file="../include/sub01/sub_menu.html" %>
<article>
    <h2> Item</h2>   
   	<c:forEach items="${productKindList}"  var="productVO">
   		 <div id="item">
			<a href="productDetail.do?pseq=${productVO.PSEQ}">
				<img src="<c:url value='/product_images/${productVO.IMAGE}' />" />
				<h3>${productVO.NAME} </h3><p>${productVO.PRICE2} </p>
			</a>
   		</div>
   	</c:forEach>
   	<div class="clear"></div>
</article>
<%@ include file="../include/headerfooter/footer.jsp" %>