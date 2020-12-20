package org.example.beautiful.restapi.endpoint;

import org.example.beautiful.restapi.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionEndpoint {


    @GetMapping("npe")
    public String getNpeException() {
        User user = null;
        return user.getName();
    }

}
