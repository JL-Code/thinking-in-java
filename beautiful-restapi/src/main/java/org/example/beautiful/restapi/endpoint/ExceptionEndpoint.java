package org.example.beautiful.restapi.endpoint;

import org.example.beautiful.restapi.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/exception")
public class ExceptionEndpoint {


    @GetMapping("npe")
    public String getNpeException() {
        User user = null;
        return user.getName();
    }

    @GetMapping("/{id}")
    public Object methodName(@PathVariable String id) {
//        try {
//            Foo resourceById = RestPreconditions.checkFound(service.findOne(id));
//
//            eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
//            return resourceById;
//        }
//        catch (MyResourceNotFoundException exc) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);
//        }
        return null;
    }
}
