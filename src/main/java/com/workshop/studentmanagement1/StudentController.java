package com.workshop.studentmanagement1;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "Student Management",
        description = "APIs for creating, retrieving, updating, deleting, and searching students"
)
public class StudentController  {
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.addStudent(studentDTO),HttpStatus.OK);
    }
    @GetMapping("/students")
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudents(Pageable pageable){
        return new ResponseEntity<>(studentService.getAllStudent(pageable),HttpStatus.OK);
    }
    @GetMapping("/students/{id}")
    ResponseEntity<StudentResponseDTO> getById(@PathVariable Integer id){
        return new ResponseEntity<>(studentService.getById(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        studentService.delete(id);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable Integer id,@Valid @RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.update(id, studentDTO),HttpStatus.OK);
    }
    @GetMapping("/student/search")
    public ResponseEntity<List<StudentResponseDTO>> searchByName(@RequestParam("name" )String name){
        return new ResponseEntity<>(
                studentService.searchByName(name),
                HttpStatus.OK
        );
    }
    @GetMapping("/student/search/course")
    public ResponseEntity<List<StudentResponseDTO>> searchByCourse(@RequestParam("course") String course){
        return new ResponseEntity<>(studentService.searchByCourse(course),
                HttpStatus.OK);
    }
}
