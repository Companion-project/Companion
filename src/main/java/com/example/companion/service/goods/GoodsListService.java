package com.example.companion.service.goods;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.GoodsMapper;
import com.example.companion.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GoodsListService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    StartEndPageService startEndPageService;

    String searchWord;

    public void execute(String searchWord, Model model, int page){
        if(searchWord != null){
            this.searchWord = searchWord.trim();
        }
        StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
        List<GoodsDTO> list = goodsMapper.allSelect(sepDTO);

        int count = goodsMapper.goodsCount(this.searchWord);
        startEndPageService.execute(page, count, model, list, this.searchWord);
    }
}
