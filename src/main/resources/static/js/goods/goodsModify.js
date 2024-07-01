$(function() {
    $("#frm").submit(function(event) {
        if (!$('#goodsCategory').val()) {
            alert("카테고리를 선택해주세요.");
            event.preventDefault();
        }
    });
});

function fileDel(orgFile, storeFile, btn, kind) {
    $.ajax({
        type: 'post',
        url: 'fileDel',
        dataType: 'text',
        data: {"orgFile": orgFile, "storeFile": storeFile, "kind": kind},
        success: function(result) {
            if(result.trim() == "1") {
                $(btn).text("삭제취소");
                if(kind == 'main') {
                    $("#main").css("display", "none");
                    $("#mainFile").css("display", "");
                }
            } else {
                $(btn).text("삭제");
                if(kind == 'main') {
                    $("#main").css("display", "");
                    $("#mainFile").css("display", "none");
                }
            }
        }
    });
}