package org.example.basic.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>创建时间: 2021/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Entity 转换 DTO
     *
     * @param entity
     * @return
     */
    UserDTO toDto(User entity);

    /**
     * DTO 转换 Entity
     *
     * @param dto
     * @return
     */
    User toEntity(UserDTO dto);
}
