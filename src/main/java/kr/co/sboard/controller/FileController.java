package kr.co.sboard.controller;

import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @ResponseBody // 이거 안하면 json 형식으로 들어가짐. 지금은 파일형식으로 들어가야하기에 해당 어노테이션 해줌
    @GetMapping("/file/download")
    public ResponseEntity<Resource> download(int fno){
        log.info("fno = {}", fno);

        FileDTO fileDTO = fileService.getFile(fno);

        // 파일 다운로드 카운트 업데이트
        fileService.modifyDownloadCount(fileDTO);

        fileService.download(fileDTO); // 얕은 복사로 contentType과 resource 초기화
        log.info("fileDTO = {}", fileDTO);

        // 파일 다운로드 헤더 정보 설정 스프링프레임워크
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename(fileDTO.getOname(), StandardCharsets.UTF_8)
                        .build()
        );
        headers.add(HttpHeaders.CONTENT_TYPE, fileDTO.getContentType());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileDTO.getResource());
    }

}
