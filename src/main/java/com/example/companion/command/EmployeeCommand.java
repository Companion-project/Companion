package com.example.companion.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeCommand {
    String empNum;
    @NotEmpty(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]{5,20}$",
            message = "아이디는 영어 소문자와 숫자만 사용하여 5~20글자 내에서 입력해주세요.")
    String empId;
    //비밀번호는 패턴 사용. 영문자+특문+숫자+8글자 이상
    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#!@$%^&*-+?~]).{8,}$",
            message = "영문자와 숫자 그리고 특수문자 포함 8글자 이상을 입력해주세요.")
    String empPw;
    @NotEmpty(message = "비밀번호 확인을 입력해주세요.")
    String empPwCon;
    @NotBlank(message = "이름을 입력해주세요.")
    String empName;
    @NotBlank(message = "주소를 입력해주세요.")
    String empAddr;
    String empAddrDetail;
    String empPost;
    @NotBlank(message = "연락처를 입력해주세요.")
    String empPhone;
    @Email(message = "형식에 맞지 않습니다.")
    @NotEmpty(message = "이메일을 입력해주세요.")
    String empEmail;
    @NotEmpty(message = "주민번호를 입력해주세요.")
    String empSsn;
    String empRegiDate;

    //empPw와 empPw비교
    public boolean isEmpPwEqualsEmpPwCon(){
        return empPw.equals(empPw);
    }
}
