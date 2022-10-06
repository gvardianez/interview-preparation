package ru.alov.studentservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alov.studentservice.converters.StudentConverter;
import ru.alov.studentservice.dto.StudentDto;
import ru.alov.studentservice.entities.Student;
import ru.alov.studentservice.exceptions.ResourceNotFoundException;
import ru.alov.studentservice.services.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Page<StudentDto> getAllStudents(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "page_size", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "min_age", required = false) Integer minAge,
            @RequestParam(name = "max_age", required = false) Integer maxAge,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return studentService.findAllStudents(minAge, maxAge, namePart, page, pageSize).map(StudentConverter.STUDENT_CONVERTER::entityToDto);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto saveNewStudent(@RequestBody StudentDto studentDto) {
        return StudentConverter.STUDENT_CONVERTER.entityToDto(studentService.addNewStudent(studentDto));
    }

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        return StudentConverter.STUDENT_CONVERTER.entityToDto(studentService.updateStudent(studentDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}
