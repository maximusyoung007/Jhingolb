package com.maximus.jhingolbback.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuthUser implements Serializable {
    private Integer id;

    private String password;

    private Date lastLogin;

    private Boolean isSuperuser;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isStaff;

    private Boolean isActive;

    private Date dateJoined;

}