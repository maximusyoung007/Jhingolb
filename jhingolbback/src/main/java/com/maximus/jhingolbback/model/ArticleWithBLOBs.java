package com.maximus.jhingolbback.model;

import java.io.Serializable;

public class ArticleWithBLOBs extends Article implements Serializable {
    private String excerpt;

    private String body;

    private static final long serialVersionUID = 1L;

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt == null ? null : excerpt.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", excerpt=").append(excerpt);
        sb.append(", body=").append(body);
        sb.append("]");
        return sb.toString();
    }
}