package ru.skypro.homework.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;       //логин пользователя
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;       // полный URL, например: "http://localhost:8080/users/images/1"
}
