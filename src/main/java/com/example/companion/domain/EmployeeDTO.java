package com.example.companion.domain;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {
    String empNum;
    String empId;
    String empPw;
    String empName;
    String empAddr;
    String empAddrDetail;
    String empPost;
    String empPhone;
    String empEmail;
    Date empRegiDate;
    String empSsn;
}
