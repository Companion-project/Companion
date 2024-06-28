package com.example.companion.service.goodsIncoming;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.GoodsMapper;
import com.example.companion.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsItemService {

    //상품정보는 GoodsMapper로 가져오기
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    StartEndPageService startEndPageService;

    String searchWord;

    public Map<String,Object> execute(String searchWord, int page){
        // searchWord null처리
        if(searchWord != null){
            this.searchWord = searchWord.trim();
        }
        Map<String,Object> map = new HashMap<String, Object>();
        //goodsMapper.allSelect 는 sepDTO가 있어야하고 StartEndPageService 필요
        StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
        List<GoodsDTO> list = goodsMapper.allSelect(sepDTO);
        //페이징 코드 추가, model로 보내지 않기때문에 서비스에 선언된 메서드를 사용할 수 없다
        int count = goodsMapper.goodsCount(this.searchWord);
        int limit = 10;
        int limitPage = 10;
        int startPage = (int)((double)page / limitPage + 0.95 -1) * limitPage +1;
        int endPage = startPage + limitPage -1;
        int maxPage = (int)((double)count / limit + 0.95);
        if(maxPage < endPage) endPage = maxPage;
        // model 대신 map에 저장
        map.put("dtos", list);
		map.put("searchWord", this.searchWord);
		map.put("page", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("count", count);
		map.put("maxPage", maxPage);

        return map;
    }
}
