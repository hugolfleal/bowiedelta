package org.academiadecodigo.hackathon.controllers.rest;

import org.academiadecodigo.hackathon.command.UserDto;
import org.academiadecodigo.hackathon.converters.UserDtoToUser;
import org.academiadecodigo.hackathon.converters.UserToUserDto;
import org.academiadecodigo.hackathon.persistence.model.User;
import org.academiadecodigo.hackathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
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
    public ResponseEntity<List<UserDto>> listUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userService.list()) {
            userDtos.add(userToUserDto.convert(user));
        }
        return new ResponseEntity(userDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserDto> getUserId(@PathVariable Integer id) {
        try {
            User user = userService.get(id);
            UserDto userDto = userToUserDto.convert(user);
            return new ResponseEntity(userDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/googleId/{googleId}")
    public ResponseEntity<UserDto> getUserGoogleId(@PathVariable String googleId) {
        try {
            String registeredGoogleId = userService.getByGoogleId(googleId);
            if (registeredGoogleId.equals(googleId)){
                return new ResponseEntity("true", HttpStatus.OK);
            }
            return new ResponseEntity("false", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("false", HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userDto.setId(null);
        User user = userDtoToUser.convert(userDto);
        User persistedUser = userService.save(user);
        userDto = userToUserDto.convert(persistedUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}")
    public ResponseEntity<UserDto> editUser(@Valid @RequestBody UserDto userDto,
                                                    BindingResult bindingResult,
                                                    @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userService.get(id);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());

            user = userService.save(user);
            UserDto userConvertedToDto = userToUserDto.convert(user);
            return new ResponseEntity<>(userConvertedToDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
