package pl.dykacz.courses.courses.repositories;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.objects.Course;
import pl.dykacz.courses.courses.objects.Enrollment;
import pl.dykacz.courses.courses.objects.Student;
import pl.dykacz.courses.courses.objects.values.Id;

import java.util.List;

public interface DataRepository {
    List<Course> getAllCourses();
    Course getCourse(@NonNull final Id courseId);
    boolean updateCourse(@NonNull final Course course);
    boolean deleteCourse(@NonNull final Id curseId);
    boolean addCourse(@NonNull final Course course);
    List<Student> getAllStudents();
    Student getStudent(@NonNull final Id studentId);
    boolean updateStudent(@NonNull final Student student);
    boolean deleteStudent(@NonNull final Id studentId);
    Student addStudent(@NonNull final Student student);
    List<Enrollment> getAllEnrollments();
    boolean addEnrollment(@NonNull final Enrollment enrollment);
    boolean deleteEnrolment(@NonNull final Id enrollmentId);
}
