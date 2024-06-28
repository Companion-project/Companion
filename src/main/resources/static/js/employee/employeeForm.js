$(function(){
	$("#empId").on("change keyup",function(){
		$.ajax({
			type:'post',
			url:"/login/userIdCheck",
			data:{"userId":$("#empId").val()},
			dataType:'text',
			success:function(result){
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
	$("#empEmail").on("change keyup",function(){
		$.ajax({
			type:'post',
			url:"/checkRest/userEmailCheck",
			data:{"userEmail":$("#empEmail").val()},
			dataType:'text',
			success:function(result){
				$("#emailCheck").text(result);
				if(result.trim() == "사용가능한 Email입니다."){
					$("#emailCheck").css("color","blue");
				}else{
					$("#emailCheck").css("color","red");
				}
			},
			error:function(){

			}
		});
	});
	$("#frm").submit(function(){

		if($("#idCheck").text()=="" || $("#idCheck").text()=="사용중인 아이디입니다."){
			alert("아이디 중복확인을 해주세요");
			return false;
		}
		if($("#emailCheck").text()=="" || $("#emailCheck").text()=="사용중인 Email입니다."){
			alert("이메일 중복확인을 해주세요");
			return false;
		}
	});
});