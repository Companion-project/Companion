package com.example.companion.service.goods;

import com.example.companion.command.GoodsCommand;
import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoodsAutoNumService {
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(Model model){
        //"prod" 글자수에  따라 숫자의 변화를 줄 수 있다
        String goodsNum = goodsMapper.incomingAndGoodsAutoNum("goods","goods_num","gd",3);
        // ex) gd100001 ~ 자동Num부여
        GoodsCommand goodsCommand = new GoodsCommand();
        goodsCommand.setGoodsNum(goodsNum);
        model.addAttribute("goodsCommand", goodsCommand);
    }
}
