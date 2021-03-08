package org.example.mybatis.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/3/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void testPagination() {
        String agentId = "013f00be-923d-4a73-8896-43ac0b87d618";
        Page<User> pagination = new Page<>(1, 2);
        IPage<User> records = repository.selectPage(pagination, agentId);

        assertNotEquals(0, records.getSize());
        assertEquals(records.getRecords().get(0).getName(), "mecode1");
        assertEquals(records.getRecords().get(1).getName(), "mecode2");
    }
}