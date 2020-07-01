package com.imedzenned.redditclone.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

public class Subreddit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank(message = "community name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @OneToMany(fetch = LAZY)
    private List<Post> postes;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    private User user;

}
