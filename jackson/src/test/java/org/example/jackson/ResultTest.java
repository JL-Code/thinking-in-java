package org.example.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     *
     * 自定义序列化器
     * Jackson 注解 @JsonProperty @JsonIgnore @JsonIgnoreProperties
     */

    @Test
    public void testJacksonCustomSerializer() throws JsonProcessingException {

        Result result = new Result();

        result.setCode(201);
        result.setMessage("创建成功");
        result.setErrors("创建失败");

        ResultSerializer serializer = new ResultSerializer(Result.class);
        ObjectMapper mapper = new ObjectMapper();

//        SimpleModule module =
//                new SimpleModule("ResultSerializer", new Version(2, 1, 3, null, null, null));

        SimpleModule module = new SimpleModule("ResultSerializer");

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

    @Test
    public void testGenericSerialize() throws JsonProcessingException {
        List<Result> resultList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Result result = new Result();
            result.setCode(100 + i);
            result.setMessage(String.format("result %d", i));
            result.setErrors(String.format("errors %d", i));
            resultList.add(result);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultList);

        System.out.println(json);

    }

    @Test
    public void testGenericDserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType javaType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Result.class);

        String filePath = "/json/result-list.json";
        InputStream stream1 = this.getClass().getResourceAsStream(filePath);
        InputStream stream2 = this.getClass().getResourceAsStream(filePath);

        List<Result> list1 = mapper.readValue(stream1, javaType);

        List<Result> list2 = mapper.readValue(stream2, new TypeReference<List<Result>>() {
        });

        System.out.println(list1);
        System.out.println(list2);
    }

}