package com.example.EyeQlytics.EyeQlytics.dto;

import com.example.EyeQlytics.EyeQlytics.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private User user;
    private String token;
}


