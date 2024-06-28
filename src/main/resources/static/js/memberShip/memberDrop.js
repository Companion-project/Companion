$(function(){
    $("#frm").submit(function(){
        if($("#memberPw").val() == ""){
            alert("비밀번호를 입력하세요.");
            $("#memberPw").focus();
            return false;
        }else{
            var conf = confirm("정말로 탈퇴하시겠습니까?");
            if(!conf){
                return false;
            }
        }
    });
});