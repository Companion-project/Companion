package com.example.companion.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MemberCommand {
    //html에 전달할 오류메세지 설정
    //String자료형에서는 @NotEmpty, @NotBlank 사용

    String memberNum;
    @NotEmpty(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]{4,8}$",
             message = "아이디는 영어 소문자와 숫자만 사용하여 4~8글자 내에서 입력해주세요.")
    String memberId;
    //비밀번호는 패턴 사용. 영문자+특문+숫자+8글자 이상
    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#!@$%^&*-+?~]).{8,}$",
             message = "영문자와 숫자 그리고 특수문자 포함 8글자 이상을 입력해주세요.")
    String memberPw;
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    String memberPwCon;
    String memberName;
    @NotBlank(message = "주소를 입력해주세요.")
    String memberAddr;
    String memberAddr2;
    String memberPost;
    String memberGender;
    @NotBlank(message = "연락처를 입력해주세요.")
    String memberPhone1;
    String memberPhone2;
    @NotEmpty(message = "이메일을 입력해주세요.")
    String memberEmail;

    //문자형 날짜 자동형변환을 위한 패턴 필요
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //String이 아닌 모든 자료형은 @NotNull사용
    @NotNull(message = "생년월일을 입력해주세요.")
    Date memberBirth;
    //나중에 사용할 멤버필드 추가
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date memberRegiDate;

    Integer point;

    //비밀번호와 비밀번호 확인이 같은지 확인을 위한 메서드
    public boolean isMemberPwEqualsMemberPwCon(){

        return memberPw.equals(memberPwCon);
    }
}
