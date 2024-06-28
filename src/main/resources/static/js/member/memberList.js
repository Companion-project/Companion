//하나이상 선택하도록 설정
$(function(){
    $("#frm").submit(function(){
        if($("input:checkbox[name=memDels]:checked").length < 1 ){
            alert("하나이상 선택하세요.");
            return false;
        }
    });
    //checkBoxs를 선택 시 모두 선택되도록 설정
    $("#checkBoxs").click(function(){
        if($("#checkBoxs").prop("checked")){
            $("input:checkbox[name=memDels]").prop("checked", true);
        }else{
            $("input:checkbox[name=memDels]").prop("checked", false);
        }
    });
    //memDels 체크를 하나씩 없애면 checkBoxs에 체크가 없어지고 모두 체크하면 checkBoxs도 체크가 되도록
    $("input:checkbox[name=memDels]").click(function(){
        //먼저 체크박스의 개수를 가지고 온다.
        var tot = $("input:checkbox[name=memDels]").length;
        //먼저 체크박스의 개수를 가지고 온다.
        var checked = $("input:checkbox[name=memDels]:checked").length;
        //두개를 비교
        if(tot == checked){
            //같으면 checkBoxs에 체크
            $("#checkBoxs").prop("checked",true);
        }else{
            $("#checkBoxs").prop("checked",false);
        }
    });
});