package com.example.companion.service.goods;

import com.example.companion.command.FileCommand;
import com.example.companion.command.GoodsCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsUpdateService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(GoodsCommand goodsCommand, HttpSession session, BindingResult result, Model model){
        GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsCategory(goodsCommand.getGoodsCategory());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setDeliveryCost(goodsCommand.getDeliveryCost());
		// 수정한 직원의 정보를 알기 위해 로그인 정보를 이용
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empId = auth.getUserId();
		String empNum = employeeMapper.getEmpNum(empId);
		dto.setUpdateEmpNum(empNum);

		// 파일 삭제 정보를 가지고 있는 session 가지고 오기
		List<FileCommand> list = (List<FileCommand>)session.getAttribute("fileList");
		// 상품정보를 가지고 와서 파일정보를 가져온다
		GoodsDTO goodsDTO = goodsMapper.selectOne(goodsCommand.getGoodsNum());
		// 삭제할 파일과 upload 할 파일의 디렉터리 정보 가져오기
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();  //워크스페이스의 build폴더
		System.out.println(fileDir); ///경로를 콘솔에 출력
		// 파일 정보를 가지고 올 변수 지정
		MultipartFile mf;
		String originalFile;
		String extension;
		String storeName;
		String storeFileName;
		File file;
		// 메인 이미지 삭제시 새로운 이미지가 있는지 확인 (not null)
		System.out.println(goodsCommand.getGoodsMainStore());
		if(!goodsCommand.getGoodsMainStore().getOriginalFilename().isEmpty()){
			// 메인 이미지 정보 가져오기
			mf = goodsCommand.getGoodsMainStore();    //command에 있는 메인 이미지를 불러오기
			originalFile = mf.getOriginalFilename();  //원본파일명 가져오기
			extension = originalFile.substring(originalFile.lastIndexOf(".")); //파일명에서 확장자만 추출
			storeName = UUID.randomUUID().toString().replace("-",""); //중복 이름을 막기 위해 새로운 이름 만들어줌
			storeFileName = storeName + extension;	//새로운 이름에 확장자 붙여 파일명 만들어주기
			//디비에 수정하는 dto
			dto.setGoodsMainStore(storeFileName);
			dto.setGoodsMainStoreImg(originalFile);
			// fileDir경로에 파일 저장
			file = new File(fileDir + "/" + storeFileName);
			try{
				mf.transferTo(file);	//새로운 메인 이미지파일 저장
			} catch(Exception e){
				e.printStackTrace();
			}
		}else{		// 새로운 메인 이미지가 없는 경우 오류메세지 전달(Not null)
			if (list != null) {
				for(FileCommand fileCommand : list){
					if(fileCommand.getStoreFile().equals(goodsDTO.getGoodsMainStore())){
						result.rejectValue("goodsMainStore", "error");
						model.addAttribute("error", "메인이미지를 선택해주세요");
						model.addAttribute("goodsCommand", goodsDTO);
						session.removeAttribute("fileList");
						return;
					}
				}

			}
		}
		//첨부이미지 수정, 반복문 사용
		List<String> goodsImages = new ArrayList<String>();
		List<String> goodsOrgImages = new ArrayList<String>();
		if(goodsDTO.getGoodsImages() != null){
			String[] images = goodsDTO.getGoodsImages().split("-");
			String[] orgImages = goodsDTO.getGoodsImagesImg().split("-");
			for(String img : images){
				goodsImages.add(img);  //리스트의 삭제가 용이하도록
			}
			for(String img : orgImages){
				goodsOrgImages.add(img);
			}
			// session에 있는 값을 list에서 삭제
			if(list != null){
				for (FileCommand fileCommand : list){
					for(String str : goodsImages) {
						if (fileCommand.getStoreFile().equals(str)) {
							goodsImages.remove(fileCommand.getStoreFile());
							goodsOrgImages.remove(fileCommand.getOrgFile());
							break;
						}
					}
				}
			}
		}
		// 첨부이미지 추가
		// 디비에 가져온 수정된 파일정보를 추가
		String originalTotal = "";
		String storeTotal = "";
		if(!goodsCommand.getGoodsImages()[0].getOriginalFilename().isEmpty()){
			for (MultipartFile mtf : goodsCommand.getGoodsImages()){
				originalFile = mtf.getOriginalFilename();  //원본파일명 가져오기
				extension = originalFile.substring(originalFile.lastIndexOf(".")); //파일명에서 확장자만 추출
				storeName = UUID.randomUUID().toString().replace("-",""); //중복 이름을 막기 위해 새로운 이름 만들어줌
				storeFileName = storeName + extension;	//새로운 이름에 확장자 붙여 파일명 만들어주기
				// fileDir경로에 파일 저장
				file = new File(fileDir + "/" + storeFileName);
				try{
					mtf.transferTo(file);	//새로운 메인 이미지파일 저장
				} catch(Exception e){
					e.printStackTrace();
				}
				// 여러개의 파일명을 하나로 묶기
				originalTotal += originalFile + "-";
				storeTotal += storeFileName + "-";
			}
		}
		//수정된 디비정보에 추가
		for(String img : goodsImages){
				storeTotal += img + "-";  //리스트의 삭제가 용이하도록
			}
			for(String img : goodsOrgImages){
				originalTotal += img + "-";
			}
			dto.setGoodsImages(storeTotal);
			dto.setGoodsImagesImg(originalTotal);

      	int i = goodsMapper.goodsUpdate(dto);
		  if(i > 0){ 	//DB에 내용이 수정되었다면 파일 삭제
			  if(list != null){
				  for(FileCommand fileCommand : list){
					  file = new File(fileDir + "/" + fileCommand.getStoreFile());
					  if(file.exists())
						  file.delete();
				  }
			  }
		  }
		// session 필요 없으므로 삭제
		session.removeAttribute("fileList");
    }
}
