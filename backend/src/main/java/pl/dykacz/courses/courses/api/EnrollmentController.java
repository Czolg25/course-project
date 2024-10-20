package pl.dykacz.courses.courses.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import pl.dykacz.courses.courses.api.objects.EnrollmentApi;
import pl.dykacz.courses.courses.objects.Course;
import pl.dykacz.courses.courses.objects.Enrollment;
import pl.dykacz.courses.courses.objects.EnrollmentRequest;
import pl.dykacz.courses.courses.objects.Student;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.services.CoursesService;
import pl.dykacz.courses.courses.services.EnrollmentService;
import pl.dykacz.courses.courses.services.StudentService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CoursesService coursesService;

    public EnrollmentController(@NonNull final EnrollmentService enrollmentService,@NonNull final StudentService studentService,
                                @NonNull final CoursesService coursesService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.coursesService = coursesService;
    }

    @GetMapping
    public List<EnrollmentApi> getAllEnrollments() {
        final List<EnrollmentApi> enrollments = new ArrayList<>();
        for (final Enrollment enrollment : this.enrollmentService.getAllEnrollments()) enrollments.add(new EnrollmentApi(enrollment));

        return enrollments;
    }

    @PostMapping
    public ResponseEntity<Void> addEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
        if(enrollmentRequest.getCourseId() == null || enrollmentRequest.getStudentId() == null)return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();


        final Student student = this.studentService.findStudentById(new Id(enrollmentRequest.getStudentId()));
        if (student == null) return ResponseEntity.notFound().build();

        final Course course = this.coursesService.findCourseById(new Id(enrollmentRequest.getCourseId()));
        if (course == null) return ResponseEntity.notFound().build();

        final Enrollment enrollment = new Enrollment(null, student, course);


        if(this.enrollmentService.addEnrollment(enrollment)) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        this.enrollmentService.deleteEnrolment(new Id(id));

        if(this.enrollmentService.deleteEnrolment(new Id(id))) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}

