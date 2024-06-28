$(function(){
        $("#memberId").on("change keyup", function(){
            var memberId = $("#memberId").val();
            var memberIdPattern = /^[a-z0-9]{5,20}$/;

            //아이디 유효성 검사
            if(!memberIdPattern.test(memberId)){
            $("#idCheck").text("아이디는 영어 소문자와 숫자만 사용하여 5~20글자 내에서 입력하세요.")
                .css("color","red");
                return;
            }

            //아이디 중복 검사
            $.ajax({
                type:'post',
                url:"/login/userIdCheck",
                data:{"userId":$("#memberId").val()},
                dataType:'text',
                success: function(result){
                    $("#idCheck").text(result);
                    if(result.trim() == "사용가능한 아이디입니다."){
                        $("#idCheck").css("color","blue");
                    }else{
                        $("#idCheck").css("color","red");
                    }
                },
                error:function(){
                }
            });
        });

    $("#memberEmail").on("change keyup", function(){
        $.ajax({
            type:'post',
            url:"/checkRest/userEmailCheck",
            data:{"userEmail":$("#memberEmail").val()},
            dataType:'text',
            success: function(result){
                $("#emailCheck").text(result);
                if(result.trim() == "사용가능한 이메일입니다."){
                    $("#emailCheck").css("color","blue");
                }else{
                    $("#emailCheck").css("color","red");
                }
            },
            error:function(){
            }
        });
    });

    //회원가입 시 사용할 수 없는 아이디, 이메일은 전송하지 못하도록 차단
    $("#frm").submit(function(){
        if($("#idCheck").text() == "" || $("#idCheck").text() == "사용중인 아이디입니다."){
            alert("아이디 중복확인 필요");
            return false;
        }
        if($("#emailCheck").text() == "" || $("emailCheck").text() == "사용중인 이메일입니다."){
            alert("이메일 중복확인 필요");
            return false;
        }
    });
});