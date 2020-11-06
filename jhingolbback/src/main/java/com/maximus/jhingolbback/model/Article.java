package com.maximus.jhingolbback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Article extends Page implements Serializable {
    private String id;

    private String title;

    private String articleBody;

    private String img;

    private Integer views;

    private String excerpt;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;

    private Integer categoryId;

    private Integer tuiId;

    private Integer userId;

    private Integer releaseState;

    private List<String> allTags;

    private String category;

    private List<Tags> tags;

    private String tagId;

    private String archive;

    private List<String> ids;

    private Date firstDay;

    private Date lastDay;

    private Integer thumbsUp;

    private Integer oppose;

    private Integer commentCounts;

    private static final long serialVersionUID = 1L;

}