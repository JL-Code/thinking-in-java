package org.example.beautiful.restapi.service;

import org.example.beautiful.restapi.entity.User;
import org.example.beautiful.restapi.exception.UserNotEnabledException;
import org.example.beautiful.restapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
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

    public User getUser(Integer id) {
        // 显式指定 http status ，需要开发人员对 http status code 有一定的背景知识。
        // 是否可以将  http status 进一步封装 ？
        throw new UserNotEnabledException(String.format("用户 [%d] 未被启用", id), HttpStatus.BAD_REQUEST);
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
