package com.pragyan.authapp.authapp.controllers;

import com.pragyan.authapp.authapp.payloads.ApiResponse;
import com.pragyan.authapp.authapp.payloads.CategoryDto;
import com.pragyan.authapp.authapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return  new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
        return  ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return  new ResponseEntity<>(new ApiResponse("category is deleted Succesfully", true),
                HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategories(){

        return ResponseEntity.ok(categoryService.getCategories());
    }
    @GetMapping("/{catId}")
    public ResponseEntity<?> getCategory(@PathVariable Integer catId){
        return ResponseEntity.ok(categoryService.getCategory(catId));
    }
}
