package ru.alov.studentservice.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.alov.studentservice.dto.StudentDto;
import ru.alov.studentservice.entities.Student;
import ru.alov.studentservice.repositories.StudentRepository;
import ru.alov.studentservice.specifications.StudentsSpecifications;
import ru.alov.studentservice.validators.StudentValidator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentValidator studentValidator;

    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Page<Student> findAllStudents(Integer minAge, Integer maxAge, String partName, Integer page, Integer pageSize) {
        System.out.println(maxAge);
        System.out.println(minAge);
        System.out.println(partName);
        if (page < 1) {
            page = 1;
        }
        Specification<Student> spec = Specification.where(null);
        if (minAge!= null) {
            spec = spec.and(StudentsSpecifications.ageGreaterOrEqualsThan(minAge));
        }
        if (maxAge != null) {
            spec = spec.and(StudentsSpecifications.ageLessThanOrEqualsThan(minAge));
        }
        if (partName != null) {
            spec = spec.and(StudentsSpecifications.nameLike(partName));
        }
        return studentRepository.findAll(spec, PageRequest.of(page - 1, pageSize));
    }

    public Student addNewStudent(StudentDto studentDto) {
        studentValidator.validate(studentDto);
        return studentRepository.save(new Student(studentDto.getName(), studentDto.getAge()));
    }

    public Student updateStudent(StudentDto studentDto) {
        studentValidator.validate(studentDto);
        return studentRepository.save(new Student(studentDto.getId(), studentDto.getName(), studentDto.getAge()));
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
