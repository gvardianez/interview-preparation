package lesson_5_hibernate;

import lesson_5_hibernate.abstract_dao.AbstractDao;
import lesson_5_hibernate.abstract_dao.Dao;
import lesson_5_hibernate.entities.Student;
import lesson_5_hibernate.migration.FlywayBaseMigration;
import lesson_5_hibernate.session_factory.SessionFactoryManager;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new FlywayBaseMigration("jdbc:postgresql://192.168.99.100:5434/postgres?currentSchema=lesson5", "postgres", "postgres").migrate();
        Dao<Student, Long> studentDao = new AbstractDao<>(Student.class, SessionFactoryManager.getSessionFactory());
        Student student = new Student();
        Student student2 = new Student();
        student.setMark(5);
        student.setName("Vasya");
        studentDao.saveOrUpdate(student);
        student.setName("Petya");
        studentDao.update(student);
        student2.setName("Vova");
        student2.setMark(4);
        studentDao.saveOrUpdate(student2);
        studentDao.delete(student2);
        List<Student> students = studentDao.findAll();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            studentList.add(new Student("v+" + i,4));
        }
        studentDao.saveOrUpdate(studentList);
    }
}


