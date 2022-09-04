package dz.tchakal.jwt.controller;

import dz.tchakal.jwt.dto.UserDto;
import dz.tchakal.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static dz.tchakal.jwt.Util.SecurityConstants.SIGN_UP_URL;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = SIGN_UP_URL+"/create",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto save(@RequestBody UserDto userDto) {
        return userService.saveUserDto(userDto);
    }
    @GetMapping(value = SIGN_UP_URL+"/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll() {
        return userService.findAll();
    }

}
