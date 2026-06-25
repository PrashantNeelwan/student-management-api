package com.student.student_management.serivice;

import com.student.student_management.dto.StudentRequestDTO;
import com.student.student_management.dto.StudentResponseDTO;
import com.student.student_management.entity.Student;
import com.student.student_management.exception.EmailAlreadyExistsException;
import com.student.student_management.exception.StudentNotFoundException;
import com.student.student_management.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Page<StudentResponseDTO> getAllStudents(Pageable pageable){
        return studentRepository.findAll(pageable)
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getAge()
                ));
    }


    public StudentResponseDTO createStudent(StudentRequestDTO request){
        logger.info("Creating new student with email: {}", request.getEmail());

        if(studentRepository.existsByEmail(request.getEmail())){
            logger.warn("Email {} already exists", request.getEmail());

            throw new EmailAlreadyExistsException(request.getEmail());
        }
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());

        Student savedStudent = studentRepository.save(student);
        logger.info("Student created successfully with id: {}", savedStudent.getId());


        return new StudentResponseDTO(
                savedStudent.getId(),
                savedStudent.getFirstName(),
                savedStudent.getLastName(),
                savedStudent.getEmail(),
                savedStudent.getAge()
        );
    }

    public StudentResponseDTO getStudentById(Long id){
        logger.info("Fetching student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(()-> {
                    logger.error("Student not found with id: {}", id);
                    return new StudentNotFoundException(id);
                });
        logger.info("Student found: {} {}", student.getFirstName(), student.getLastName());

        return new StudentResponseDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge());
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new  StudentNotFoundException(id));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());

        Student savedStudent = studentRepository.save(student);
        return  new StudentResponseDTO(savedStudent.getId(), savedStudent.getFirstName(),  savedStudent.getLastName(), savedStudent.getEmail(),savedStudent.getAge());
    }
    public void deleteById(Long id){
        logger.info("Deleting student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(()-> {
                    logger.error("Cannot delete - student not found with id: {}", id);
                    return new  StudentNotFoundException(id);
                });
        studentRepository.deleteById(id);
    }
}
