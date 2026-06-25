package com.student.student_management.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
