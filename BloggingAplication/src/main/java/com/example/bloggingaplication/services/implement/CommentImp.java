package com.example.bloggingaplication.services.implement;

import com.example.bloggingaplication.entity.Comment;
import com.example.bloggingaplication.entity.Post;
import com.example.bloggingaplication.exceptions.ResourceNotFoundException;
import com.example.bloggingaplication.payloads.CommentDto;
import com.example.bloggingaplication.payloads.PostDto;
import com.example.bloggingaplication.repositories.CommentRepository;
import com.example.bloggingaplication.repositories.PostRepository;
import com.example.bloggingaplication.services.CommentService;
import com.example.bloggingaplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id: ", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id: ", id));
        commentRepository.delete(comment);
    }

}
