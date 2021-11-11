package com.sai.api.dto;

import com.sai.model.User;
import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;
    private String contactEmail;
    private String phone;
    private String name;
    private Long roleId;

}
