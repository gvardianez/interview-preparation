package ru.alov.studentservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alov.studentservice.dto.StudentDto;
import ru.alov.studentservice.entities.Student;

import java.util.List;

@Mapper
public interface StudentConverter {

    StudentConverter STUDENT_CONVERTER = Mappers.getMapper(StudentConverter.class);

    StudentDto entityToDto(Student student);

    List<StudentDto> studentListToDto(List<Student> studetnLists);

}
