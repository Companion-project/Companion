$(function(){
    console.log("goodsForm.js loaded"); // 디버깅을 위한 로그

    $("#btn").click(function(){
        var formData = new FormData($("#frm")[0]);

        $.ajax({
            url:"goodsWrite",
            type:"post",
            data:formData,
            enctype:"multipart/form-data",
            contentType: false,
            processData: false,
            success:function(result){
                if(result.trim() == 200){
                    location.href="goodsList";
                }else{
                    $("#writeForm").html(result);
                }
            },
            error:function(){
                alert("다시 로그인 해 주세요.")
                location.href="/";
            }
        });
    });
});