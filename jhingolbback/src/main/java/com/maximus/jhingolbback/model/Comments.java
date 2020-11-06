package com.maximus.jhingolbback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Comments implements Serializable {
    private String id;

    private String fatherId;

    private String username;

    private String email;

    private String articleId;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String content;

    private Integer commentCounts;

    private String avatar;

    private Integer likes;

    private Integer dislikes;

    private String replayTo;

    private List<Comments> childrenComments;

    private static final long serialVersionUID = 1L;
}