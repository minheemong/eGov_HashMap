function go_order_insert(){
	document.formm.action = "orderInsert.do";
	document.formm.submit();
}



function go_cart_delete() {
	var count=0;
	if(document.formm.cseq.length==undefined){
		// 체크박스가 한개인경우
		if (document.formm.cseq.checked == true)
		      count++;
	} else {
		// 체크박스가 여러개인경우
		for(var i=0; i<document.formm.cseq.length; i++)
			if (document.formm.cseq[i].checked == true) 
				count++;
	}	
	if (count == 0) {
	    alert("삭제할 항목을 선택해 주세요.");
	} else{
		document.formm.action = "cartDelete.do";
	    document.formm.submit();
	}
}





function go_cart() {
  if (document.formm.quantity.value == "") {
    alert("수량을 입력하여 주세요.");
    document.formm.quantity.focus();
  } else {
    document.formm.action = "cartInsert.do";
    document.formm.submit();
  }
}







