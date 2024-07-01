$(function() {
    $('a[href^="goodsDel"]').click(function(e) {
        e.preventDefault();
        alert("입고된 상품은 삭제할 수 없습니다.");
        return false;
    });
});