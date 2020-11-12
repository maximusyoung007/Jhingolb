package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuRoleConnect implements Serializable {
    private String id;

    private String rid;

    private String mid;

    private String roleName;

    private String menuName;

}