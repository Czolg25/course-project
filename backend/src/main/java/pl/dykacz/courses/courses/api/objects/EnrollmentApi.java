package pl.dykacz.courses.courses.api.objects;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.objects.Enrollment;

public class EnrollmentApi {
    private final Long enrollmentId;
    private final StudentApi student;
    private final CourseApi course;

    public EnrollmentApi(@NonNull Enrollment enrollment){
        this(enrollment.enrollmentId() != null? enrollment.enrollmentId().getIdAsLong():null,
                enrollment.student() != null? new StudentApi(enrollment.student()):null,
                enrollment.course() != null? new CourseApi(enrollment.course()):null);
    }

    public EnrollmentApi(Long enrollmentId, StudentApi student, CourseApi course) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public StudentApi getStudent() {
        return student;
    }

    public CourseApi getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "EnrollmentApi{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
