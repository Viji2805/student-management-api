package com.workshop.studentmanagement1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentResponseDTO addStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new DuplicateEmailException(
                    "Student with email " + studentDTO.getEmail()
                            + " already exists"
            );
        }
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setCourse(studentDTO.getCourse());
        Student savedStudent = studentRepository.save(student);
        return toResponseDTO(savedStudent);
    }
    public StudentResponseDTO toResponseDTO(Student student) {
        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setAge(student.getAge());
        response.setCourse(student.getCourse());
        response.setCreatedAt(student.getCreatedAt());
        response.setUpdatedAt(student.getUpdatedAt());
        return response;
    }
    public StudentResponseDTO getById(Integer id) {
        Student student= studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with id " + id + " not found"));
        return toResponseDTO(student);
    }
    public StudentResponseDTO update(Integer id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with id " + id + " not found"));
        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setAge(studentDTO.getAge());
        existingStudent.setCourse(studentDTO.getCourse());
        Student savedStudent = studentRepository.save(existingStudent);
        return toResponseDTO(savedStudent);
    }
    public void delete(Integer id){
        studentRepository.deleteById(id);
    }
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> responseList = new ArrayList<>();
        for (Student student : students) {
            responseList.add(toResponseDTO(student));
        }
        return responseList;
    }
    public Page<StudentResponseDTO> getAllStudent(Pageable pageable) {
        return studentRepository.findAll(pageable).map(this::toResponseDTO);
    }
    public List<StudentResponseDTO> searchByName(String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        List<StudentResponseDTO> responseList = new ArrayList<>();
        for (Student student : students) {
            responseList.add(toResponseDTO(student));
        }
        return responseList;
    }
    public List<StudentResponseDTO> searchByCourse(String course){
        List<Student> students = studentRepository.findByCourseContainingIgnoreCase(course);
        List<StudentResponseDTO> responseList = new ArrayList<>();
        for (Student student : students){
            responseList.add(toResponseDTO(student));
        }
        return responseList;
    }
}
