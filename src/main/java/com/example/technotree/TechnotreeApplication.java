package com.example.technotree;

import com.example.technotree.domain.Comment;
import com.example.technotree.domain.Post;
import com.example.technotree.domain.Todo;
import com.example.technotree.service.CommentService;
import com.example.technotree.service.PostService;
import com.example.technotree.service.TodoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@RestController
@Log4j2
public class TechnotreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnotreeApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PostService postService) {
        return args -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Post>> typeReference = new TypeReference<List<Post>>() {};
                InputStream inputStream = TypeReference.class.getResourceAsStream("/json/posts.json");
                try {
                    List<Post> posts = mapper.readValue(inputStream, typeReference);
                    postService.saveAllPosts(posts);
                    log.info("posts saved in database");
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn("posts not saved in database");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("posts not saved in database");
            }
        };
    }

    @Bean
    CommandLineRunner runner(CommentService commentService) {
        return args -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Comment>> typeReference = new TypeReference<List<Comment>>() {};
                InputStream inputStream = TypeReference.class.getResourceAsStream("/json/comments.json");
                try {
                    List<Comment> comments = mapper.readValue(inputStream, typeReference);
                    commentService.saveAllComments(comments);
                    log.info("comments saved in database");
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn("comments not saved in database");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("comments not saved in database");
            }
        };
    }

    @Bean
    CommandLineRunner runner(TodoService todoService) {
        return args -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Todo>> typeReference = new TypeReference<List<Todo>>() {};
                InputStream inputStream = TypeReference.class.getResourceAsStream("/json/todos.json");
                try {
                    List<Todo> todos = mapper.readValue(inputStream, typeReference);
                    todoService.saveAllTodos(todos);
                    log.info("todos saved in database");
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn("todos not saved in database");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("todos not saved in database");
            }
        };
    }
}
