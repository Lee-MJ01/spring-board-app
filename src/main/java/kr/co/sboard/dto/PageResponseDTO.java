package kr.co.sboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor // 생성자를 따로 만들어서 noargs는 없어야함
public class PageResponseDTO {

    private List<ArticleDTO> dtoList;

    private String cate;
    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequsetDTO, List<ArticleDTO> dtoList, int total) {

        this.cate = pageRequsetDTO.getCate();
        this.pg = pageRequsetDTO.getPg();
        this.size = pageRequsetDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.startNo = total - ((pg - 1) * size);
        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) (Math.ceil(total / (double) size));
        this.end = Math.min(end, last);

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }
}
