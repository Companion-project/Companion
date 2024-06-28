package com.example.companion.mapper;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userInsert(MemberDTO dto);
    int userCheckUpdate(String email);
    AuthInfoDTO loginSelect(String userId);
}
