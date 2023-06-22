package com.example.bloggingaplication.services;

import com.example.bloggingaplication.payloads.PostDto;
import com.example.bloggingaplication.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    //CREATE
    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId );

    // UPDATE
    PostDto updatePost(PostDto postDto, Integer postId);

    //DELETE
    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir );

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
