package com.example.bloggingaplication.repositories;

import com.example.bloggingaplication.entity.Category;
import com.example.bloggingaplication.entity.Post;
import com.example.bloggingaplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("SELECT p FROM Post p WHERE p.title LIKE :key")
    List<Post> searchByTitle(@Param("key") String keyword);


}
