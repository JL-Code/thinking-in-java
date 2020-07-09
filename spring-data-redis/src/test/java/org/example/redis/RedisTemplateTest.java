package org.example.redis;

import com.alibaba.fastjson.JSON;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.codec.ByteArrayCodec;
import org.example.redis.model.Station;
import org.example.redis.model.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */

public class RedisTemplateTest {

    private String host = "127.0.0.1";
    private int database = 2;
//    RedisConnectionFactory redisConnectionFactory;
//    RedisConnection conn;

    @BeforeEach
    public void init() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setDatabase(database);
//        configuration.setHostName(host);
//        redisConnectionFactory = new LettuceConnectionFactory(configuration);
//
//        conn = redisConnectionFactory.getConnection();
    }


    @Test
    public void testRedisTemplate() {
        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<byte[], byte[]> connection = client.connect(new ByteArrayCodec());
        RedisStringCommands sync = connection.sync();

        UserInfo userInfo = new UserInfo();
        userInfo.setId("12345678");
        userInfo.setCompanyId("12345678");
        userInfo.setName("蒋勇");
        userInfo.setUserCode("jiangy");
        userInfo.setDefaultStation("12");
        Station station = new Station("12", "经理", "13", "销售部", "14", "衡泽");
        userInfo.setStations(Arrays.asList(station));
        byte[] userBytes = JSON.toJSONBytes(userInfo);
        byte[] userKey = serializeKey("user:" + userInfo.getId());
        sync.set(userKey, userBytes);

        Object result = sync.get(userKey);
        UserInfo userInfo1 = JSON.parseObject((byte[]) result, UserInfo.class);

        connection.close();

        Assertions.assertEquals(userInfo.getId(), userInfo1.getId());
    }

    private final byte[] serializeKey(String object) {
        return serialize(object);
    }

    private byte[] serialize(String string) {
        return JSON.toJSONBytes(string);
    }

    @AfterEach
    public void destory() {

    }
}
