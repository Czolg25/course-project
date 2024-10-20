package pl.dykacz.courses.courses.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dykacz.courses.courses.api.objects.StudentApi;
import pl.dykacz.courses.courses.objects.Student;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentApi> getAllStudents() {
        final List<StudentApi> students = new ArrayList<>();
        for (final Student student : this.studentService.getAllStudents()) students.add(new StudentApi(student));

        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentApi> getStudentById(@PathVariable long id) {
        final Student student = this.studentService.findStudentById(new Id(id));
        if (student == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new StudentApi(student));
    }

    @PostMapping("/{id}")
    public ResponseEntity<StudentApi> updateStudent(@PathVariable long id, @RequestBody StudentApi studentDetails) {
        if(!this.studentService.updateStudent(new Student(studentDetails))){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new StudentApi(this.studentService.findStudentById(new Id(id))));
    }

    @PostMapping
    public ResponseEntity<StudentApi> addStudent(@RequestBody StudentApi student) {
        final Student newStudent = this.studentService.addStudent(new Student(student));
        if(newStudent != null) return ResponseEntity.ok(new StudentApi(newStudent));

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentApi> updateStudent(@PathVariable Long id, @RequestBody StudentApi studentDetails) {
        if(!this.studentService.updateStudent(new Student(studentDetails))){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new StudentApi(this.studentService.findStudentById(new Id(id))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if(this.studentService.deleteStudent(new Id(id))) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
