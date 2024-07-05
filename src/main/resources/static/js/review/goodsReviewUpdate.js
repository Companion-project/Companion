$(function(){
    $.ajax({
        url : "goodsReviewUpdate",
        type: "post",
        data : { "reviewNum" : reviewNum },
        success : function(responseData){
            result = responseData.replace(/[\x00-\x1F\x7F-\x9F]/g, '\\n');
            var data = JSON.parse(result);
            var html = '<div class="review-form">';
            html += '<form action="goodsReviewModify" method="post">';
            html += '<div class="review-container">';
            html += '<div class="info-row"><div class="info-label">리뷰 번호</div><div class="info-value"><input type="text" name="reviewNum" value="' + data.reviewNum + '" readonly></div></div>';
            html += '<div class="info-row"><div class="info-label">리뷰 내용</div><div class="info-value"><textarea class="review-textarea" name="reviewContent">' + data.reviewContent + '</textarea></div></div>';
            html += '<div class="info-row"><div class="info-label">리뷰 등록일</div><div class="info-value"><input type="date" name="reviewDate" value="' + data.reviewDate + '" readonly></div></div>';
            html += '<div class="submit-row"><input type="submit" value="리뷰등록" class="submit-button"></div>';
            html += '</div></form></div>';
            $("#container").html(html);
        }
    });
});