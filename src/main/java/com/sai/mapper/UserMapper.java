package com.sai.mapper;

import com.sai.api.dto.UserDto;
import com.sai.api.requests.UserRequest;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRequest userRequest);

    UserDto map(User user);

}
