package com.student.student_management.controller;

import com.student.student_management.dto.StudentRequestDTO;
import com.student.student_management.dto.StudentResponseDTO;
import com.student.student_management.serivice.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size,
                                                                   @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO request){
        return ResponseEntity.status(201).body(studentService.createStudent(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){
        return ResponseEntity.status(200).body(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(@PathVariable Long id,@Valid @RequestBody StudentRequestDTO request){
        return ResponseEntity.status(200).body(studentService.updateStudent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        studentService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

}
