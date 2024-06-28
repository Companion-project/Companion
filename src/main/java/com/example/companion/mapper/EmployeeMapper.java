package com.example.companion.mapper;

import com.example.companion.domain.EmployeeDTO;
import com.example.companion.domain.StartEndPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    public String autoNum();
    public Integer employeeInsert(EmployeeDTO dto);
    public List<EmployeeDTO> employeeAllSelect(StartEndPageDTO sepDTO);
    public int employeeCount(String searchWord);
	public Integer employeesDelete( @Param("employeesNum") String empsDel[]);
    public EmployeeDTO employeeOneSelect(String empNum);
    public Integer employeeUpdate(EmployeeDTO dto);
    public Integer employeeDelete(String empNum);
    public String getEmpNum(String empId);

}
