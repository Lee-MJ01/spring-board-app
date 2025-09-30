package kr.co.sboard.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.entity.QArticle;
import kr.co.sboard.entity.QUser;
import kr.co.sboard.repository.custom.ArticleRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom { // 이름 형식 중요 ~RepositoryImpl이 아니면 에러남, QueryDSL 생성 에러 발생!!!!!!!!!

    private final JPAQueryFactory query;

    private QArticle article = QArticle.article;
    private QUser user = QUser.user;

    @Override
    public Page<Tuple> selectArticleAllForList(PageRequestDTO pageRequsetDTO, Pageable pageable) {

        List<Tuple> tupleList = query.select(article, user.nick)
                                        .from(article)
                                        .join(user)
                                        .on(article.writer.eq(user.usid))
                                        .offset(pageable.getOffset())
                                        .limit(pageable.getPageSize())
                                        .orderBy(article.ano.desc())
                                        .fetch();

        // 전체 게시물 개수
        long total = query.select(article.count()).from(article).fetchOne();


        return new PageImpl<Tuple>(tupleList, pageable, total);
    }

    @Override
    public Page<Tuple> selectArticleAllForSearch(PageRequestDTO pageRequsetDTO, Pageable pageable) {

        String searhType = pageRequsetDTO.getSearchType();
        String keyword = pageRequsetDTO.getKeyword();

        // 검색 타입에 따라 where 조건 표현식 생성(동적 쿼리)
        BooleanExpression expression = null;

        if(searhType.equals("title")) {
            expression = article.title.contains(keyword); // == title like %keyword%
        }else if(searhType.equals("content")) {
            expression = article.content.contains(keyword);
        }else if(searhType.equals("writer")) {
            expression = article.writer.contains(keyword);
        }

        List<Tuple> tupleList = query.select(article, user.nick)
                .from(article)
                .join(user)
                .on(article.writer.eq(user.usid))
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(article.ano.desc())
                .fetch();

        // 전체 게시물 개수
        long total = query
                .select(article.count())
                .from(article)
                .where(expression)
                .fetchOne();


        return new PageImpl<Tuple>(tupleList, pageable, total);

    }
}
