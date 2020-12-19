package org.example.beautiful.restapi.service;

import org.example.beautiful.restapi.entity.User;
import org.example.beautiful.restapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(User user) {
        userRepository.insert(user);
        return "success";
    }


    public String addUserRequireValid(User user) {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user);

        for (ConstraintViolation<User> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }

        userRepository.insert(user);

        return "success";
    }
}
