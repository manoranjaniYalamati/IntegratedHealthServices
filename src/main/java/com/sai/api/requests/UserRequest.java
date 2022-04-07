package com.sai.api.requests;

import com.sai.model.Gender;
import lombok.Data;

@Data
public class UserRequest {

    private String username;

    private String password;

    private String email;

    private String phone;//ledhu

    private String name;//ledhu

    private Gender gender;

    private String contactEmail;//ledu

    private String speciality;//ledu

    private String meetLink;

    private String roleId;

    private String experience;
}
