package com.pragyan.authapp.authapp.payloads;

import com.pragyan.authapp.authapp.entity.Category;
import com.pragyan.authapp.authapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String title;
    private  String content;
    private String imageName;
    private Date addDate;
    private CategoryDto category;
    private UserDto user;

}
