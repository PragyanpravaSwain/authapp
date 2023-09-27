package com.pragyan.authapp.authapp.repositories;

import com.pragyan.authapp.authapp.entity.Category;
import com.pragyan.authapp.authapp.entity.Post;
import com.pragyan.authapp.authapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
