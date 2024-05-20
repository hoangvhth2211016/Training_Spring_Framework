package com.tasc.training.controller;


import com.tasc.training.model.student.Student;
import com.tasc.training.service.students.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> getAll(){
        return studentService.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){

        return ResponseEntity.ok(studentService.getById(id));
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid Student student){
        return ResponseEntity.ok(studentService.create(student));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody @Valid Student student){
        return ResponseEntity.ok(studentService.update(id, student));
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        studentService.delete(id);
        return "student deleted";
    }
    
    @GetMapping("/search")
    public Student getByName(@RequestParam(value = "name") String name){
        return studentService.getByName(name);
    }
    
    
}
