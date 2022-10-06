package ru.alov.studentservice.validators;

import org.springframework.stereotype.Component;
import ru.alov.studentservice.dto.StudentDto;
import ru.alov.studentservice.exceptions.FieldValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidator {

    public void validate(StudentDto studentDto) {
        List<String> errors = new ArrayList<>();
        if (studentDto.getName() == null || studentDto.getName().isBlank()) {
            errors.add("Поле имя студента не должно быть пустым");
        }
        if (studentDto.getAge() == null || studentDto.getAge() < 14) {
            errors.add("Поле с возрастом некорректно");
        }
        if (!errors.isEmpty()) {
            throw new FieldValidationException(errors);
        }
    }
}
