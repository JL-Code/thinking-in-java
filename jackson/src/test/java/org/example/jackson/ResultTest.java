package org.example.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jackson 框架的高阶应用 https://developer.ibm.com/zh/articles/jackson-advanced-application/
 */
class ResultTest {
    @Test
    public void testJackson() throws JsonProcessingException {
        Result result = new Result();
        result.setCode(201);
        result.setMessage("创建成功");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(result);

        System.out.println(json);

//        String pattern = "errors";

    }

    /**
     * 1.序列化时自定义字段名称
     * 2.序列化时忽略指定字段
     * 3.泛型反序列化
     * 4.泛型序列化
     * 5.日期格式处理
     * 自定义序列化器
     *  Jackson 注解 @JsonProperty @JsonIgnore @JsonIgnoreProperties
     *
     */

    @Test
    public void testJacksonCustomSerializer() throws JsonProcessingException {

        Result result = new Result();

        result.setCode(201);
        result.setMessage("创建成功");
        result.setErrors("创建失败");

        ResultSerializer serializer = new ResultSerializer(Result.class);
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module =
                new SimpleModule("ResultSerializer", new Version(2, 1, 3, null, null, null));
        module.addSerializer(Result.class, serializer);

        mapper.registerModule(module);


        String json = mapper.writeValueAsString(result);

//        Pattern pattern = Pattern.compile("errors");
//        Matcher matcher = pattern.matcher(json);

//        System.out.println(matcher.find());
        System.out.println(json);

//        Assertions.assertTrue(matcher.find());
    }


    @Test
    public void testJacksonDate() throws JsonProcessingException {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("成功");
        result.setCreated(new Date());

        // 默认情况下，Jackson会将 java.util.Date 对象序列化为其 long 型的值，该值是自1970年1月1日以来的毫秒数
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);

        System.out.println(json);


        // java.util.Date 序列化为字符串
        // 1. 通过 @JsonFormat 2. 调用 ObjectMapper.setDateFormat

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objectMapper.setDateFormat(dateFormat);

        String output2 = objectMapper.writeValueAsString(result);
        System.out.println(output2);


    }


}