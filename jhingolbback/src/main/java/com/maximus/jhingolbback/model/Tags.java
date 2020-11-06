package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tags implements Serializable {
    private Integer id;

    private String name;

    private Integer useCounts;
}