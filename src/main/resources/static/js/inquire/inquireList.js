$(function(){
    $("#btn").click(function(){
        console.log(auth);
        //상품문의를 하는 사람이 누구인지 알아야 하므로 로그인이 안되어 있으면 로그인 창이 떠야 합니다.
        if(auth == null){
            window.open("/login/item.login","login","width=315, height=200,top = 100, left=100");
        }else{
            //상품문의도 popup 으로 하겠습니다..
            window.open("/inquire/inquireWrite?goodsNum="+goodsNum
                ,"inquireWrite","width=700, height=650, top = 100, left=100");
        }
    })
});

function contentOpen(id){
    if($("."+id).css("display") == "none"){ // 답글이 안보인 다면
        $("."+id).css("display", "");  // 답글을 보이게 하고
        $("#"+id).text("답변닫기"); // 버튼은 닫기로 변경
    }else{  // 답글이 보이면 안보이게 하고
        $("."+id).css("display", "none");
        $("#"+id).text("답변열기");  // 버튼은 열기로 변경
    }
}

function inquireUpdate(inquireNum){
    window.open("/inquire/inquireUpdate?inquireNum="+inquireNum, "",
        "width=700, height=650, top=100, left=100")
}

function inquireDelete(inquireNum){
    $.ajax({ // ajax를 사용해서 삭제한 후에 inquire함수를 실행시킨다.
        type:'post',
        url:'/inquire/inquireDelete',
        data: {'inquireNum':inquireNum},
        success:function(){
            inquire();
        }
    });
}