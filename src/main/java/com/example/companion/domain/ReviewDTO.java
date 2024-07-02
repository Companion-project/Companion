package com.example.companion.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
public class ReviewDTO {
    String goodsNum;

    String purchaseNum;
    Date reviewDate;
    String reviewContent;

    Integer score;
    Integer reviewNum;
    String memberId;
}
