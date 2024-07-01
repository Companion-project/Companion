$(function(){
    itemList(1);

    // 검색 버튼 클릭 이벤트 처리
    $(document).on("click", "#searchBtn", function(){
        itemList(1);
    });
});

function itemList(page){
    $.ajax({
        url:"goodsItemList",
        type:"post",
        datatype: "json",
        data : {'searchWord': $("#searchWord").val(), "page":page},
        success:function(result){
            if(result.searchWord == null){
                result.searchWord = "";
            }
            var item = "";
            item +=    '<table>';
            item +=    '    <tr><th>';
            item +=    '        검색 : <input type="search" name="searchWord" id="searchWord" value="'+result.searchWord+'">';
            item +=    '        <button type="button" id="searchBtn">검색</button>';
            item +=    '    </th></tr>';
            item +=    '</table>';
            item +=    '<table border=1 width="600" align="center">';
            item +=    '    <tr>';
            item +=    '        <th>번호</th><th>상품 번호</th><th>상품명</th><th>가격</th>';
            item +=    '    </tr>';
            $.each(result.dtos, function(idx, goodsDTO){
                item +=    '    <tr>';
                item +=    '        <th>'+ eval(idx + 1) +' </th>'
                     +'         <th><a href="javascript:goodsItem(\'' + goodsDTO.goodsNum +'\''
                     +'              , \''+ goodsDTO.goodsName + '\')">'+ goodsDTO.goodsNum +'</a></th>'
                     +'         <th><a href="javascript:goodsItem(\'' + goodsDTO.goodsNum +'\''
                     +'              , \''+ goodsDTO.goodsName + '\')">'+ goodsDTO.goodsName +'</a></th>'
                     +'         <th>'+ goodsDTO.goodsPrice +'</th>';
                item += '    </tr>';
            });
            item +=    '<tr align="center"><td colspan="6">';
            if(result.page <= 1){
                item +=    '[이전]';
            }
            if(result.page > 1){
                item +=    '<a href="javascript:itemList('+ eval(result.page - 1) + ')">[이전]</a>';
            }
            var startPage = result.startPage; console.log(startPage);
            var endPage = result.endPage; console.log(endPage);
            while (startPage <= endPage){
                item +=    '<a href="javascript:itemList('+ startPage + ')">['+startPage+']</a> ';
                startPage++;
            }
            if(result.page >= result.maxPage){
                item +=    '[다음]';
            }
            if(result.page < result.maxPage){
                item +=    '<a href="javascript:itemList('+ eval(result.page + 1) + ')">[다음]</a>';
            }
            item +=    '</td></tr>';
            item +=    '</table>';
            $("#goodsList").html(item);
        },
        error:function(){
            alert("서버오류");
        }
    });
}

function goodsItem(goodsNum, goodsName){
    $(opener.document).find("#goodsNum").val(goodsNum);
    $(opener.document).find("#goodsName").val(goodsName);
    window.self.close();
}