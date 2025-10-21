package ru.skypro.homework.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.user.Register;
import ru.skypro.homework.dto.user.Role;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.model.UserEntity;

@Component
public class UserMapper {

    @Value("${app.base-url:http://localhost:8080}")
    String baseUrl;

    public UserDto toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());

        if (entity.getRole() != null) {
            dto.setRole(entity.getRole().name());
        }

        // Преобразуем относительный путь в полный URL
        if (entity.getImage() != null) {
            dto.setImage(baseUrl + entity.getImage());
        } else {
            dto.setImage(null);
        }

        return dto;
    }

    public UserEntity toEntity(Register dto) {
        if (dto == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getUsername()); // username в Register -> email в Entity
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole(Role.valueOf(dto.getRole()));
        // image не устанавливаем при регистрации

        return entity;
    }

    public void updateEntityFromDto(UpdateUserDto dto, UserEntity entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getFirstName() != null) {
            entity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            entity.setLastName(dto.getLastName());
        }
        if (dto.getPhone() != null) {
            entity.setPhone(dto.getPhone());
        }
    }

    // Метод для установки относительного пути к изображению пользователя
    public void setUserImagePath(UserEntity entity, String fileName) {
        if (fileName != null && entity.getId() != null) {
            entity.setImage("/users/images/" + entity.getId());
        } else {
            entity.setImage(null);
        }
    }

    // Метод для удаления изображения пользователя
    public void removeUserImagePath(UserEntity entity) {
        entity.setImage(null);
    }

}
