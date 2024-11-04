package com.hackernews.backend.DO;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "hacker_news")
public class HackerNewsDO {
    public Long getId() {
        return id;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public String getType() {
        return type;
    }

    public String getBy() {
        return by;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public Integer getDead() {
        return dead;
    }

    public Long getParent() {
        return parent;
    }

    public Long getPoll() {
        return poll;
    }

    public List<String> getKids() {
        return kids;
    }

    public String getUrl() {
        return url;
    }

    public Integer getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getParts() {
        return parts;
    }

    public Integer getDescendants() {
        return descendants;
    }

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
    @ElementCollection
    private List<String> kids;

    @Column(name = "url")
    private String url;

    @Column(name = "score")
    private Integer score;

    @Column(name = "title")
    private String title;

    @Column(name = "parts")
    @ElementCollection
    private List<String> parts;

    @Column(name = "descendants")
    private Integer descendants;

}
