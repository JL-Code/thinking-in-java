package org.example.beautiful.restapi.endpoint;

import org.example.beautiful.restapi.entity.User;
import org.example.beautiful.restapi.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserEndpoint {

    private UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @PostMapping("binding/result")
    public String addUser2(@RequestBody @Valid User user, BindingResult bindingResult) {
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            return objectError.getDefaultMessage();
        }
        return userService.addUser(user);
    }
}
