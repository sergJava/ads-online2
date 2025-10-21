package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String username) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
        return userMapper.toDto(userEntity);
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto, String username) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));

        userMapper.updateEntityFromDto(updateUserDto, userEntity);
        UserEntity savedEntity = userRepository.save(userEntity);

        UpdateUserDto result = new UpdateUserDto();
        result.setFirstName(savedEntity.getFirstName());
        result.setLastName(savedEntity.getLastName());
        result.setPhone(savedEntity.getPhone());

        return result;
    }

    @Override
    public boolean updatePassword(NewPasswordDto newPasswordDto, String username) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));

        if (!passwordEncoder.matches(newPasswordDto.getCurrentPassword(), userEntity.getPassword())) {
            return false;
        }

        userEntity.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean updateUserImage(byte[] image, String username) {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));


        return false;
    }
}
