$(function() {
    const subCategories = {
        '사료': ['건식사료', '습식사료', '자연식'],
        '간식': ['수제간식', '캔/파우치', '영양/기능'],
        '용품': ['장난감', '산책용품', '의류/악세사리']
    };

    function updateSubCategories() {
        const mainCategory = $('#goodsCategory').val();
        const $subCategory = $('#subCategory');
        const currentSubCategory = $subCategory.val();

        $subCategory.empty().append('<option value="">소분류 선택</option>');

        if (mainCategory && subCategories[mainCategory]) {
            subCategories[mainCategory].forEach(function(subCat) {
                $subCategory.append($('<option>', {
                    value: subCat,
                    text: subCat,
                    selected: subCat === currentSubCategory
                }));
            });
        }
    }

    // 페이지 로드 시 초기 소분류 설정
    updateSubCategories();

    $('#goodsCategory').change(function() {
        updateSubCategories();
    });

    $("#frm").submit(function(event) {
        if (!$('#goodsCategory').val() || !$('#subCategory').val()) {
            alert("대분류와 소분류를 모두 선택해주세요.");
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