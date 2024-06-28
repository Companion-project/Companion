$(function(){
	$("#checkBoxs").click(function(){
		if($("#checkBoxs").prop("checked")){
			$("input:checkbox[name=memDels]").prop("checked",true);
		}else{
			$("input:checkbox[name=memDels]").prop("checked",false);
		}
	});
	$("input:checkbox[name=memDels]").click(function(){
		var tot = $("input:checkbox[name=memDels]").length;
		var checked = $("input:checkbox[name=memDels]:checked").length;
		if(tot == checked){
			$("#checkBoxs").prop("checked",true);
		}else{
			$("#checkBoxs").prop("checked",false);
		}
	});
	$("#frm").submit(function(){
		if($("input:checkbox[name=memDels]:checked").length < 1){
			alert("하나이상 선택하세요.");
			return false;
		}
	});
});
