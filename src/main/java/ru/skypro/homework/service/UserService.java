package ru.skypro.homework.service;

import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;

public interface UserService {
    UserDto getUser(String username);

    UpdateUserDto updateUser(UpdateUserDto updateUserDto, String username);

    boolean updatePassword(NewPasswordDto newPasswordDto, String username);

    boolean updateUserImage(byte[] image, String username);
}
