package com.example.companion.command;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GoodsIncomingCommand {
    String goodsIncomingNum;
    String goodsNum;
    Integer incomingQty;
    Integer incomingPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date incomingDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime productionDate;
}
