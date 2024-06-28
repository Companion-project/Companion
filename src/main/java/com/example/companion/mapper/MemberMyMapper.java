package com.example.companion.mapper;

import com.example.companion.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMyMapper {
    public MemberDTO memberInfo(String memberId);
    public int memberPwUpdate(String userPw, String memberId);
    public int memberDrop(String memberId);
    public int memberInfoUpdate(MemberDTO dto);
}
