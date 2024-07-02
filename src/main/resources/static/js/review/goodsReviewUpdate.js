$(function(){
    $.ajax({
        url : "goodsReviewUpdate",
        type: "post",
        data : { "reviewNum" : reviewNum },
        success : function(responseData){
            // json에서는 \n이 있으면 오류가 발생하므로 아래 코드를 추가해줍니다.
            result = responseData.replace(/[\x00-\x1F\x7F-\x9F]/g, '\\n');
            var data = JSON.parse(result); // json형식으로 넘어온 데이터를 json으로 형변환해야 한다.
            var html = '<table align="center" width="600"><tr><td>';
            html += '<form action="goodsReviewModify" method="post" >';
            html += '리뷰 번호 : <input type="text"  name="reviewNum" value="'+data.reviewNum+'" ><br />';
            html += '리뷰 내용 : <textarea rows="5" cols="30" name="reviewContent">'+data.reviewContent+'</textarea><br />';
            html += '리뷰 등록일 : <input type="Date"  name="reviewDate" value="'+data.reviewDate+'"><br />';
            html += '<input type="submit" value="리뷰등록" />';
            html += '</form>';
            html += '</td></tr></table>';
            $("#container").html(html);
        }
    });
});