package com.workshop.studentmanagement1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void addStudentRejectsDuplicateEmail() {
        StudentDTO dto = new StudentDTO();
        dto.setName("Viji");
        dto.setEmail("viji@gmail.com");
        dto.setAge(22);
        dto.setCourse("CSE");
        when(studentRepository.existsByEmail("viji@gmail.com"))
                .thenReturn(true);
        assertThrows(
                DuplicateEmailException.class,
                () -> studentService.addStudent(dto)
        );
        verify(studentRepository, never()).save(any(Student.class));
    }
    @Test
    void addStudentSucessfully(){
        StudentDTO dto = new StudentDTO();
        dto.setName("Arun");
        dto.setEmail("arun@gmail.com");
        dto.setAge(21);
        dto.setCourse("CSE");
        when(studentRepository.existsByEmail("arun@gmail.com"))
                .thenReturn(false);
        when(studentRepository.save(any(Student.class)))
                .thenAnswer(invocation -> {
                   Student student = invocation.getArgument(0);
                   student.setId(1);
                   return student;
                });
        StudentResponseDTO response = studentService.addStudent(dto);
        assertEquals(1, response.getId());
        assertEquals("Arun", response.getName());
        assertEquals("arun@gmail.com", response.getEmail());
        verify(studentRepository, times(1))
                .save(any(Student.class));
    }
}