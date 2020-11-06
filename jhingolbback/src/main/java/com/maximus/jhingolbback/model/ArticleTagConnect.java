package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTagConnect implements Serializable {
    private String id;

    private String articleId;

    private Integer tagId;

    private String tagName;

}