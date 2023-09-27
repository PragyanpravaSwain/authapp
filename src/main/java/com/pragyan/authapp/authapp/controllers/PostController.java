package com.pragyan.authapp.authapp.controllers;

import com.pragyan.authapp.authapp.entity.Post;
import com.pragyan.authapp.authapp.payloads.ApiResponse;
import com.pragyan.authapp.authapp.payloads.PostDto;
import com.pragyan.authapp.authapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
@PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
        @RequestBody PostDto postDto,
        @PathVariable Integer userId,
        @PathVariable Integer categoryId) {
        PostDto createPost = postService.createPost(postDto, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId){
       return ResponseEntity.ok(postService.getPostByUser(userId));

    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.ok(postService.getPostByCategory(categoryId));

    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost(@RequestBody Integer pageNumber, @RequestBody Integer pageSize){
        return ResponseEntity.ok(postService.getAllPost(PageRequest.of(pageNumber, pageSize)));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Integer postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    @DeleteMapping("/{postId}/post")
    public ResponseEntity<?> deletepost(@PathVariable Integer postId){
        postService.deletePost(postId);
          return new ResponseEntity<>(new ApiResponse
                  ("User Deleted Successfully", true), HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatepost(@RequestBody PostDto postDto, @PathVariable Integer postId){
         PostDto updatedPost = this.postService.updatePost(postDto, postId);
         return ResponseEntity.ok(updatedPost);
    }

}
