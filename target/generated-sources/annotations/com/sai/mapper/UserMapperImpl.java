package com.sai.mapper;

import com.sai.api.dto.UserDto;
import com.sai.api.requests.UserRequest;
import com.sai.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-07T14:54:14+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userRequest.getUsername() );
        user.setPassword( userRequest.getPassword() );
        user.setContactEmail( userRequest.getContactEmail() );
        user.setPhone( userRequest.getPhone() );
        user.setName( userRequest.getName() );
        user.setGender( userRequest.getGender() );
        user.setRoleId( userRequest.getRoleId() );

        return user;
    }

    @Override
    public UserDto map(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setContactEmail( user.getContactEmail() );
        userDto.setPhone( user.getPhone() );
        userDto.setName( user.getName() );
        userDto.setRoleId( user.getRoleId() );

        return userDto;
    }
}
