<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="include/headerfooter/header.jsp" %> 


<article>
<div id="sub_img"><img src="<c:url value='/images/product/sub_img.jpg' />"/></div>
<nav id="top_menu"> <!-- catagory menu -->
   <ul>
      <li><a href="catagory.do?kind=1">Heels</a></li>
      <li><a href="catagory.do?kind=2">Boots</a></li>
      <li><a href="catagory.do?kind=3">Sandals</a></li>
      <li><a href="catagory.do?kind=4">Sneakers</a></li>
      <li><a href="catagory.do?kind=5">Sleeper</a></li>
      <li><a href="catagory.do?kind=6">On Sale</a></li>
   </ul>
</nav><br/><br/><fmt:setLocale value="ko_KR"/>
<div id="front">
   <div style="width:100%, margin:0 auto;">
   <h2> New Item</h2><br/>
      <c:forEach items="${newList }"  var="productVO">
        <div class="item"><a href="productDetail.do?pseq=${productVO.PSEQ}">
         <img src="<c:url value='/product_images/${productVO.IMAGE}' />"  />
            <br>${productVO.NAME}<br>${productVO.PRICE2}</a>
        </div>
      </c:forEach>
    </div>
</div><div class="clear"></div><br/><br/>
<div id="front">
   <div stype="width:100%, margin:0 auto;">
   <h2> Best Item</h2><br/>       
        <c:forEach items="${bestList}"  var="productVO">
          <div class="item"><a href="productDetail.do?pseq=${productVO.PSEQ}">
         <img src="<c:url value='/product_images/${productVO.IMAGE}' />" />
           <br>${productVO.NAME}<br>${productVO.PRICE2}</a>
        </div>
         </c:forEach>
      </div>   
</div>


</article>

<%@ include file="include/headerfooter/header.jsp" %> 