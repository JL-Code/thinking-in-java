package org.example.basic.mapper;

import org.example.basic.model.mapper.Post;
import org.example.basic.model.mapper.PostDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ModelMapper 映射框架
 * http://modelmapper.org/getting-started/
 */
class PostDTOConverionTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    @DisplayName("EntityToDTO")
    public void whenConvertPostEntityToPostDto_thenCorrect() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("测试标题");
        post.setUrl("www.test.com");

        PostDTO postDto = modelMapper.map(post, PostDTO.class);
        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getTitle(), postDto.getTitle());
        assertEquals(post.getUrl(), postDto.getUrl());
    }

    @Test
    @DisplayName("DTOToEntity")
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        PostDTO postDto = new PostDTO();
        postDto.setId(1L);
        postDto.setTitle("测试标题");
        postDto.setUrl("www.test.com");

        Post post = modelMapper.map(postDto, Post.class);
        assertEquals(postDto.getId(), post.getId());
        assertEquals(postDto.getTitle(), post.getTitle());
        assertEquals(postDto.getUrl(), post.getUrl());
    }
}
