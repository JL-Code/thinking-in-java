package org.example.basic.threadpool;


import lombok.extern.slf4j.Slf4j;
//import org.example.basic.threadpool.custom.DefaultThreadPool;
//import org.example.basic.threadpool.custom.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SimpleHttpServer {
    // 处理 HttpRequest 的线程池
//    static final ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(1);
    static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    // SimpleHttpServer 根路径
    static String basePath;
    static int port = 8080;
    static ServerSocket serverSocket;
    static String ServerName = "SimpleHttpServer";

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void start() {
        try {
            // 默认 backlog = 50
            serverSocket = new ServerSocket(port, 1024);
            Socket socket;
            // accept() 是单线程的，如果 100个连接同时到达，accept 来不及处理，TCP 队列就满了，后面的连接就被拒绝或重置（Connection reset by peer）
            // ServerSocket 内部底层依赖 OS 的 TCP backlog 队列（就是“未完成三次握手”的排队队列）。 默认系统一般 backlog 大小是 128，或者更小。
            // 如果爆了，连接直接拒绝，报错：apr_socket_recv: Connection reset by peer
            // 总结：accept 太慢 + backlog 太小 + 线程池太小 = 高并发断开
            while ((socket = serverSocket.accept()) != null) {
                // 接收一个客户端 Socket，生成一个 HttpRequestHandler ，放入线程池执行
//            threadPool.execute(new HttpRequestHandler(socket));
                executorService.submit(new HttpRequestHandler(socket));
            }
            serverSocket.close();
        } catch (IOException e) {
            log.error("server fail: {}", e.getMessage(), e);
        }
    }


    static class HttpRequestHandler implements Runnable {
        private final Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String requestLine = reader.readLine();
                if (requestLine == null || requestLine.isEmpty()) {
                    return;
                }

                log.info("{}: 处理请求 - 开始", Thread.currentThread().getName());
                String[] parts = requestLine.split(" ");
                if (parts.length < 2) {
                    // 非法请求
                    log.warn("非法请求");
                    return;
                }
                String path = basePath + requestLine.split(" ")[1];
                File file = new File(path);
                if (!file.exists() || !file.isFile()) {
                    // 404
                    String resp = """
                            HTTP/1.1 404 Not Found\r
                            Content-Type: text/plain; charset=UTF-8\r
                            Content-Length: 13\r
                            Connection: close\r
                            \r
                            File Not Found""";
                    outputStream.write(resp.getBytes(StandardCharsets.UTF_8));
                    return;
                }

                // 返回 HTTP 200 Header
                String contentType = path.endsWith(".jpg") || path.endsWith(".png") ? "image/jpeg" : "text/html; charset=UTF-8";
                String header = "HTTP/1.1 200 OK\r\n" +
                        "Server: " + ServerName + "\r\n" +
                        "Content-Length: " + file.length() + "\r\n" +
                        "Content-Type: " + contentType + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n";
                outputStream.write(header.getBytes(StandardCharsets.UTF_8));

                // 返回 Body 内容（用缓冲流）
                try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file))) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = fileInput.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

                log.info("{}: 处理请求 - 完成", Thread.currentThread().getName());

            } catch (Exception e) {
                log.error("{}: 处理请求发生错误: {}", Thread.currentThread().getName(), e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("Socket关闭失败: {}", e.getMessage());
                }
            }
        }
    }
}
