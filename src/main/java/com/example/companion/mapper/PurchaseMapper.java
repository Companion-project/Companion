package com.example.companion.mapper;

import com.example.companion.domain.OrderListDTO;
import com.example.companion.domain.PaymentDTO;
import com.example.companion.domain.PurchaseDTO;
import com.example.companion.domain.PurchaseListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    public String selectNum();
    public Integer purchaseInsert(PurchaseDTO dto);
    public int purchaseListInsert(PurchaseListDTO dto);
    public PurchaseDTO purchaseSelect(String purchaseNum);
    public int purchaseGoodsCount(String purchaseNum);
    public String firstGoods(String purchaseNum);
    public int paymentInsert(PaymentDTO dto);
    public int purchaseStatusUpdate(@Param("status") String status,
                                    @Param("purchaseNum") String purchaseNum);
    public List<OrderListDTO> orderList(@Param("memberNum")String memberNum,
                                        @Param("purchaseNum")String purchaseNum);
    public int paymentDelete(String memberNum);
    public Integer purchaseOk(String purchaseNum);
}
