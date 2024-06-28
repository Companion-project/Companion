package com.example.companion.service.employees;

import com.example.companion.domain.EmployeeDTO;
import com.example.companion.domain.StartEndPageDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.service.StartEndPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class EmployeeListService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StartEndPageService startEndPageService;

String searchWord;
public void execute(String searchWord, int page, Model model) {
    if(searchWord != null ) {
			this.searchWord = searchWord.trim();
		}
    StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
	List<EmployeeDTO> list = employeeMapper.employeeAllSelect(sepDTO);

    int count = employeeMapper.employeeCount(this.searchWord);
    startEndPageService.execute(page, count, model, list, this.searchWord);

        //model.addAttribute("list",list); 페이징 검색 처리 후 서비스에서 model.addAttribute("dtos", list);

	}
}
