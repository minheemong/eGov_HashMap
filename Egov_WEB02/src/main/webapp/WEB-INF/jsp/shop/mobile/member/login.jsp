<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/headerfooter/header.jsp" %>
<%@ include file="../include/sub02/sub_image.html" %> 
<%@ include file="../include/sub02/sub_menu.html" %>
    
<article>
<h1>Login</h1>
	<form method="post" action="mlogin.do">
		<table align="center" width="300">
			<tr height="50">
				<th width="80">ID</th>
				<td width="200"><input name="id" type="text"></td></tr>
			<tr height="50">
				<th>PASSWORD</th>
				<td><input name="pwd" type="password"></td></tr>
		</table> 
        </fieldset>
        <div id="buttons">
            <input type="submit" value="로그인" class="submit">
            <input type="button" value="회원가입" class="cancel" onclick="location.href='mcontract.do'">
            <input type="button" value="아이디 비밀번호 찾기" class="submit" onclick="find_id()">     
        </div><br>
        ${message}<br>
</form>    
</article>

<%@ include file="../include/headerfooter/footer.jsp" %>