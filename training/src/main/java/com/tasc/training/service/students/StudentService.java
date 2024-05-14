package com.tasc.training.service.students;

import com.tasc.training.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();
    
    Student getById(int id);
    
    Student getByName(String name);
    
    Student create(Student student);
    
    Student update(int id, Student student);

    void delete(int id);
}
