<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>
<%@ include file="../../include/admin/sub_menu.jsp"%>
<article>
<h1>상품리스트</h1>
<form name="frm" method="post">
<table>
	<tr><td width="642">
		상품명<input type="text" name="key" value="${key}">
		<input class="btn" type="button" name="btn_search" value="검색" onClick="go_search();">
		<input class="btn" type="button" name="btn_total" value="전체보기 " onClick="go_total();">
		<input class="btn" type="button" name="btn_write" value="상품등록"  
			onClick="go_wrt();"></td></tr>
</table>
<table id="productList">
	<tr><th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>사용유무</th></tr>
	<c:choose>
    	<c:when test="${productListSize<=0}">
    		<tr><td width="100%" colspan="7" align="center" height="23">등록된 상품이 없습니다.</td></tr>
    	</c:when>
    	<c:otherwise>
    		<c:forEach items="${productList}" var="productVO">
		   		<tr><td height="23" align="center" >${productVO.PSEQ}</td>
					<td style="text-align:left; padding-left:50px; padding-right:0px;">
					<a href="#" onClick="go_detail('${productVO.PSEQ}')">${productVO.NAME}</a></td>
					<td><fmt:formatNumber value="${productVO.PRICE}"/></td>
					<td><fmt:formatNumber value="${productVO.PRICE2}"/></td>
		      		<td><fmt:formatDate value="${productVO.INDATE}"/></td>
		      		<td><c:choose>
		      			<c:when test='${productVO.USEYN=="n"}'>미사용</c:when>
		   	 			<c:otherwise>사용</c:otherwise> 
					</c:choose></td>
				</tr>
		    </c:forEach> 
    	</c:otherwise>
    </c:choose>
</table>
</form><br>

<jsp:include page="../../paging/paging.jsp">
	<jsp:param name="command" value="productList.do" />
</jsp:include>

</article>
<%@ include file="../../include/admin/footer.jsp"%>


