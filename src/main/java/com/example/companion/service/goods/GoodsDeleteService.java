package com.example.companion.service.goods;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

@Service
public class GoodsDeleteService {
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(String goodsNum){

       GoodsDTO dto = goodsMapper.selectOne(goodsNum); // 삭제할 파일의 정보
		int i = goodsMapper.goodsDelete(goodsNum);
		//디비삭제되었는 지 확인하기 위해 정수를 받아 오기
		if(i > 0) {
			URL resource = getClass().getClassLoader().getResource("static/upload");
			String fileDir = resource.getFile();
			File file = new File(fileDir + "/" + dto.getGoodsMainStore());
			if(file.exists())file.delete();//메인이미지 삭제
			if(dto.getGoodsImages() != null) { // 첨부 이미지
				String [] img = dto.getGoodsImages().split("-");
				for(String fileName : img) { // 반복하여 삭제
					file = new File(fileDir + "/" +  fileName);
					if(file.exists())file.delete();
				}
			}
		}
	}
}
