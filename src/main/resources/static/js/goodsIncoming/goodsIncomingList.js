$(function(){
    $.ajax({
        type:"post",
        url:"goodsIncomingList",
        datatype:"json",
        success:function(result){
            var item = "";
				item +='<table width=600 border=1 id="incomingTable">';
				item +='	<tr><th>번호</th><th>상품 번호</th><th>수량</th><th>입고일</th><th>가격</th></tr>';

				$.each( result.list ,function(idx, dto){
				        var date = new Date(dto.incomingDate);
                        var year = date.getFullYear();
                        var month = ("0"+(date.getMonth() + 1)).slice(-2);
                        var day = ("0" + date.getDate()).slice(-2);
                    item +='	<tr>';
					item +='		<th>' + eval(idx + 1) +'</th>';
					item +='        <th><a href="javascript:goodsIncomingDetail(\''+dto.incomingNum +'\',\''+ dto.goodsNum+'\')">' + dto.goodsNum + '</a></th>';
					item +='        <th>' + dto.incomingQty +' </th>';
					item +='		<th>' +year + '-' + month+ '-' + day +'</th>';
					item +='		<th>' + dto.incomingPrice + '</th>';
					item +='    </tr>';
				})
		    item +='</table>';
			$("#list").html(item);
        },
        error:function(){
            alert("서버오류");
        }
    });
});
function goodsIncomingDetail(incomingNum, goodsNum) {
    $.ajax({
        type: "post",
        url: "goodsIncomingDetail",
        data: {"incomingNum": incomingNum, "goodsNum": goodsNum},
        success: function(dto) {
            var item = "";
            item += '<div class="goods-details-container">';
            item += '<div class="goods-info">';

            item += createInfoRow("입고번호", dto.incomingNum);
            item += createInfoRow("상품번호", dto.goodsNum);
            item += createInfoRow("입고일", dto.incomingDate);
            item += createInfoRow("입고 수량", dto.incomingQty);
            item += createInfoRow("제조일", dto.productionDate);
            item += createInfoRow("가격", dto.incomingPrice);

            item += '<div class="form-actions">';
            item += '<button class="submit-button" onclick="incomingUpdate(\'' + dto.incomingNum + '\',\'' + dto.goodsNum + '\');">입고 수정</button>';
            item += '<button class="submit-button" onclick="incomingDelete(\'' + dto.incomingNum + '\',\'' + dto.goodsNum + '\');">입고 삭제</button>';
            item += '</div>';

            item += '</div>'; // goods-info 닫기
            item += '</div>'; // goods-details-container 닫기

            $("#list").html(item);
        },
        error: function() {
            alert("서버오류");
        }
    });
}

function createInfoRow(label, value) {
    return '<div class="info-row">' +
           '<div class="info-label">' + label + '</div>' +
           '<div class="info-value">' + value + '</div>' +
           '</div>';
}
function incomingUpdate(incomingNum, goodsNum){
    location.href="goodsIncomingUpdate?incomingNum="+incomingNum+"&num="+goodsNum;
}
function incomingDelete(incomingNum,goodsNum){
    location.href="goodsIncomingDelete?incomingNum="+incomingNum+"&num="+goodsNum;
}