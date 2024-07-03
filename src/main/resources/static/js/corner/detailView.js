$(function(){
    // 페이지가 열릴 때 리뷰정보가 출력되게 함수를 실행.
    goodsReivew();

    $("#buyItem").click(function(){
        location.href="../buyItem?goodsNum=" + goodsNum + "&qty="+$("#qty").val();
    });

    $("#qty").click(function(){
        const money = goodsPrice * $("#qty").val();
        const moneyFormatter = new Intl.NumberFormat('ko-KR',{style:'currency',currency:'KRW'});
        const formattedMoney = moneyFormatter.format(money);
        $("#tot").text(formattedMoney + '원');
    });

    $("#cartBtn").click(function(){
        if(auth == null){
            window.open("/login/item.login","login","width=315, height=200,top = 100, left=100");
        }else{
            $.ajax({
                type:"get",
                url:"../cartAdd",
                dataType:"text",
                data:{"goodsNum" : goodsNum ,"qty":$("#qty").val()},
                success:function(result){
                    if(result.trim() == "200"){
                        if(confirm("계속 쇼핑하려면 '취소'를 클릭하세요.")){
                            location.href="../cartList";
                        }else if(result.trim() == "999"){
                            alert("관리자는 사용할 수 없는 페이지입니다.");
                        }else if(result.trim() == "000"){
                            alert("로그인을 해야합니다.");
                        }
                    }
                },
                error:function(){
                    alert("관리자는 사용할 수 없는 페이지입니다.");
                }
            });
        }
    });

    $("#wish").click(function(){
        if(auth == null){
            window.open("/login/item.login","login","width=315, height=200,top = 100, left=100");
        }else{
            $.ajax({
                type:"post",
                url:"../goodsWishAdd",
                dataType:"text",
                data:{"goodsNum" : goodsNum },
                success:function(result){
                    if(result.trim() == "1"){
                        $("#wish").attr("src","/static/images/heart.png");
                    }else if(result.trim() == "0"){
                        $("#wish").attr("src","/static/images/heart1.png");
                    }else if(result.trim() == "999"){
                        alert("관리자는 사용할 수 없습니다.");
                    }
                },
                error:function(){
                    alert("관리자는 사용할 수 없습니다.");
                }
            });
        }
    });

    $("#review").click(function(){
        goodsReivew();
    });

    $("#inquire").click(function(){
        inquire();
    });

});
//메인페이지들어왔다가 다시 들어오면 관심상품이 등록되어 있어도 표시가 안된다.
//구매페이지들어올때 관심상품이 있는 회원인지 확인을 해야 한다.
// 현재 우리는 회원만 등록 가능하고 직원은 등록할 수 없다. 직원인 경우 코드를 추가해준다.

function goodsReivew(){
    $.ajax({
        type : "post" ,
        url : "/review/reviewList",
        dataType : "html",
        data : {"goodsNum" : goodsNum },
        success : function(result){
            $("#notice").html(result);
        },
        error : function(){
            alert("서버오류!!!!");
            return false;
        }
    });
}
// 상품문의 를 불러올 함수를 만들겠습니다.
function inquire(){ // 상품정보 , 리뷰, 상품문의가 모두 코드가 같습니다. 데이터를 불러올 주소만 다릅니다.
    $.ajax({
        type : "post" ,
        url : "/inquire/inquireList",
        dataType : "html",
        data : {"goodsNum" : goodsNum },
        success : function(result){ //result를 html로 받아와서 출력하겠습니다.
            $("#notice").html(result);
        },
        error : function(){
            alert("서버오류!!!!");
            return false;
        }
    });
}