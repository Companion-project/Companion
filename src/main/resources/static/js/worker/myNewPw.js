function resultOk(result, status, xhr, $form){
    if(status == 'success'){
        if(result == "true"){
            location.href = "empMyPage";
        }else{
            $("#oldPw").val("");
            $("#pwErr").text("비밀번호가 틀렸습니다.");
            $("#pwErr").css("color","red");
            $("#newPwErr").text("");
            $("#pwConErr").text("");
            $("#oldPw").focus();
        }
    }
}
function passwordChecked(){
    if($("#oldPw").val() == ""){
       $("#pwErr").text("현재 비밀번호를 입력해주세요.");
       $("#pwErr").css("color","red");
       alert("현재비밀번호를 입력해주세요.");
       return false;
    }
    if($("#newPw").val() != ""){
        if($("#newPw").val() != $("#newPwCon").val()){
            $("#newPwErr").text("");
            $("#pwConErr").text("비밀번호 확인이 일치하지 않습니다.");
            $("#pwConErr").css("color","red");
            $("#newPw").val("");
            $("#newPwCon").val("");
            $("#newPw").focus();

            return false;
        }
    }else{
        $("#newPwErr").text("새 비밀번호를 입력해주세요.");
        $("#newPwErr").css("color","red");
        return false;
    }
}
$(function(){
    $("#frm").submit(function(){
        $(this).ajaxSubmit({
            type : 'post',
            url : 'empPwUpdate',
            dataType : 'text',
            beforeSubmit : passwordChecked,
            success : resultOk,
            error : function(){
                alert("서버오류");
            }
        });
        return false;
    });
});