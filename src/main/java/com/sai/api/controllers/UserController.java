package com.sai.api.controllers;

import com.sai.api.dto.AuthToken;
import com.sai.api.dto.LoginUser;
import com.sai.api.dto.UserDto;
import com.sai.api.requests.UserRequest;
import com.sai.config.TokenProvider;
import com.sai.model.User;
import com.sai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.io.FileWriter;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthToken generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        AuthToken authToken = new AuthToken(token);
        try {
            FileWriter Writer
                    = new FileWriter("tokenfile.txt");
            Writer.write(token);
            Writer.close();
            System.out.println("file created");
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        return authToken;// i think this should work
    }

//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    public User saveUser(@RequestBody User user){
//        return userService.save(user);
//    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserRequest user){
        UserDto userDto= userService.save(user);
        System.out.println(userDto);
        return userDto;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @RequestMapping(value="/isDoctor", method = RequestMethod.GET)
    public Boolean isDoctor(){

        System.out.println("executing");
        return true;
    }

}
