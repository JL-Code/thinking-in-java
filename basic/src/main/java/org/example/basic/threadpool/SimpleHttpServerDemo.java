package org.example.basic.threadpool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHttpServerDemo {
    public static void main(String[] args) {
        SimpleHttpServer.setBasePath("/Users/codeme/playground/thinking-in-java/basic/src/main/resources/web");
        SimpleHttpServer.setPort(8080);
        try {
            log.info("服务器启动成功，port: 8080");
            SimpleHttpServer.start();
        } catch (Exception e) {
            log.error("SimpleHttpServerDemo fail: {}", e.getMessage());
        }
    }
}
