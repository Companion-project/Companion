package com.example.companion.command;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class EmployeeCommand {
    String empNum;
	@NotEmpty(message = "아이디를 입력해주세요")
	@Size(min = 4, max = 8, message = "아이디는 4글자이상 8글자이하로 작성해주세요")
	String empId;
	@Pattern( regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
			 message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상으로 작성해주세요")
	String empPw;
	@NotEmpty(message = "비밀번호확인 입력해 주세요")
	String empPwCon;
	@NotBlank(message = "이름을 입력해 주세요")
	String empName;
	@NotBlank(message = "주소를 입력해 주세요")
	String empAddr;
	String empAddrDetail;
	String empPost;
	@NotBlank(message = "연락처를 입력해 주세요")
	@Pattern(regexp = "^[\\d]{2,3}-[\\d]{3,4}-[\\d]{4}+$", message = "전화번호를 정확히 입력해주세요")
	String empPhone;
	@Email(message = "형식에 맞지 않습니다(OOO@OOO.OOO)")
	@NotEmpty(message = "이메일을 입력해 주세요")
	String empEmail;
	@NotEmpty(message = "주민번호를 입력해 주세요")
	@Pattern(regexp = "^[\\d]{6}-[1-4][\\d]{6}+$", message = "주민번호를 정확히 입력해주세요")
	String empssn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empRegiDate;
	//empPw와 empPwCon를 비교하기 위한 메서드
	public boolean isEmpPwEqualsEmpPwCon() {
		return empPw.equals(empPwCon);
	}
}
