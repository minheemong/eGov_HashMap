function mgo_next() {
	if (document.formm.okon[0].checked == true) {
    	document.formm.action = "mjoinForm";
    	document.formm.submit();
  	} else if (document.formm.okon[1].checked == true) {
    	alert('약관에 동의하셔야만 합니다.');
  	}
}



function midcheck(){
	 if(document.formm.id.value==""){
	  	alert('아이디를 입력하여 주세요');
	  	document.formm.id.focus();
	  	return;
	 }
	 var url = "midCheckForm?id=" + document.formm.id.value;
	 // var pop = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=250";
 	 // window.open( url, "IdCheck", pop);
 	 location.href=url;
}



function mpost_zip(){
	var url = "mfindZipNum?id=";
	url += document.formm.id.value;
	url += "&reid=";
	url += document.formm.reid.value;
	alert(url);
	location.href=url;
}











