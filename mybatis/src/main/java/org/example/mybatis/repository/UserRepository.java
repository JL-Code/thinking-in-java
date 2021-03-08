package org.example.mybatis.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.mybatis.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Repository
public interface UserRepository extends BaseMapper<User> {

    @Select("select t1.nick_name,t1.id,t1.name,t2.agent_id,t2.create_time from cloud_user t1 right join cloud_invitation_user_agent t2 on" +
            " t1.id = t2.user_id where t2.agent_id=#{agentId}")
    List<User> selectUserPage(Page<User> pagination, @Param("agentId") String agentId);

    @Select("select t1.nick_name,t1.id,t1.name,t2.agent_id,t2.create_time from cloud_user t1 right join cloud_invitation_user_agent t2 on" +
            " t1.id = t2.user_id where t2.agent_id=#{agentId} order by t1.name")
    IPage<User> selectPage(Page<User> pagination, String agentId);
}
