# MyBatis

## 分页插件 PaginationInterceptor

* 1. 注册 PaginationInterceptor Bean
* 2. 在数据层编写分页方法接收分页参数 Page<?> 并返回一个 IPage<?> 类型。
* 3. 单元测试 

```java
@MapperScan("org.example.mybatis.repository")
@Configuration
public class MybatisPlusConfig {

    /*
     * 分页插件，自动识别数据库类型 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
``` 

分页方法：

```java
@Repository
public interface UserRepository extends BaseMapper<User> {
    @Select("select t1.nick_name,t1.id,t1.name,t2.agent_id,t2.create_time from cloud_user t1 right join cloud_invitation_user_agent t2 on" +
            " t1.id = t2.user_id where t2.agent_id=#{agentId}")
    IPage<User> selectPage(Page<User> pagination, String agentId);
}
```

测试方法：

```java
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository repository;
    @Test
    public void testPagination() {
        String agentId = "013f00be-923d-4a73-8896-43ac0b87d618";
        Page<User> pagination = new Page<>(1, 2);
        IPage<User> records = repository.selectPage(pagination, agentId);
        System.out.println(records);
    }
}
```