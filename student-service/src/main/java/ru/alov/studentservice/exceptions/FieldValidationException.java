package ru.alov.studentservice.exceptions;

import java.util.List;

public class FieldValidationException extends RuntimeException {

    public FieldValidationException(List<String> errorFieldsMessages) {
        super(String.join(", ", errorFieldsMessages));
    }
}
