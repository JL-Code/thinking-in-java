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

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping()
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    /**
     * 自动抛出异常 + 全局异常处理
     *
     * @param user
     * @return
     */
    @PostMapping("autothrows-exception")
    public String addUserWithAutoThrowsException(@RequestBody @Valid User user) {
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
