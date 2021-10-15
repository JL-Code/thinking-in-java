package org.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>创建时间: 2021/10/14 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class DownloadController {

    @GetMapping("/download/html")
    public void methodName(HttpServletResponse response) throws IOException {

        String htmlStr = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>test</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  你好我是test\n" +
                "</body>\n" +
                "</html>";

        byte[] buff = new byte[htmlStr.length()];
        //从字符串获取字节写入流
        InputStream inputStream = new ByteArrayInputStream(htmlStr.getBytes("utf-8"));

        inputStream.read(buff);

        response.setContentType("text/html");
        //设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=test.html");
        response.addHeader("Content-Length", "" + htmlStr.length());

        OutputStream stream = response.getOutputStream();
        stream.write(buff);
        stream.flush();
        stream.close();
    }

}
