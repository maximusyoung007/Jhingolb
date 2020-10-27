package com.maximus.jhingolbback.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;

    private String name;

    private Integer index;

    private Integer useCounts;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
        sb.append(", index=").append(index);
        sb.append(", useCounts=").append(useCounts);
        sb.append("]");
        return sb.toString();
    }
}