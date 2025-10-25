package com.example.student_management;

import com.example.student_management.controller.StudentController;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setId(1);
        student.setNom("LACHGAR");
        student.setPrenom("Mohamed");
        student.setDateNaissance(new Date());

        when(studentService.save(any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.save(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("LACHGAR", response.getBody().getNom());
        assertEquals("Mohamed", response.getBody().getPrenom());
    }

    @Test
    void testDeleteStudent() {
        when(studentService.delete(1)).thenReturn(true);
        ResponseEntity<Void> response = studentController.delete(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteStudentNotFound() {
        when(studentService.delete(999)).thenReturn(false);
        ResponseEntity<Void> response = studentController.delete(999);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testFindAllStudents() {
        Student student1 = new Student();
        student1.setId(1);
        student1.setNom("Student1");
        student1.setPrenom("Prenom1");
        
        Student student2 = new Student();
        student2.setId(2);
        student2.setNom("Student2");
        student2.setPrenom("Prenom2");
        
        when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

        ResponseEntity<List<Student>> response = studentController.findAll();

        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCountStudents() {
        when(studentService.countStudents()).thenReturn(10L);
        ResponseEntity<Long> response = studentController.countStudent();
        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindByYear() {
        Object[] year1985 = {1985, 2};
        Object[] year1990 = {1990, 1};
        List<Object[]> mockResult = Arrays.asList(year1985, year1990);
        doReturn(mockResult).when(studentService).findNbrStudentByYear();
        
        ResponseEntity<Collection<?>> response = studentController.findByYear();
        
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
