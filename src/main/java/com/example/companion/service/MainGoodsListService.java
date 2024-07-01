package com.example.companion.service;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class MainGoodsListService {
    @Autowired
    CartMapper cartMapper;

    public void execute(Model model) {
        List<GoodsDTO> list = cartMapper.goodsSelectAll();

        Map<String, List<GoodsDTO>> categoryMap = new HashMap<>();

        // 미리 정의된 카테고리 목록
        List<String> categories = Arrays.asList("사료", "간식", "용품");

        // 카테고리 맵 초기화
        for (String category : categories) {
            categoryMap.put(category, new ArrayList<>());
        }

        // 상품을 해당 카테고리에 분류
        for (GoodsDTO dto : list) {
            String category = dto.getGoodsCategory();
            if (category != null && categoryMap.containsKey(category)) {
                categoryMap.get(category).add(dto);
            }
        }

        model.addAttribute("categoryMap", categoryMap);
    }
}