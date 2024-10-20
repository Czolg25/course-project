package pl.dykacz.courses.courses.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import pl.dykacz.courses.courses.api.objects.CourseApi;
import pl.dykacz.courses.courses.objects.Course;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.services.CoursesService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CoursesController {
    private final CoursesService cursesService;

    @Autowired
    public CoursesController(@NonNull final CoursesService cursesService) {
        this.cursesService = cursesService;
    }

    @GetMapping
    public List<CourseApi> getAllCourses() {
        final List<CourseApi> courses = new ArrayList<>();
        for (final Course course : this.cursesService.getAllCourses()) courses.add(new CourseApi(course));

        return courses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseApi> getCourseById(@PathVariable Long id) {
        Course course = this.cursesService.findCourseById(new Id(id));
        if (course == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new CourseApi(course));
    }

    @PostMapping
    public ResponseEntity<Void> addCourse(@RequestBody CourseApi course) {
        if(this.cursesService.addCourse(new Course(course))) return ResponseEntity.noContent().build();

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseApi> updateCourse(@PathVariable Long id, @RequestBody CourseApi courseDetails) {
        if(!this.cursesService.updateCourse(new Course(courseDetails))){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CourseApi(this.cursesService.findCourseById(new Id(id))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if(this.cursesService.deleteCourse(new Id(id))) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
