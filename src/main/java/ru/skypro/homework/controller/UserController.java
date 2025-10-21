package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    ResponseEntity<UserDto> getUser() {
        UserDto userDto = new UserDto();
        return ResponseEntity.ok(userDto);
    }

    //обновление информации об авторизованном пользователе
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }

    //обновление аватара авторизованного пользователя
    @PatchMapping(value = "/{id}/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@PathVariable("id") Integer userId,
                                             @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}
