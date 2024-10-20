package pl.dykacz.courses.courses.objects;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.objects.values.Id;

public record Enrollment(Id enrollmentId, Student student, Course course) {
    public Enrollment(final Id enrollmentId, @NonNull final Student student, Course course) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
