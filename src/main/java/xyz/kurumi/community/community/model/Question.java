package xyz.kurumi.community.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private String creator;
    private String tag;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
}
