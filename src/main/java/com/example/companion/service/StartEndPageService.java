package com.example.companion.service;

import com.example.companion.domain.StartEndPageDTO;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class StartEndPageService {
    int limit;

    public StartEndPageDTO execute(int page, String searchWord){
            //페이징
            limit = 10; //한페이지에 보여줄 회원 수
            int startRow = ((page - 1) * limit) + 1; //시작번호
            int endRow = startRow + limit - 1; //마지막번호
            //startRow, endRow, searchWord를 myBatis에 넘기기 위해 DTO생성
            StartEndPageDTO sepDTO = new StartEndPageDTO();
            sepDTO.setEndRow(endRow);
            sepDTO.setSearchWord(searchWord);
            sepDTO.setStartRow(startRow);

        return sepDTO;
    }

    public void execute(int page, int count, Model model,
        List list, String searchWord){

        //페이지에 보여줄 페이지 번호의 갯수 정해줌
        int limitPage = 10;
        //시작페이지번호와 마지막페이지번호를 가지고 옴
        int startPage = (int)((double)page / limitPage + 0.95 - 1) * limitPage + 1;
        int endPage = startPage + limitPage - 1;
        //전체 페이지 개수를 구함
        int maxPage = (int)((double)count / limit + 0.95);
        //endPage가 maxPage보다 크지 않게 설정
        if(maxPage < endPage) endPage = maxPage;

        model.addAttribute("dtos", list);
        //검색할 때 사용한 단어를 html에 출력되도록 한다.
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("page", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("count", count);
        model.addAttribute("maxPage", maxPage);

    }
}
