$(function(){
	$("#checkBoxs").click(function(){
		if($("#checkBoxs").prop("checked")){
			$("input:checkbox[name=empDels]").prop("checked",true);
		}else{
			$("input:checkbox[name=empDels]").prop("checked",false);
		}
	});

	$("input:checkbox[name=empDels]").click(function(){
		var tot = $("input:checkbox[name=empDels]").length;
		var checked = $("input:checkbox[name=empDels]:checked").length;
		if(tot == checked){
			$("#checkBoxs").prop("checked",true);
		}else{
			$("#checkBoxs").prop("checked",false);
		}
	});
	$("#frm").submit(function(){
		if($("input:checkbox[name=empDels]:checked").length < 1 ){
			alert("하나이상 선택해주세요.");
			return false;
		}
	});
});
    function goBack() {
        window.history.back();
    }
