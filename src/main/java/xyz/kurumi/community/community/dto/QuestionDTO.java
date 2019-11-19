package xyz.kurumi.community.community.dto;

import lombok.Data;
import xyz.kurumi.community.community.model.User;
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private String tag;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private User user;
}
