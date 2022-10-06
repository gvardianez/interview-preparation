package ru.alov.studentservice.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.alov.studentservice.entities.Student;

public class StudentsSpecifications {

    public static Specification<Student> ageGreaterOrEqualsThan(Integer age) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age);
    }

    public static Specification<Student> ageLessThanOrEqualsThan(Integer age) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("age"), age);
    }

    public static Specification<Student> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
