package kr.co.sboard.service;

import kr.co.sboard.dto.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void getCommentAll() {
        List<CommentDTO> dtoList = commentService.getCommentAll(1);
        System.out.println(dtoList);
    }

    @Test
    void getComment() {
    }

    @Test
    void sava() {

        CommentDTO commentDTO = CommentDTO.builder()
                .ano(4)
                .content("2번 댓글")
                .writer("qwe123")
                .reg_ip("127.0.0.1")
                .build();

        CommentDTO savedCommentDTO = commentService.sava(commentDTO);
        System.out.println(savedCommentDTO);

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}