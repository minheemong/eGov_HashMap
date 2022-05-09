<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/admin/header.jsp"%>    
<%@ include file="../../include/sub05/sub_menu.jsp"%>
<article>
<h1>상품수정</h1>  
<form name="frm" method="post" action="productUpdate.do">
<input type="hidden" name="pseq" value="${dto.PSEQ}">
<table id="list">
  <tr><th>상품분류</th><td colspan="5">
    <select name="kind">
      <c:forEach items="${kindList}" var="kind" varStatus="status">
        <c:choose>
          <c:when test="${dto.KIND==status.count}">
            	<option value="${status.count}" selected="selected">${kind}</option>
          </c:when>
          <c:otherwise>
            	<option value="${status.count}">${kind}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select></td></tr>
  <tr> <th>상품명</th><td width="343" colspan="5">
      <input type="text" name="name" size="47" value="${dto.NAME}"></td></tr>
  <tr><th>원가[A]</th><td width="70">        
      <input type="text" name="price" size="11"  value="${dto.PRICE}"></td>
      <th>판매가[B]</th><td width="70">
      <input type="text" name="price2" size="11" value="${dto.PRICE2}"></td>
      <th>[B-A]</th><td width="72">
      <input type="text" name="price3" size="11"  value="${dto.PRICE2-dto.PRICE}">
    </td> </tr>
  <tr><th>베스트상품</th><td>
      <c:choose>
        <c:when test='${dto.BESTYN=="y"}'>
          <input type="checkbox" name="bestyn" value="y" checked="checked">
        </c:when>
        <c:otherwise>
          <input type="checkbox" name="bestyn" value="n">
        </c:otherwise>
      </c:choose></td>        
    <th>사용유무</th> <td>
      <c:choose>
        <c:when test='${dto.USEYN=="y"}'>
          <input type="checkbox" name="useyn" value="y" checked="checked">
        </c:when>
      <c:otherwise>
        <input type="checkbox" name="useyn" value="n">
      </c:otherwise>
    </c:choose> </td></tr>
  <tr>
    <th>상세설명</th><td colspan="5">
      <textarea name="content" rows="8" cols="70" >${dto.CONTENT}</textarea> </td></tr>
 	<tr><th rowspan="2">상품이미지</th> <td colspan="5">
 		<div id="filename"><c:choose>
  			<c:when test="${not empty dto.IMAGE}">
      			<img src="<c:url value='/product_images/${dto.IMAGE}' />" width="200"> <br>
    		</c:when> 
      		<c:otherwise>이미지가 없습니다</c:otherwise>
      	</c:choose></div></td></tr>    
	<tr><td height="30">
		<div id="filename"></div><input type="hidden" id="image" name="image">
		<input type="hidden" name="oldimage"  value="${dto.IMAGE}">
	</td></tr>
</table>
<input class="btn" type="button" value="수정"  onClick="go_mod_save('${dto.PSEQ}')">           
<input class="btn" type="button" value="취소" onClick="go_mov()">
</form>

	<div style="position:relative; top:-80px; width:500px; margin:0 auto;">
		<form name="formm" id="fileupForm" method="post" enctype="multipart/form-data">
    		<input type="file" name="image">이미지를 변경할 때만 사용하세요
    		<input type="button" id="myButton" value="변경">
		</form>
	</div>
</article>
<%@ include file="../../include/admin/footer.jsp"%>