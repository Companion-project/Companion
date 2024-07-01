package com.example.companion.service.goods;

import com.example.companion.command.GoodsCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.mapper.EmployeeMyMapper;
import com.example.companion.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class GoodsWriteService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(GoodsCommand goodsCommand, HttpSession session){
        GoodsDTO dto = new GoodsDTO();
        dto.setGoodsContent(goodsCommand.getGoodsContent());
        dto.setGoodsName(goodsCommand.getGoodsName());
        dto.setGoodsNum(goodsCommand.getGoodsNum());
        dto.setGoodsPrice(goodsCommand.getGoodsPrice());
        dto.setDeliveryCost(goodsCommand.getDeliveryCost());
        dto.setGoodsCategory(goodsCommand.getGoodsCategory());
        AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
        String empId = auth.getUserId();
        String empNum = employeeMapper.getEmpNum(empId);
        dto.setEmpNum(empNum);

        long maxFileSize = 20 * 1024 * 1024; // 이미지 크기 20메가

        //이미지 파일 추가 로직
        //파일저장 경로
        URL resource = getClass().getClassLoader().getResource("static/upload");
        String fileDir = resource.getFile();
        //메인이미지
        MultipartFile mf = goodsCommand.getGoodsMainStore(); //command에서 이미지 객체 가지고 오기
        if (mf.getSize() > maxFileSize) {
            throw new RuntimeException("이미지 파일은 20MB 이하로 첨부해주세요.");
        }
        String originalFile = mf.getOriginalFilename(); //파일객체에서 upload할 때 사용한 파일명
        String extension = originalFile.substring(originalFile.lastIndexOf(".")); //파일명에서 확장자 추출
        String storeName = UUID.randomUUID().toString().replace("-",""); //랜덤아이디 부여
        String storeFileName = storeName + extension; //새로운 파일명 만들어주기
        //새로운 파일명으로 파일 저장
        File file = new File(fileDir + "/" + storeFileName);
        try {
            mf.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // dto에 파일명 저장
        dto.setGoodsMainStore(storeFileName);  //static/upload에 저장된 이름
        dto.setGoodsMainStoreImg(originalFile);  //upload할 때 사용된 이름
        // multiple로 가져온 파일처리, 파일이 있는지 먼저 확인하기
        if(!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()){
            String originalTotal = "";
            String storeTotal = "";
            //파일이 여러개이므로 반복
            for(MultipartFile mtf : goodsCommand.getGoodsImages()){
                if (mtf.getSize() > maxFileSize) {
                    throw new RuntimeException("이미지 파일은 20MB 이하로 첨부해주세요.");
                }
                originalFile = mtf.getOriginalFilename();
                extension = originalFile.substring(originalFile.lastIndexOf("."));
                storeName = UUID.randomUUID().toString().replace("-","");
                storeFileName = storeName + extension;

                file = new File(fileDir + "/" + storeFileName);
                try {
                    mtf.transferTo(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                originalTotal += originalFile + "-";
                storeTotal += storeFileName + "-";
            }
            // DTO에 저장
            dto.setGoodsImages(storeTotal);
            dto.setGoodsImagesImg(originalTotal);
        }
        goodsMapper.goodsInsert(dto);
    }
}