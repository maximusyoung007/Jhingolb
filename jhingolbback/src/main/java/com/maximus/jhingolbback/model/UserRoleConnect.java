package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleConnect implements Serializable {
    private String id;

    private String username;

    private String role;

    private String userId;

    private String roleId;

    private static final long serialVersionUID = 1L;

}