package kr.co.sboard.repository.custom;

import com.querydsl.core.Tuple; // 라이브러리 확인
import kr.co.sboard.dto.PageRequestDTO;
import org.springframework.data.domain.Page; // 라이브러리 확인
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    public Page<Tuple> selectArticleAllForList(PageRequestDTO pageRequsetDTO, Pageable pageable);

    // 검색용 쿼리 메서드
    public Page<Tuple> selectArticleAllForSearch(PageRequestDTO pageRequsetDTO, Pageable pageable);
}
