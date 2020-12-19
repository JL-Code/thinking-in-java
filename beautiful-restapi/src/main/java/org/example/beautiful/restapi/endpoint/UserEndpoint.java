package org.example.beautiful.restapi.endpoint;

import org.example.beautiful.restapi.entity.User;
import org.example.beautiful.restapi.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    private UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setName("mecode");
        user.setEmail("778671998@qq.com");
        return user;
    }

    @PostMapping()
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @PostMapping("binding-result")
    public String addUserByBindingResult(@RequestBody @Valid User user, BindingResult bindingResult) {
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            return objectError.getDefaultMessage();
        }
        return userService.addUser(user);
    }


    @PostMapping("service-valid")
    public String addUserByServiceValid(@RequestBody User user) {
        return userService.addUserRequireValid(user);
    }
}
