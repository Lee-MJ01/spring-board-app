package kr.co.sboard.dto;

import lombok.*;
import org.springframework.core.io.Resource;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {

    private int fno;
    private int ano;
    private String oname;
    private String sname;
    private int download;
    private String rdate;

    // 추가 필드
    private Resource resource;
    private String contentType;

}
