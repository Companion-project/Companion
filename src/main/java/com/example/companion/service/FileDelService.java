package com.example.companion.service;

import com.example.companion.command.FileCommand;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileDelService {

    public String execute(FileCommand fileCommand, HttpSession session){
        String num = ""; //저장할 파일인지 아닌지 정해줄 변수 선언
        // 새로운 상품인지 아닌지 확인하는 변수
        Boolean newFile = true;
        //파일을 무조건 지우면 안되니 일단 임시 삭제할 파일을 session에 저장
        //이미 삭제할 파일 정보를 가진 session이 있는지 확인
        //여러개를 삭제할 수 있도록 List에 저장해서 session
        List<FileCommand> list = (List<FileCommand>)session.getAttribute("fileList");
        //첫  상품인 경우 session없음, session이 없으면 list부터 만든다
        if(list == null){
            list = new ArrayList<FileCommand>();
        }
        //session이 있다면 list가 있으므로 list에 어떤 상품이 존재하는지 확인
        for(int i = 0; i< list.size(); i++){
            //만약 리스트가 존재하고 파일정보가 있다면 취소
            if(list.get(i).getStoreFile().equals(fileCommand.getStoreFile())){
                list.remove(i);
                newFile = false; //새 파일이 아니므로 리스트에 추가되지 않게 false
                num = "0";
                break;
            }
        }
        //삭제할 파일이 새로 선택된 경우
        if(newFile){
            list.add(fileCommand);
            num = "1";
        }
        //변경된 내용을 session에 저장
        session.setAttribute("fileList", list);
        return num;  // num을 ajax에 전달, 삭제취소가 된 파일은 0, 삭제하려는 파일은 1을 전달
    }

}
