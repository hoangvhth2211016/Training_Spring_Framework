package com.tasc.training.service.students;

import com.tasc.training.exception.AlreadyExistException;
import com.tasc.training.exception.NotFoundException;
import com.tasc.training.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    
    private static List<Student> students = new ArrayList<>();
    
    public StudentServiceImpl(){
        students.addAll(Arrays.asList(
                new Student(1, "david", "david@gmail.com", "Mathematic"),
                new Student(2, "adam", "adam@gmail.com", "IT"),
                new Student(3, "Anna", "anna@gmail.com", "Physic")
        ));
    }
    
    @Override
    public List<Student> getAll() {
        return students;
    }
    
    @Override
    public Student getById(int id) {
        return students
                .stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }
    
    @Override
    public Student getByName(String name) {
        return students
                .stream()
                .filter(s -> s.getName().startsWith(name))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }
    
    @Override
    public Student create(Student student) {
        if (students.stream().anyMatch(s -> s.getId() == student.getId())) throw new AlreadyExistException("Student Already Exist");
        students.add(student);
        return student;
    }
    
    @Override
    public Student update(int id, Student student) {
        var foundStudent = getById(id);
        foundStudent.setId(student.getId());
        foundStudent.setName(student.getName());
        foundStudent.setEmail(student.getEmail());
        foundStudent.setMajor(student.getMajor());
        return foundStudent;
    }
    
    @Override
    public void delete(int id) {
        var student = getById(id);
        students.remove(student);
    }
}
