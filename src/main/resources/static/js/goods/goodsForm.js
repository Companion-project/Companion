$(function(){
    console.log("goodsForm.js loaded"); // 디버깅을 위한 로그

    const subCategories = {
        '사료': ['건식사료', '습식사료', '자연식'],
        '간식': ['수제간식', '캔/파우치', '영양/기능'],
        '용품': ['장난감', '산책용품', '의류/악세사리']
    };

    $('#goodsCategory').change(function() {
        console.log("대분류 변경됨"); // 디버깅을 위한 로그
        const mainCategory = $(this).val();
        const $subCategory = $('#subCategory');

        $subCategory.empty().append('<option value="">소분류 선택</option>');

        if (mainCategory && subCategories[mainCategory]) {
            console.log("선택된 대분류:", mainCategory); // 디버깅을 위한 로그
            subCategories[mainCategory].forEach(function(subCat) {
                $subCategory.append($('<option>', {
                    value: subCat,
                    text: subCat
                }));
            });
        }
    });

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