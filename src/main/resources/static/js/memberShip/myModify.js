$(function(){
    $("#frm").submit(function(){
        if($("#emailCheck").text()=="" || $("emailCheck").text() == "사용중인 이메일입니다."){
            alert("이메일 중복확인을 해주세요.");
            return false;
        }
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
});