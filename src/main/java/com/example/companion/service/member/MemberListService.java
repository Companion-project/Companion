package com.example.companion.service.member;

import com.example.companion.domain.MemberDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.MemberMapper;
import com.example.companion.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MemberListService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    StartEndPageService startEndPageService;
    String searchWord;

    public void execute(Model model, String searchWord, int page){
        //searchWord 양옆 공백문자가 올 수 있으므로 제거
        if(searchWord != null){ //searchWord가 null이 아닌 경우에만
            this.searchWord = searchWord.trim();
        }

        StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
        List<MemberDTO> list = memberMapper.selectAll(sepDTO);

        //전체 회원수를 가지고 옴
        int count = memberMapper.memberCount(this.searchWord);
        startEndPageService.execute(page, count, model, list, this.searchWord);

    }
}
