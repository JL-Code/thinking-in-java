package org.example.elk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>创建时间: 2021/8/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("elk/hello")
    public String getHello() {
        log.info("你好" + new Date());
        return "你好" + new Date();
    }

    @PostMapping("/elk/logging")
    public void sendToElk() {
        log.info("测试消息");
        log.error("测试消息" + new Date());
    }
}
