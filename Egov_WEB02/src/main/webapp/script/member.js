function find_id_pw(){
	var url = "findIdPw";
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=700, ";
	opt = opt + "height=500, top=300, left=300";
	window.open(url, "Find Id/Pw", opt);
}




function go_update(){
	if (document.formm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");
	    document.formm.pwd.focus();
	}else if ((document.formm.pwd.value != document.formm.pwdCheck.value)) {
	    alert("비밀번호가 일치하지 않습니다.");
	    document.formm.pwd.focus();
	}else if (document.formm.name.value == "") {
	    alert("이름을 입력해 주세요.");
	    document.formm.name.focus();
	} else if (document.formm.email.value == "") {
	    alert("이메일을 입력해 주세요.");
	    document.formm.email.focus();
	}else {
	    document.formm.action = "memberUpdate";
	    document.formm.submit();
	}
}





function go_save(){
	 if (document.formm.id.value == "") {
	     alert("아이디를 입력하여 주세요."); 	     
		document.formm.id.focus();
	 }else if(document.formm.id.value != document.formm.reid.value){
	    alert("아이디 중복확인을 클릭해주세요"); 	  
		document.formm.id.focus();
	 }else if (document.formm.pwd.value == "") {
	     alert("비밀번호를 입력해 주세요."); 	     
		document.formm.pwd.focus();
	 } else if ((document.formm.pwd.value != document.formm.pwdCheck.value)) {
	     alert("비밀번호가 일치하지 않습니다."); 	     
		document.formm.pwd.focus();
	 } else if (document.formm.name.value == "") {
	     alert("이름을 입력해 주세요."); 	     document.formm.name.focus();
	 } else if (document.formm.email.value == "") {
	     alert("이메일을 입력해 주세요."); 	     document.formm.email.focus();
	 } else {
	     document.formm.action = "join";
	     document.formm.submit();
	 }
}





function post_zip(){
	var url = "findZipNum.do";
	var pop = "toolbar=no, menubar=no, scrollbars=no, "
	 + "resizable=no, width=550, height=300, top=300, left=300";
	window.open( url, "find Zip num",pop);
}




function idcheck(){
	 if(document.formm.id.value==""){
	  	alert('아이디를 입력하여 주세요');
	  	document.formm.id.focus();
	  	return;
	 }
	 var url = "idCheckForm.do?id=" + document.formm.id.value;
	 var pop = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=250";
 	 window.open( url, "IdCheck", pop);
}




function go_next() {
	if (document.formm.okon[0].checked == true) {
    	document.formm.action = "joinForm.do";
    	document.formm.submit();
  	} else if (document.formm.okon[1].checked == true) {
    	alert('약관에 동의하셔야만 합니다.');
  	}
}




function loginCheck(){
	if(document.loginFrm.id.value==""){
		alert("아이디를 입력하세요");
		document.loginFrm.id.focus();
		return false;
	}
	if(document.loginFrm.pwd.value==""){
		alert("비밀번호를 입력하세요");
		document.loginFrm.pwd.focus();
		return false;
	}
	return true;
}