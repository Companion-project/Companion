package com.example.companion.mapper;

import com.example.companion.domain.GoodsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    public List<GoodsDTO> goodsSelectAll();
}
