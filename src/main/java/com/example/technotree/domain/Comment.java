package com.example.technotree.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
