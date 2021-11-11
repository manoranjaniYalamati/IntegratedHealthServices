package com.sai.api.requests;

import com.sai.model.Gender;
import lombok.Data;

@Data
public class UserRequest {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String name;

    private Gender gender;

    private String contactEmail;

    private String speciality;

    private String meetLink;

    private Long roleId;
}
