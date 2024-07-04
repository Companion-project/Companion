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
            alert("하나 이상 선택하세요.");
            return false;
        }

        // 입고된 상품 선택 시 알림 메시지 표시
        var incomingSelected = $("input:checkbox[name=memDels]:checked:disabled").length > 0;
        if(incomingSelected){
            alert("입고된 상품은 선택삭제 할 수 없습니다.");
            return false;
        }
    });
});