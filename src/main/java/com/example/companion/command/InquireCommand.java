package com.example.companion.command;

import lombok.Data;

@Data
public class InquireCommand {
    Integer inquireNum;
    String goodsNum;
    String inquireKind;
    String inquireSubject;
    String inquireContent;
    String inquireAnswer;
    String memberNum;
}
