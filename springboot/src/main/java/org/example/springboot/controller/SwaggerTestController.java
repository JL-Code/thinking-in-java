package org.example.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/5/23 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class SwaggerTestController {

    @ApiOperation("用户列表")
    @GetMapping("/{id}")
    public Object getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return null;
    }
}