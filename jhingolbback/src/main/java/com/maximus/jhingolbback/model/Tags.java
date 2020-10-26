package com.maximus.jhingolbback.model;

import java.io.Serializable;

public class Tags implements Serializable {
    private Integer id;

    private String name;

    private Integer useCounts;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUseCounts() {
        return useCounts;
    }

    public void setUseCounts(Integer useCounts) {
        this.useCounts = useCounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}