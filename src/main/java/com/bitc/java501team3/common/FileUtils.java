package com.bitc.java501team3.common;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.FreeBoardFileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// @Bean, @Component은 스프링 프레임워크에서 객체를 생성하여 사용하고 관리하는 라이브러리를 뜻하는 어노테이션
// @Bean : 스프링 프레임워크 혹은 각종 서드파티 회사에서 제공하는 라이브러리를 사용 시 사용하는 어노테이션
// @Component : 사용자가 직접 생성하고 사용하기 위해서 스프링 프레임워크에 관리를 맡기기 위해서 사용하는 어노테이션
@Component
public class FileUtils {
    @Value("${file.upload-dir}") // application.properties 또는 application.yml에서 설정된 경로를 주입
    private String uploadPath;

    public List<FreeBoardFileDTO> parseFileInfo(int boardIdx, String boardUserId, MultipartHttpServletRequest multipart) throws Exception{
        if(ObjectUtils.isEmpty(multipart)){
            return null;
        }

        //ObjectUtils 안비었으면 실행

        //파일정보 리스트 객체 생성
        List<FreeBoardFileDTO> fileList = new ArrayList<>();

        //사용자가 지정한 형식으로 날짜 데이터 출력 형식을 설정
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 현재 위치를 기준으로 현재 시간을 가져옴 date()로도 가능
        ZonedDateTime current = ZonedDateTime.now();

        // 저장할 파일 경로 설정
        //current 현재 시간 기준인데 format은 위에서 설정한 format
        //ex > C:/fullstack501/images/2023-12-06
//        String path = "C:/fullstack/images/" + current.format(format);
        String path = uploadPath + current.format(format);

        // File 클래스를 통해서 파일 객체 생성
        File file = new File(path);

        // 지정한 경로가 존재하는지 확인
        if(file.exists() == false){
            //지정한 경로가 없으면 false
            file.mkdirs();
            //없을 경우 해당 경로 생성
        }

        // getFileNames() : 업로드된 파일 정보에서 전체 파일 이름 목록 가져오기
        Iterator<String> iterator = multipart.getFileNames();

        String newFileName; // 파일 이름을 저장할 변수
        String originalFileExtension; // 확장자를 저장할 변수(원본)
        String contentType; // 확장자를 저장할 변수(타겟)

        while(iterator.hasNext()){
            //파일 이름을 기준으로 파일 정보를 가져옴
            List<MultipartFile> fileLists = multipart.getFiles(iterator.next());

            for(MultipartFile uploadFile : fileLists){
                if(uploadFile.isEmpty() == false){
                    contentType = uploadFile.getContentType(); //파일 타입 가져오기

                    if(ObjectUtils.isEmpty(contentType)) {
                        break;
                    }
                    else {
                        //파일의 타입에 따라 확장자명 입력하기
                        if(contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        }
                        else if(contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        }
                        else if(contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        }
                        else {
                            break;
                        }
                    }

                    //파일 이름 생성하기
                    newFileName = System.nanoTime() + originalFileExtension;

                    //BoardFileDto타입의 객체 생성
                    FreeBoardFileDTO boardFile = new FreeBoardFileDTO();
                    boardFile.setFreeboardIdx(boardIdx); // 매개변수로 받아온 게시물 번호 저장
                    boardFile.setFreeboardFilesize(uploadFile.getSize());
                    boardFile.setFreeboardOrgFileName(uploadFile.getOriginalFilename()); // 원본 파일명
                    //위에서 생성한 파일 저장 경로 + 현재 시간을 기준으로 새로 만든 파일명으로 서버에 저장한 파일명 설정
                    boardFile.setFreeboardStoredFileName(path  + "/" + newFileName);
                    boardFile.setFreeboardUserId(boardUserId);

                    //만들어진 파일 정보를 위에서 생성한 List<BoardFileDto> 타입의 객체에 데이터 추가
                    fileList.add(boardFile);

                    // 서버에 저장하기 위해서 파일 객체 다시 생성
                    file = new File(path + "/" + newFileName);
                    // 업로드 된 파일 정보(메모리상에 존재)를 실제 서버의 지정한 폴더로 복사(물리적 서버의 폴더에 저장) 실제 파일을 메모리에서 디스크로 복사
                    uploadFile.transferTo(file);
                }
            }
        }
        // 파일 정보 목록을 반환
        return fileList;
    }
}
