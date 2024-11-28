package com.hackernews.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "hacker_news")
@Getter
@Setter
public class HackerNewsDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "by")
    private String by;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "text")
    private String text;

    @Column(name = "dead")
    private Integer dead;

    @Column(name = "parent")
    private Long parent;

    @Column(name = "poll")
    private Long poll;


    @Column(name = "kids")
    private String[] kids; // Tableau Java pour correspondre à TEXT[] dans PostgreSQL


    @Column(name = "url")
    private String url;

    @Column(name = "score")
    private Integer score;

    @Column(name = "title")
    private String title;

    @Column(name = "parts")
    private List<String> parts;


    @Column(name = "descendants")
    private Integer descendants;

}
