package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
@Tag(name = "Student Management", description = "API pour la gestion des étudiants")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    @Operation(summary = "Enregistrer un étudiant", description = "Crée un nouvel étudiant dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer un étudiant", description = "Supprime un étudiant par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Étudiant supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Identifiant de l'étudiant") @PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Récupérer tous les étudiants", description = "Retourne la liste complète des étudiants")
    @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/count")
    @Operation(summary = "Compter les étudiants", description = "Retourne le nombre total d'étudiants")
    @ApiResponse(responseCode = "200", description = "Nombre d'étudiants récupéré avec succès")
    public ResponseEntity<Long> countStudent() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/byYear")
    @Operation(summary = "Statistiques par année", description = "Retourne le nombre d'étudiants regroupés par année de naissance")
    @ApiResponse(responseCode = "200", description = "Statistiques récupérées avec succès")
    public ResponseEntity<Collection<?>> findByYear() {
        Collection<?> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);
    }
}
