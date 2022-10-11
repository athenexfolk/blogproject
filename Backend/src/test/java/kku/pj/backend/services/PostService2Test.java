package kku.pj.backend.services;

import kku.pj.backend.entities.PostEntity;
import kku.pj.backend.repositories.PostRepository2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostService2Test {
    PostEntity post;
    IPostService postService;

    @BeforeAll
    private void config(){
        post = new PostEntity();
        post.setTitle("Test");
        post.setImgId(1);
        post.setUsername("Villium");
        post.setContent("test");
        post.setShortContent("c");
    }

    @Test
    void add() {

    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }

    @Test
    void gets() {
    }
}