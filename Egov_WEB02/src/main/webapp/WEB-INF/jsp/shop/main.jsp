<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/headerfooter/header.jsp" %> 

 <div id="main_img"><img src="<c:url value='/images/main_img.jpg'/>"  
    			style="border-radius:20px 20px 20px 20px;border:2px solid white;"></div>
    			
<div id="front"><h2> New Item</h2>
  	<div id="bestProduct">         
      <c:forEach items="${newList }"  var="productVO">
        <div id="item"><a href="productDetail.do?pseq=${productVO.PSEQ}">
			<img src="<c:url value='/product_images/${productVO.IMAGE}' />"  />
            <h3> ${productVO.NAME} - <fmt:formatNumber value="${productVO.PRICE2}" type="currency" /></h3></a></div>
      </c:forEach></div>
</div><div class="clear"></div>

<div id="front"><h2> Best Item</h2><div id="bestProduct">         
        <c:forEach items="${bestList}"  var="productVO">
          <div id="item"><a href="productDetail.do?pseq=${productVO.PSEQ}">
			<img src="<c:url value='/product_images/${productVO.IMAGE}' />" />
           <h3> ${productVO.NAME} - <fmt:formatNumber value="${productVO.PRICE2}" type="currency" /></h3></a></div>
    	</c:forEach></div>	<div class="clear"></div>
</div><div class="clear"></div>

<%@ include file="include/headerfooter/footer.jsp" %> 