package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDto {
    @Size(min=3, max=10, message = "Имя должно быть от 3 до 10 символов")
    private String firstName;

    @Size(min=3, max=10, message = "Фамилия должна быть от 3 до 10 символов")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Телефон должен быть в формате +7 XXX XXX-XX-XX")
    private String phone;
}

