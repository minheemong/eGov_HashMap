function mgo_cart() {
  if (document.formm.quantity.value == "") {
    alert("수량을 입력하여 주세요.");
    document.formm.quantity.focus();
  } else {
    document.formm.action = "mcartInsert";
    document.formm.submit();
  }
}


function mgo_cart_delete() {
	var count=0;
	if(document.formm.cseq.length==undefined){
		if (document.formm.cseq.checked == true)
		      count++;
	} else {
		for(var i=0; i<document.formm.cseq.length; i++)
			if (document.formm.cseq[i].checked == true) 
				count++;
	}	
	if (count == 0) {
	    alert("삭제할 항목을 선택해 주세요.");
	} else{
		document.formm.action = "mcartDelete";
	    document.formm.submit();
	}
}
