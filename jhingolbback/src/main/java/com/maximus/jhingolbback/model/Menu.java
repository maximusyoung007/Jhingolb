package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {
    private String id;

    private String path;

    private String name;

    private String nameZh;

    private String iconCls;

    private String component;

    private String parentId;

    private List<Menu> children;

    private static final long serialVersionUID = 1L;


}