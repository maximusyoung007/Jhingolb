package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTagConnect implements Serializable {
    private String id;

    private String articleId;

    private String tagId;

    private String tagName;

}