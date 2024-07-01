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

        Map<String, Map<String, List<GoodsDTO>>> categoryMap = new HashMap<>();

        categoryMap.put("사료", new HashMap<>());
        categoryMap.get("사료").put("건식사료", new ArrayList<>());
        categoryMap.get("사료").put("습식사료", new ArrayList<>());
        categoryMap.get("사료").put("자연식", new ArrayList<>());

        categoryMap.put("간식", new HashMap<>());
        categoryMap.get("간식").put("수제간식", new ArrayList<>());
        categoryMap.get("간식").put("캔/파우치", new ArrayList<>());
        categoryMap.get("간식").put("영양/기능", new ArrayList<>());

        categoryMap.put("용품", new HashMap<>());
        categoryMap.get("용품").put("장난감", new ArrayList<>());
        categoryMap.get("용품").put("산책용품", new ArrayList<>());
        categoryMap.get("용품").put("의류/악세사리", new ArrayList<>());

        for (GoodsDTO dto : list) {
            String mainCategory = dto.getGoodsCategory();
            String subCategory = dto.getSubCategory();

            if (mainCategory != null && subCategory != null &&
                categoryMap.containsKey(mainCategory) &&
                categoryMap.get(mainCategory).containsKey(subCategory)) {
                categoryMap.get(mainCategory).get(subCategory).add(dto);
            }
        }

        model.addAttribute("categoryMap", categoryMap);
    }
}