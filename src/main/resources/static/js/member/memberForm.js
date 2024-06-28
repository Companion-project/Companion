$(function(){
    $("#memberId").on("change keyup", function(){
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
});
