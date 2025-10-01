package kr.co.sboard.repository;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.sboard.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

//    @Test
//    void test1() {
//        Pageable pageable = PageRequest.of(0, 10); // 0이 1페이지, 1가 2페이지...
//
//        Page<Tuple> pageTuple = articleRepository.selectArticleAllForList(pageable);
//
//        List<Tuple> tupleList =  pageTuple.getContent();
//
//        System.out.println(tupleList);
//    }

    @Test
    @Transactional // no session error일 때 주입
    void test2() {
        Optional<Article> optArticle = articleRepository.findById(3);
        if(optArticle.isPresent()) {
            Article article = optArticle.get();
            System.out.println(article);
        }
    }



}