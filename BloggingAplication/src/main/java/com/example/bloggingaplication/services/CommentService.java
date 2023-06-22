package com.example.bloggingaplication.services;

import com.example.bloggingaplication.payloads.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, Integer postId);

//    public CommentDto updateComment(CommentDto commentDto);

    public void deleteComment(Integer id);

//    public CommentDto getCommentById(Integer id);

//    public List<CommentDto> getAllComments();

}
