package org.academiadecodigo.hackathon.controllers.rest;

import org.academiadecodigo.hackathon.command.UserDto;
import org.academiadecodigo.hackathon.converters.UserDtoToUser;
import org.academiadecodigo.hackathon.converters.UserToUserDto;
import org.academiadecodigo.hackathon.persistence.model.User;
import org.academiadecodigo.hackathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private UserDtoToUser userDtoToUser;
    private UserToUserDto userToUserDto;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }
    
    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public List<UserDto> listUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userService.list()) {
            userDtos.add(userToUserDto.convert(user));
        }
        return userDtos;
    }
}
