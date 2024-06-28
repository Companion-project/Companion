$(function(){
    $("#frm").submit(function(){
        $(this).ajaxSubmit({
            type:'post',
            url:'myPwUpdate',
            dataType:'text',
            beforeSubmit:passwordChecked,
            success:resultOk,
            error:function(){
                alert("서버오류")
            }
        });
        return false;
    });
});

//새 비밀번호 유효성 검사 및 중복검사 처리
function passwordChecked(){
    var oldPw = $("#oldPw").val();
    var newPw = $("#newPw").val();
    var newPwCon = $("#newPwCon").val();
    var pwPattern = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/;

    if(oldPw == ""){
        $("#pwErr").text("현재 비밀번호를 입력해 주세요.");
        $("#pwErr").css("color","red");
        return false;
    }

    if(newPw === oldPw){
        $("#newPwErr").text("기존 비밀번호와 다른 비밀번호를 입력해주세요.");
        $("#newPwErr").css("color", "red");
        return false;
    }

    if(newPw == ""){
        $("#newPwErr").text("새 비밀번호를 입력해주세요.");
        $("#newPwErr").css("color", "red");
        return false;
    }

    if(!pwPattern.test(newPw)){
        $("#newPwErr").text("비밀번호는 영문자, 숫자, 특수문자 포함 8글자 이상을 입력해야 합니다.");
        $("#newPwErr").css("color","red");
        return false;
    }

    if(newPw != newPwCon){
        $("#pwConErr").text("비밀번호 확인이 일치하지 않습니다.");
        $("#pwConErr").css("color","red");
            return false;
        }
    return true;
}

//status는 서버 실행이 잘 되었는지 확인
//result는 서버로부터 넘어온 값(여기서는 true, false로 받음)
function resultOk(result, status, xhr, $form){
    if(status == 'success'){
        if(result == "success"){ // 정상적으로 서버가 실행되었으면 로그아웃
            alert("비밀번호가 성공적으로 변경되었습니다. 다시 로그인 해주세요.");
            location.href="/login/logout"; // 로그아웃 URL로 리다이렉트
        }else{ // 현재 비밀번호가 틀렸다면 오류 메세지, 다시 입력
            $("#oldPw").val("");
            $("#pwErr").text("잘못된 비밀번호 입니다.");
            $("#pwErr").css("color","red");
            $("#newPwErr").text("");
            $("#pwConErr").text("");
            $("#oldPw").focus();
        }
    }
}