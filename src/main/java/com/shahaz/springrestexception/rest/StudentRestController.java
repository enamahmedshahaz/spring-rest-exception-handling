package com.shahaz.springrestexception.rest;


import com.shahaz.springrestexception.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    @PostConstruct
    public void loadStudents() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Enam", "Ahmed", "shahaz@gmail.com"));
        studentList.add(new Student("Emon", "Ahmed", "sanglap@gmail.com"));
        studentList.add(new Student("Lailatul", "Ferdous", "mousumi@gmail.com"));
    }


    @GetMapping("/students")
    public List<Student> getAllStudentInfo() {
        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentInfo(@PathVariable int studentId) {

        //step 3: update rest service to throw exception
        //keep it simple only use array index to get data
        if (studentId < 0 || studentId >= studentList.size()) {
            throw new StudentNotFoundException("Student not found with Id = " + studentId);
        }

        return studentList.get(studentId);
    }

    //step 4: Add an exception handler method using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //step 5: Add another exception handler method using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
