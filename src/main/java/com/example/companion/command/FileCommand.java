package com.example.companion.command;

import lombok.Data;

@Data
public class FileCommand {
    String orgFile;
    String storeFile;
    int qty;
}
