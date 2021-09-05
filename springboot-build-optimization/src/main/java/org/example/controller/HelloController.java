package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>创建时间: 2021/9/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping()
    public Object hello() {
        return "你好，我来自 build optimization, 时间：" + LocalDateTime.now();
    }
}
