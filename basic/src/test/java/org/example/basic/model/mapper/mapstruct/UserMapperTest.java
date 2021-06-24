//package org.example.basic.model.mapper.mapstruct;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * <p>创建时间: 2021/6/18 </p>
// *
// * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
// * @version v1.0
// */
//class UserMapperTest {
//
//    @Test
//    void mapToEntity() {
//        //given
//        User user = new User();
//        user.setId("1");
//        //when
//        UserDTO dto = UserMapper.INSTANCE.toDto(user);
//
//        //then
//        assertNotNull(dto);
//        assertEquals(user.getId(), dto.getId());
//    }
//}