package com.example.companion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("goods")
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO {
    String goodsNum;
    String goodsName;
    Integer goodsPrice;
    Integer deliveryCost;
    String goodsContent;
    String empNum;

    //카테고리 추가
    String goodsCategory;
    String subCategory;

    Integer visitCount;
    Date goodsRegist;
    String updateEmpNum;
    Date goodsUpdateDate;

    //디비에 파일명 저장
    String goodsMainStore;
    String goodsMainStoreImg;
    String goodsImages;
    String goodsImagesImg;
}
