package org.example.mybatis.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@TableName("cloud_user")
public class User {
    private String id;
    private String name;
    private String nickName;
    private String agentId;
}
