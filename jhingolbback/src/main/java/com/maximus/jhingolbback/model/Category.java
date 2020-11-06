package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private String id;

    private String name;

    private Integer index;

    private Integer useCounts;

    private static final long serialVersionUID = 1L;

}