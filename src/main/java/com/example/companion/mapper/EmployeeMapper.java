package com.example.companion.mapper;

import com.example.companion.domain.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    public String autoNum();
    public Integer employeeInsert(EmployeeDTO dto);
    public List<EmployeeDTO> employeeAllSelect();
}
